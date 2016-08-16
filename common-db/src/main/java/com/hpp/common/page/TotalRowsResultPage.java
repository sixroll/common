package com.hpp.common.page;

/**
 * Created by huangpingping on 2016/8/5 10:15
 */
public class TotalRowsResultPage implements ResultPage {
    private IPage page;

    private int totalRows;

    public TotalRowsResultPage(IPage page, int totalRows) {
        this.page = page;
        this.totalRows = totalRows;
    }


    @Override
    public boolean hasNext() {
        return totalRows > (page.no() * page.size());
    }

    @Override
    public int no() {
        return page.no();
    }

    @Override
    public int size() {
        return page.size();
    }

    @Override
    public PageType type() {
        return PageType.TOTAL;
    }

    public int totalRows(){
        return this.totalRows;
    }
}
