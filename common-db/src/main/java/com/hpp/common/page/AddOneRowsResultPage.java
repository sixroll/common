package com.hpp.common.page;

/**
 * Created by huangpingping on 2016/8/5 10:20
 * 多一个元素返回，
 */
public class AddOneRowsResultPage implements ResultPage {

    private IPage page;

    private int addOneRows;

    public AddOneRowsResultPage(IPage page, int addOneRows) {
        this.page = page;
        this.addOneRows = addOneRows;
    }

    @Override
    public boolean hasNext() {
        return addOneRows > page.size();
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
        return PageType.ADD_ONE;
    }
}
