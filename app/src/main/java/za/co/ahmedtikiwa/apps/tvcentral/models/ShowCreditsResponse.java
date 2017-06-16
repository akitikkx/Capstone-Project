package za.co.ahmedtikiwa.apps.tvcentral.models;

import java.util.ArrayList;
import java.util.List;

public class ShowCreditsResponse {

    private ArrayList<ShowCast> cast = null;
    private List<Object> crew = null;
    private Integer id;

    public ArrayList<ShowCast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<ShowCast> cast) {
        this.cast = cast;
    }

    public List<Object> getCrew() {
        return crew;
    }

    public void setCrew(List<Object> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}