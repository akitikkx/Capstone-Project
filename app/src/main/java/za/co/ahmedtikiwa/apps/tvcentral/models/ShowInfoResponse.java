package za.co.ahmedtikiwa.apps.tvcentral.models;

import java.util.ArrayList;

public class ShowInfoResponse {

    private String backdrop_path;
    private ArrayList<ShowCreatedBy> created_by = null;
    private ArrayList<Integer> episode_run_time = null;
    private String first_air_date;
    private ArrayList<ShowGenre> genres = null;
    private String homepage;
    private Integer id;
    private Boolean in_production;
    private ArrayList<String> languages = null;
    private String last_air_date;
    private String name;
    private ArrayList<ShowNetwork> networks = null;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private ArrayList<String> origin_country = null;
    private String original_language;
    private String original_name;
    private String overview;
    private Double popularity;
    private String poster_path;
    private ArrayList<Object> production_companies = null;
    private ArrayList<ShowSeason> seasons = null;
    private String status;
    private String type;
    private Double vote_average;
    private Integer vote_count;

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public ArrayList<ShowCreatedBy> getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(ArrayList<ShowCreatedBy> createdBy) {
        this.created_by = createdBy;
    }

    public ArrayList<Integer> getEpisodeRunTime() {
        return episode_run_time;
    }

    public void setEpisodeRunTime(ArrayList<Integer> episodeRunTime) {
        this.episode_run_time = episodeRunTime;
    }

    public String getFirstAirDate() {
        return first_air_date;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.first_air_date = firstAirDate;
    }

    public ArrayList<ShowGenre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<ShowGenre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInProduction() {
        return in_production;
    }

    public void setInProduction(Boolean inProduction) {
        this.in_production = inProduction;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return last_air_date;
    }

    public void setLastAirDate(String lastAirDate) {
        this.last_air_date = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ShowNetwork> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<ShowNetwork> networks) {
        this.networks = networks;
    }

    public Integer getNumberOfEpisodes() {
        return number_of_episodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.number_of_episodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return number_of_seasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.number_of_seasons = numberOfSeasons;
    }

    public ArrayList<String> getOriginCountry() {
        return origin_country;
    }

    public void setOriginCountry(ArrayList<String> originCountry) {
        this.origin_country = originCountry;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginalName() {
        return original_name;
    }

    public void setOriginalName(String originalName) {
        this.original_name = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public ArrayList<Object> getProductionCompanies() {
        return production_companies;
    }

    public void setProductionCompanies(ArrayList<Object> productionCompanies) {
        this.production_companies = productionCompanies;
    }

    public ArrayList<ShowSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<ShowSeason> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(Double voteAverage) {
        this.vote_average = voteAverage;
    }

    public Integer getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(Integer voteCount) {
        this.vote_count = voteCount;
    }
}