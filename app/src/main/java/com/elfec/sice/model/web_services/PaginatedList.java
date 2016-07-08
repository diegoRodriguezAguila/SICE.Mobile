package com.elfec.sice.model.web_services;

import java.util.List;

/**
 * Created by drodriguez on 08/07/2016.
 * list which has pagination
 */
public class PaginatedList<T> {
    private List<T> data;
    private Pagination pagination;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
