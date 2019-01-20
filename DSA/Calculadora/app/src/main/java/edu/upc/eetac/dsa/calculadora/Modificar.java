package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity
{
    EditText linea;
    Button modificar;
    Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_activity);

        linea = (EditText) findViewById(R.id.line);
        modificar = (Button) findViewById(R.id.btn_modificar);
        borrar = (Button) findViewById(R.id.btn_borrar);

        Bundle data = getIntent().getExtras();
        String op = data.getString("operacion");

        linea.setText(op);  //Mostrar operacion

        //Boton historial
        modificar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        );

        //Boton historial
        borrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = getIntent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        }
        );
    }
}
