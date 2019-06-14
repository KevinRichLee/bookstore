package com.bookstore.manager.controller;

import com.bookstore.common.utils.IdUtils;
import com.bookstore.common.utils.PageModel;
import com.bookstore.manager.service.ManagerService;
import com.bookstore.manager.vo.SearchCon;
import com.bookstore.orders.bean.Order;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.bean.ProductList;
import com.bookstore.products.service.ProductService;
import com.bookstore.user.bean.User;
import com.bookstore.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PostMapping("login.do")
    public String login(User user, Model model, HttpSession session){
        User loginAdminUser = userService.checkLoginUser(user);
        if(loginAdminUser!=null){
            if("超级管理员".equals(loginAdminUser.getRole())){
                session.setAttribute("loginAdminUser",loginAdminUser);
                return "redirect:/admin/login/home.jsp";
            }else{
                return "redirect:/admin/error/privilege.jsp";
            }
        }else {
            model.addAttribute("error","用户名或密码有误");
            return "redirect:/admin/login/login.jsp";
        }
    }
    @GetMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("loginAdminUser");
        return "/admin/login/login.jsp";
    }
    @RequestMapping("list.do")
    public String findAllByCondition(@RequestParam(defaultValue = "1")Integer pageIndex, SearchCon searchCondition,Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        Integer totalCount = managerService.selectCount(searchCondition);
        pageModel.setRecordCount(totalCount);
        model.addAttribute("sc",searchCondition);
        List<Product> products = managerService.findAllByCondition(searchCondition,pageModel);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        return "/admin/products/list.jsp";
    }
    @GetMapping("deleteProduct.do")
    public String deleteProduct(@RequestParam("id")Integer id){

        Integer count = managerService.deleteProduct(id);
        if(count!=null){
            return "redirect:/admin/list.do";
        }
        return null;
    }
    @GetMapping("findProductById.do")
    public String findProductById(@RequestParam("id")String id, Model model){
        Product product = productService.queryProductById(id);
        if(product!=null){
            model.addAttribute("p",product);
            return "/admin/products/edit.jsp";
        }
        return null;
    }
    @PostMapping("editProduct.do")
    public String editProduct(Product product, MultipartFile upload,HttpSession session) throws IOException {
        if(!upload.isEmpty()){
            //将原来的文件删除，先找到原来文件路径
            String path = session.getServletContext().getRealPath("productImg");
            Product sourceProduct = productService.queryProductById(product.getId());

            String imgurl = path + File.separator + StringUtils.substring(sourceProduct.getImgurl(),12);
            File file = new File(imgurl);
            if(file.exists()){
                file.delete();
                sourceProduct.setImgurl(null);
            }
            String newPath = session.getServletContext().getRealPath("/productImg");
            String newFileName = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
            String newImgurl = newPath + File.separator + newFileName;
            upload.transferTo(new File(newImgurl));
            product.setImgurl("/productImg/"+newFileName);
        }
        managerService.updateProduct(product);
        return "redirect:/admin/list.do";
    }
    //添加
    @PostMapping("addProduct.do")
    public String addProduct(Product product,MultipartFile upload,HttpSession session) throws IOException {
        //上传文件的路径设定为tomcat服务器上的路径
        String path = session.getServletContext().getRealPath("/productImg");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String  filename = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
        String imgurl = path + File.separator  + filename;
        upload.transferTo(new File(imgurl));
        product.setId(IdUtils.getUUID());
        product.setImgurl("/productImg/"+filename);
        managerService.addProduct(product);
        return "redirect:/admin/list.do";
    }
    //销售榜单查询
    @RequestMapping("sales/list.do")
    public String querySalesList(String year, String month, Model model, HttpServletResponse response) throws IOException {
        List<ProductList> plist = managerService.querySalesList(year,month);
        if(!CollectionUtils.isEmpty(plist)){
            model.addAttribute("plist",plist);
        }
        model.addAttribute("year",year);
        model.addAttribute("month",month);

        String fileName = year +"年"+month +"月售榜单";
        String sheetName = month +"月售榜单";
        String titleName = year +"年"+month +"月售榜单";
        String[] columnName = {"商品名称","商品销量"};

        //定义二维数组的 行和列长度
        String [][] dataList = new String[plist.size()][2];
        for(int i=0;i<plist.size();i++){
            ProductList pl = plist.get(i);
            dataList[i][0] = pl.getName();
            dataList[i][1] = pl.getSalenum();
        }
        if(year!=null&&month!=null){
            //创建一个excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建wb中的工作表sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            //创建sheet的第一行
            HSSFRow row1 = sheet.createRow(0);
            //创建第一行的第一个单元格
            HSSFCell cell1 = row1.createCell(0);
            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
            cell1.setCellValue(titleName);
            //创建第二行
            HSSFRow row2 = sheet.createRow(1);
            for(int i=0;i<2;i++){
                HSSFCell cell2 = row2.createCell(i);
                cell2.setCellValue(columnName[i]);
            }
            //创建数据行
            for(int i=0;i<plist.size();i++){
                HSSFRow rowi = sheet.createRow(i+2);
                HSSFCell datacell = null;
                for(int j=0;j<2;j++){
                    //创建单元格
                    datacell = rowi.createCell(j);
                    //给单元格赋值
                    datacell.setCellValue(dataList[i][j]);
                }
            }
            //榜单下载
//        String downloadFile = fileName +".xls";
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1")+".xls");
            OutputStream out  = response.getOutputStream();
            wb.write(out);
            out.close();
        }

        return "/admin/products/download.jsp";
    }
    //订单查询
    @RequestMapping("order/list.do")
    public String orderList(@RequestParam(defaultValue = "1")Integer pageIndex,Order order,Model model){
        System.out.println(order.getId()+":"+order.getReceiverName());
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        Integer totalCount = managerService.selectOrderCount(order);
        pageModel.setRecordCount(totalCount);
        model.addAttribute("order",order);
        List<Order> orderList = managerService.queryOrderList(order,pageModel);
        model.addAttribute("orderList",orderList);
        model.addAttribute("pageModel",pageModel);
        return "/admin/orders/list.jsp";
    }
    //查询指定订单详情
    @GetMapping("queryOrderById.do")
    public String queryOrderById(String id, String type, Model model){
        if("超级管理员".equals(type)){
            Order order = managerService.queryOrderById(id);
            model.addAttribute("order",order);
            List<Product> products = order.getProducts();
            model.addAttribute("products",products);
            model.addAttribute("id",id);
            System.out.println(order);
        }
        return "/admin/orders/view.jsp";
    }
    @GetMapping("delOrderById.do")
    public String delOrderById(String id,String type){
        if("超级管理员".equals(type)){
            managerService.delOrderById(id);
        }
        return "redirect:/admin/order/list.do";
    }
    @GetMapping("notice/list.do")
    public String noticeList(Model model){
        List<Notice> notices = managerService.queryNoticeList();
        if(notices!=null){
            model.addAttribute("notices",notices);
        }
        return "/admin/notices/list.jsp";
    }
    @PostMapping("notices/add.do")
    public String noticesAdd(Notice notice){
        int count = managerService.addNotice(notice);
        if(count>0){
            return "redirect:/admin/notice/list.do";
        }
        return null;
    }
    @GetMapping("queryNoticeById.do")
    public String queryNoticeById(Integer id,Model model){
        Notice notice = managerService.queryNoticeById(id);
        if(notice!=null){
            model.addAttribute("n",notice);
            return "/admin/notices/edit.jsp";
        }
        return null;
    }
    @PostMapping("modifyNotice.do")
    public String modifyNotice(Notice notice){
        int count = managerService.modifyNotice(notice);
        if(count>0){
            return "redirect:/admin/notice/list.do";
        }
        return null;
    }
    @GetMapping("deleteNoticeById.do")
    public String deleteNoticeById(Integer n_id){
       int count =  managerService.deleteNoticeById(n_id);
       if(count>0){
           return "redirect:/admin/notice/list.do";
       }
       return null;
    }
}
