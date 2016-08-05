package com.hpp.common.page;

/**
 * Created by huangpingping on 2016/8/5 10:15
 */
public class TotalRowsResultPage implements ResultPage {
    private IPage IPage;

    private int totalRows;

    public TotalRowsResultPage(IPage IPage, int totalRows) {
        this.IPage = IPage;
        this.totalRows = totalRows;
    }


    @Override
    public boolean hasNext() {
        return totalRows > (IPage.no() * IPage.size());
    }

    @Override
    public int no() {
        return IPage.no();
    }

    @Override
    public int size() {
        return IPage.size();
    }

    @Override
    public PageType type() {
        return PageType.TOTAL;
    }

    public int totalRows(){
        return this.totalRows;
    }
}
