package com.elfec.sice.model.web_services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by drodriguez on 08/07/2016.
 * model for pagination result of ws
 */
public class Pagination {
    private int page;
    private int current;
    private int count;
    @SerializedName("perPage")
    private int perPage;
    @SerializedName("prevPage")
    private boolean prevPage;
    @SerializedName("nextPage")
    private boolean nextPage;

    //region getter setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public boolean hasPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    public boolean hasNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }
    //endregion
}
