package video;


import fileio.ShowInput;

import java.util.ArrayList;

public class Video {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;
    private int favorite = 0;
    private int views = 0;

    public Video(final ShowInput showInput) {
        title = showInput.getTitle();
        year = showInput.getYear();
        cast = showInput.getCast();
        genres = showInput.getGenres();
    }

    /**
     * get the title of the video
     * @return String - title of the video
     */
    public String getTitle() {
        return title;
    }

    /**
     * get the year the video was released
     * @return int - year of the video
     */
    public int getYear() {
        return year;
    }

    /**
     * get the cast of the video
     * @return ArrayList - cast of the video
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * get the genres of the video
     * @return ArrayList - genres of the video
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * get the number of times the video appears in users' favorite list
     * @return int - number of times the video appears in the users' favorite list
     */
    public int getFavorite() {
        return favorite;
    }

    /**
     * set the number of times the video appears in the users' favorite list
     * used to increment the times the video has been added to the list
     * @param favorite int - the number of times the video appears in the favorite
     *                 list
     */
    public void setFavorite(final int favorite) {
        this.favorite = favorite;
    }

    /**
     * get the number of time the video has been viewed
     * @return int - number of the times the video has been viewed
     */
    public int getViews() {
        return views;
    }
    /**
     * set the number of times the video has been viewed
     * used to increment the times the video has been viewed
     * @param views int - number of times the video has been viewed
     */
    public void setViews(final int views) {
        this.views = views;
    }

}
