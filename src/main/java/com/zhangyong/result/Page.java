package com.zhangyong.result;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/19 10:37
 */
public class Page {

    //页码
    private int pageNumber = CodeConstant.DEFAULT_PAGE_NUMBER;

    //页面大小
    private int pageSize = CodeConstant.DEFAULT_PAGE_SIZE;

    //总页数
    private int totalPage;

    //总记录数
    private int totalCount;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
