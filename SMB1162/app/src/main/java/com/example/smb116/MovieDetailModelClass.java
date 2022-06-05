package com.example.smb116;

public class MovieDetailModelClass {

    String id;
    String name;
    String overview;
    String img;

    public MovieDetailModelClass() {
    }

    public MovieDetailModelClass(String id, String name, String overview, String img) {

        this.id = id;
        this.name = name;
        this.overview = overview;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
