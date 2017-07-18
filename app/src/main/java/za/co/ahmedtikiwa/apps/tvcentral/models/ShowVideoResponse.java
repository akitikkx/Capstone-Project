package za.co.ahmedtikiwa.apps.tvcentral.models;


import java.util.ArrayList;
import java.util.List;

public class ShowVideoResponse {

    private Integer id;
    private ArrayList<ShowVideo> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ShowVideo> getResults() {
        return results;
    }

    public void setResults(ArrayList<ShowVideo> results) {
        this.results = results;
    }

}