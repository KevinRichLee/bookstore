package com.bookstore.orders.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bookstore.common.utils.AlipayConfig;
import com.bookstore.common.utils.IdUtils;
import com.bookstore.orders.bean.Order;
import com.bookstore.orders.bean.OrderItem;
import com.bookstore.orders.service.OrderItemService;
import com.bookstore.orders.service.OrderService;
import com.bookstore.products.bean.Product;
import com.bookstore.products.service.ProductService;
import com.bookstore.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;
    @PostMapping("createOrder.do")
    public String createOrder(Order order, Integer user_id, HttpSession session, Model model){
        OrderItem orderItem = new OrderItem();
            order.setId(IdUtils.getUUID());
            order.setUser_id(user_id);
            orderService.insertOrder(order);
            orderItem.setOrder(order);
            Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            for(Product p:cart.keySet()){
                orderItem.setProduct(p);
                orderItem.setBuynum(cart.get(p));
                orderItemService.insertOrderItem(orderItem);
                 productService.updateNum(cart.get(p),p.getId());
            }
            session.removeAttribute("cart");
            model.addAttribute("order",order);
        return "/client/pay.jsp";
    }
    @PostMapping("onlinepay.do")
    public void onlienPay(String id, String money, HttpServletResponse response) throws AlipayApiException, IOException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String order_id = id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = id;
        //商品描述，可空
        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ order_id +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = client.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html");

        response.getWriter().println(result);
    }
    @GetMapping("paysuccess.do")
    public String paysuccess(HttpServletRequest request,HttpServletResponse response)throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String order_id = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

             orderService.updatePayState(order_id);
            return "/client/paysuccess.jsp";
            // out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("验签失败");
        }
        return null;
    }
    @GetMapping("findOrderByUser.do")
    public String findOrderByUser(HttpSession session, Model model){
        User user = (User) session.getAttribute("login_user");
        List<Order> orders = orderService.findOrderByUser(user.getId());
        model.addAttribute("orders",orders);

        return "/client/orderlist.jsp";
    }
    @GetMapping("findOrderById.do")
    public String findOrderById(@RequestParam("id") String id,Model model){
       List<OrderItem> items = orderItemService.findOrderById(id);
       model.addAttribute("items",items);
       return "/client/orderInfo.jsp";
    }
    @GetMapping("delOrderById.do")
    public String delOrderById(String id,Integer flag, HttpSession session){
        if(flag==null){
            List<OrderItem> items = orderItemService.findOrderById(id);
            for(OrderItem ot:items){
                productService.updateNumAdd(ot.getBuynum(),ot.getProduct().getId());
            }
        }
        orderItemService.deleteOrderItemById(id);
        orderService.deleteOrderById(id);
        return "/order/findOrderByUser.do";
    }
}
