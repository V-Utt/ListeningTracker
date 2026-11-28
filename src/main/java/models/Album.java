package models;

public class Album {
    private String id;
    private String name;
    private String artist;
    private String year;
    private int rating;
    private String userStat;

    public Album(String id, String name, String artist, String year) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.rating = 0;
        this.userStat = "DEFAULT";
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getYear() {
        return this.year;
    }

    public int getRating() {
        return this.rating;
    }

    public String getUserStat() {
        return this.userStat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            this.rating = rating;
        }
    }

    public void setUserStat(String userStat) {
        this.userStat = userStat;
    }

}
