package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial extends AppCompatActivity
{
    Button close;
    Button borrar;
    ListView list;
    Intent intent;
    String eliminar;
    ArrayList<String> operaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        close = (Button) findViewById(R.id.close);
        borrar = (Button) findViewById(R.id.borrar);
        list = (ListView) findViewById(R.id.list);

        //Recibe el historial
        Bundle data = getIntent().getExtras();
        operaciones = data.getStringArrayList("list");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, operaciones);
        list.setAdapter(adaptador);

        //Boton close
        close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        }
        );

        //Boton borrar
        borrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Historial.this, Confirmacion.class);
                startActivityForResult(intent, 101);
            }
        }
        );

        //Click en lista
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                eliminar = ((TextView) view).getText().toString();
                intent = new Intent(Historial.this, Modificar.class);
                intent.putExtra("operacion", eliminar);
                startActivityForResult(intent, 102);
            }
        }
        );
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        getIntent().putStringArrayListExtra("list", operaciones);
        setResult(RESULT_OK, getIntent());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Eliminar Historial
        if((requestCode == 101) && (resultCode == Activity.RESULT_OK))
        {
            operaciones = new ArrayList<String>();
            getIntent().putStringArrayListExtra("list", operaciones);
            list = (ListView)findViewById(R.id.list);
            list.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, operaciones));
        }

        //Modificar operacion
        if((requestCode == 102) && (resultCode == Activity.RESULT_OK))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Operació no disponible", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Eliminar operacion
        if((requestCode == 102) && (resultCode == Activity.RESULT_CANCELED))
        {
            Bundle bundle = data.getExtras();
            int index = bundle.getInt("list");
            operaciones.remove(index);
            list = (ListView)findViewById(R.id.list);
            list.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, operaciones));
        }
    }
}