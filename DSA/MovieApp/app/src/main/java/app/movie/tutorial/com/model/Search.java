package app.movie.tutorial.com.model;

import com.google.gson.annotations.SerializedName;

public class Search
{
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;

    public Search(String posterPath, String title)
    {
        this.posterPath = posterPath;
        this.title = title;
    }

    public String getPosterPath() { return posterPath;  }
    public String getTitle() {  return title;   }

    public void setPosterPath(String posterPath) {  this.posterPath = posterPath;   }
    public void setTitle(String title) {    this.title = title; }

}
