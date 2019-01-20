package dsa.upc.edu.minim2.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import dsa.upc.edu.minim2.adapter.AdapterProductos;
import dsa.upc.edu.minim2.object.Producto;
import dsa.upc.edu.minim2.R;
import dsa.upc.edu.minim2.rest.APIservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListProductActivity extends AppCompatActivity
{
    private String BASE_URL = "http://10.192.119.86:8080/myapp/";
    private ProgressDialog progressDialog;
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precios_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getListaPrecios();
    }


    public void getListaPrecios()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Obteniendo productos");
        progressDialog.show();

        APIservice apiService = retrofit.create(APIservice.class);

        Call<List<Producto>> getPrecios = apiService.getPrecio();
        getPrecios.enqueue(new Callback<List<Producto>>()
        {
            @Override
            public void onResponse(Call<List<Producto>> getPrecios, Response<List<Producto>> response)
            {
                progressDialog.dismiss();

                List<Producto> listProducto = response.body();

                recyclerView.setAdapter(new AdapterProductos(listProducto, R.layout.item_producto, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Producto>> login, Throwable t)
            {
                progressDialog.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}