package com.hpp.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by huangpingping on 2016/8/5 13:37
 */
public class PageList<E> extends ArrayList<E> implements Serializable {
    private static final long serialVersionUID = 1412759446332294208L;
    private ResultPage resultPage;

    public PageList() {
    }

    public PageList(Collection<? extends E> c) {
        super(c);
    }

    public PageList(Collection<? extends E> c, ResultPage p) {
        super(c);
        this.resultPage = p;
    }

    public PageList(ResultPage p) {
        this.resultPage = p;
    }

    public ResultPage getPaginator() {
        return this.resultPage;
    }
}
