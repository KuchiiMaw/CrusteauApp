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


        }


    }

}
