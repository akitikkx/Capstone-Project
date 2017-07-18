package za.co.ahmedtikiwa.apps.tvcentral.models;

public class ShowSeason {

    private String air_date;
    private Integer episode_count;
    private Integer id;
    private Object poster_path;
    private Integer season_number;

    public String getAirDate() {
        return air_date;
    }

    public void setAirDate(String airDate) {
        this.air_date = airDate;
    }

    public Integer getEpisodeCount() {
        return episode_count;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episode_count = episodeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(Object posterPath) {
        this.poster_path = posterPath;
    }

    public Integer getSeasonNumber() {
        return season_number;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.season_number = seasonNumber;
    }

}
