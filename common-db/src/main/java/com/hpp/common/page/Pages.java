package com.hpp.common.page;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by huangpingping on 2016/8/5 13:45
 */
public class Pages {

    public static <E> PageList<E> createPageList(Page page, List<E> elements, int count) {
        ResultPage resultPage = null;
        List<E> list = null;
        if (IPage.PageType.ADD_ONE.equals(page.type())) {
            resultPage = new AddOneRowsResultPage(page, count);
            if (!CollectionUtils.isEmpty(elements)) {
                list = elements.subList(0, elements.size() - 1);
            }
        } else if (IPage.PageType.TOTAL.equals(page.type())) {
            resultPage = new TotalRowsResultPage(page, count);
            list = elements;
        }

        return new PageList<E>(list, resultPage);
    }
}
