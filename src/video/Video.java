package video;

import fileio.ShowInput;

import java.util.ArrayList;

public class Video {
    final private String title;
    final private int year;
    final private ArrayList<String> cast;
    final private ArrayList<String> genres;
    private int favorite = 0;
    private int views = 0;

    public Video(final ShowInput showInput) {
        title = showInput.getTitle();
        year = showInput.getYear();
        cast = showInput.getCast();
        genres = showInput.getGenres();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(final int favorite) {
        this.favorite = favorite;
    }

    public int getViews() {
        return views;
    }

    public void setViews(final int views) {
        this.views = views;
    }

}
