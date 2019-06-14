package com.bookstore.common.utils;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/3/7
 * 注意：PageModel类需要什么参数：1，pageIndex：当前页面索引，2，recordCount：记录数
 * 就两个参数
 */
public class PageModel {

    /** 分页总数据条数  */
    private int recordCount;
    /** 当前页面 */
    private int pageIndex ;
    /** 每页分多少条数据   */
    private int pageSize = 4;

    /** 总页数  */
    private int totalSize;

    public int getRecordCount() {
        this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
        return recordCount;
    }
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    public int getPageIndex() {
        this.pageIndex = this.pageIndex;// <= 0?1:this.pageIndex;
        /** 判断当前页面是否超过了总页数:如果超过了默认给最后一页作为当前页  */
        //this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;

        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        //this.pageSize = pageSize;
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        if(this.getRecordCount() <=0){
            totalSize = 0 ;
        }else{
            totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
        }
        return totalSize;
    }

    //当前页面起始索引
    public int getFirstLimitParam(){
        return (this.getPageIndex()-1)*this.getPageSize() ;
    }
}
