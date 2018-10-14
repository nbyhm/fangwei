package com.dowell.utils.page;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author nanbo
 * @description PageList
 * @create 2018-05-23
 **/
public class PageList {

    private Paginator paginator;
    private List list;

    public PageList(List list) {

        if(list instanceof Page){
            paginator=new Paginator((Page)list);
            this.list=list;
        }
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
