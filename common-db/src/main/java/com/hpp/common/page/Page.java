package com.hpp.common.page;

/**
 * Created by huangpingping on 2016/8/5 10:31
 */
public class Page implements IPage {
    public final static int DEFAULT_SIZE = 10;

    private Integer no;

    private Integer size;

    private PageType pageType;

    public Page(Integer no, Integer size, PageType pageType) {
        this.no = no;
        this.size = size;
        this.pageType = pageType;
    }

    @Override
    public int no() {
        return this.no == null ? 1 : this.no;
    }

    @Override
    public int size() {
        return this.size == null ? DEFAULT_SIZE : this.size;
    }

    @Override
    public PageType type() {
        return pageType;
    }
}
