package com.gooeyapps.crusteauapp;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {
    public ImageView imageView;
    public TextView descripcio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        mostrar(position);

    }


    public void mostrar(int position){
        descripcio = (TextView)findViewById(R.id.descripcio);
        imageView = (ImageView)findViewById(R.id.foto);

        switch (position){
            case 0:
                imageView.setImageResource(R.drawable.baguette);
                descripcio.setText("");
                break;
            case 1:
                imageView.setImageResource(R.drawable.camut_integral);
                descripcio.setText("El camut integral a la Jaumee de ");

                break;

            case 2:
                imageView.setImageResource(R.drawable.coca);
                descripcio.setText("La coca integral a la Jose de ");
                break;

            case 3:
                imageView.setImageResource(R.drawable.croissant);
                descripcio.setText("Croassant");
                break;

            case 4:
                imageView.setImageResource(R.drawable.espelta_integral);
                descripcio.setText("Pa d'espelta");
                break;

            case 5:
                imageView.setImageResource(R.drawable.fajol);
                descripcio.setText("Fajol");
                break;

            case 6:
                imageView.setImageResource(R.drawable.magdalena);
                descripcio.setText("Magdalena");
                break;

            case 7:
                imageView.setImageResource(R.drawable.pa_moresc);
                descripcio.setText("Pa moresc");
                break;

            case 8:
                imageView.setImageResource(R.drawable.pa_motlle);
                descripcio.setText("Pa de motlle");
                break;

            case 9:
                imageView.setImageResource(R.drawable.pages);
                descripcio.setText("Pa de pagès");
                break;

            case 10:
                imageView.setImageResource(R.drawable.rustic);
                descripcio.setText("Pa rústic");
                break;

            case 11:
                imageView.setImageResource(R.drawable.segol_integral);
                descripcio.setText("Pa de sègol integral");
                break;

            case 12:
                imageView.setImageResource(R.drawable.sis_cereals);
                descripcio.setText("Pa 6 cereals");
                break;
        }


    }

}
