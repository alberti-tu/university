package app.movie.tutorial.com.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.movie.tutorial.com.R;

public class SearchActivity extends AppCompatActivity
{
    EditText box;
    Button button;
    Button top;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        box = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        top = (Button) findViewById(R.id.button_top);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(box.getText().toString().isEmpty())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Campo de búsqueda incompleto", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else
                {
                    //envia los parametros de la búsqueda
                    intent = new Intent(SearchActivity.this, MySearchActivity.class);
                    intent.putExtra("search", box.getText().toString());
                    startActivity(intent);
                }
            }
        });

        top.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
