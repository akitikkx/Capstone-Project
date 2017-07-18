package za.co.ahmedtikiwa.apps.tvcentral.models;

public class ShowCreatedBy {

    private Integer id;
    private String name;
    private Object profile_path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProfilePath() {
        return profile_path;
    }

    public void setProfilePath(Object profilePath) {
        this.profile_path = profilePath;
    }

}