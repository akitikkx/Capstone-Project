package za.co.ahmedtikiwa.apps.tvcentral.models;

import java.util.ArrayList;

public class ShowsResponse {

    private Integer page;
    private ArrayList<Show> results = null;
    private Integer total_results;
    private Integer total_pages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Show> getResults() {
        return results;
    }

    public void setResults(ArrayList<Show> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return total_results;
    }

    public void setTotalResults(Integer totalResults) {
        this.total_results = totalResults;
    }

    public Integer getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(Integer totalPages) {
        this.total_pages = totalPages;
    }
}