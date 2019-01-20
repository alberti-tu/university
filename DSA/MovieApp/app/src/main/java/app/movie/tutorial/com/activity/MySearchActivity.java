package app.movie.tutorial.com.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.movie.tutorial.com.R;
import app.movie.tutorial.com.adapter.SearchAdapter;
import app.movie.tutorial.com.model.Repos;
import app.movie.tutorial.com.model.Search;
import app.movie.tutorial.com.rest.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MySearchActivity extends AppCompatActivity
{
    private static final String TAG = MySearchActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    private ProgressDialog progressDialog;

    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "8fbbccb0705c22ac5d132e14e78a7b43";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysearch_activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_search_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndGetApiData();
    }

    // This method create an instance of Retrofit
    // set the base url
    public void connectAndGetApiData()
    {

        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando");
        progressDialog.show();

        final MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Bundle datos = getIntent().getExtras();
        String querry = datos.getString("search");
        Call<Repos> call = movieApiService.getSearch(API_KEY, querry);
        call.enqueue(new Callback<Repos>()
        {
            @Override
            public void onResponse(Call<Repos> call, Response<Repos> response)
            {
                progressDialog.dismiss();
                List<Search> movies = response.body().getResults();
                recyclerView.setAdapter(new SearchAdapter(movies, R.layout.mysearch_list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());

                if(movies.size() == 0)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "No hay resultados", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Repos> call, Throwable throwable)
            {
                progressDialog.dismiss();
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
