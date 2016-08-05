package com.hpp.common.page;

/**
 * Created by huangpingping on 2016/8/5 9:38
 */
public interface IPage {
    int no();

    int size();

    PageType type();

    enum PageType{
        TOTAL,
        ADD_ONE
    }
}
