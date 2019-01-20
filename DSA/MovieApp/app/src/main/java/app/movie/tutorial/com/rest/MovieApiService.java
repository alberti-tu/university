package app.movie.tutorial.com.rest;

import app.movie.tutorial.com.model.Repos;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;
import app.movie.tutorial.com.model.MovieResponse;

public interface MovieApiService
{
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    //  http://api.themoviedb.org/3/search/movie?api_key=8fbbccb0705c22ac5d132e14e78a7b43&query=son+river
    @GET("search/movie")
    Call<Repos> getSearch(@Query("api_key") String apiKey, @Query("query") String queryString);
}
