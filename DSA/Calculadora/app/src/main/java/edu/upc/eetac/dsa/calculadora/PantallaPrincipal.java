package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipal extends AppCompatActivity
{
    EditText num1;
    EditText num2;
    Spinner operacion;
    Button resultado;
    Button clear;
    Button history;
    EditText result;
    Intent intent;
    ArrayList<String> list;
    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        num1   = (EditText)findViewById(R.id.num1);
        num2   = (EditText)findViewById(R.id.num2);
        operacion = (Spinner) findViewById(R.id.operacion);
        resultado = (Button) findViewById(R.id.result);
        clear = (Button) findViewById(R.id.clear);
        result = (EditText) findViewById(R.id.resultado);
        history = (Button) findViewById(R.id.historial);

        list = new ArrayList<String>();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operacion, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        operacion.setAdapter(adapter);

        //Boton resultado
        resultado.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int res = 0, numero1, numero2;

                    //Comprobación de los valores de entrada
                    try {   numero1 = Integer.parseInt(num1.getText().toString());  }
                    catch(NumberFormatException nfe)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Número 1 no vàlid", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    try {   numero2 = Integer.parseInt(num2.getText().toString());  }
                    catch(NumberFormatException nfe)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Número 2 no vàlid", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                    //Realizar operación del Spinner
                    if  (operacion.getSelectedItem().equals("SUMA (+)"))
                    {
                        res = numero1 + numero2;
                        op = String.valueOf(numero1)+" + "+String.valueOf(numero2)+" = "+String.valueOf(res);
                    }
                    else if (operacion.getSelectedItem().equals("RESTA (-)"))
                    {
                        res = numero1 - numero2;
                        op = String.valueOf(numero1)+" - "+String.valueOf(numero2)+" = "+String.valueOf(res);
                    }
                    else if (operacion.getSelectedItem().equals("MULTIPLICACIÓ (x)"))
                    {
                        res = numero1 * numero2;
                        op = String.valueOf(numero1)+" x "+String.valueOf(numero2)+" = "+String.valueOf(res);
                    }
                    else if (operacion.getSelectedItem().equals("DIVISIÓ (/)"))
                    {
                        res = numero1 / numero2;
                        op = String.valueOf(numero1)+" / "+String.valueOf(numero2)+" = "+String.valueOf(res);
                    }

                    list.add(op);

                    String resultado = String.valueOf(res);    //String del resultado
                    result.setText(resultado);  //Mostrar resultado
                }
            }
        );

        //Boton limpiar
        clear.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    num1.setText("");
                    num2.setText("");
                    result.setText("");
                }
            }
        );

        //Boton historial
        history.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    intent = new Intent(PantallaPrincipal.this, Historial.class);
                    intent.putExtra("list", list);
                    startActivityForResult(intent, 100);
                }
            }
        );
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        num1.setText("");
        num2.setText("");
        result.setText("");

        //Han cerrado el historial
        if((requestCode == 100) && (resultCode == Activity.RESULT_OK))
        {
            Bundle datos = getIntent().getExtras();
            list = datos.getStringArrayList("list");
        }
    }
}