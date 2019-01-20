package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Confirmacion extends AppCompatActivity
{
    Button btn_si;
    Button btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_activity);

        btn_si = (Button) findViewById(R.id.button_si);
        btn_no = (Button) findViewById(R.id.button_no);

        //Boton Sí
        btn_si.setOnClickListener(new View.OnClickListener()
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

        //Boton No
        btn_no.setOnClickListener(new View.OnClickListener()
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
