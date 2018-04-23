package com.gooeyapps.crusteauapp;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {
    ImageView imatge;
    TextView descripcio;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        mostrar(position);

         imatge = (ImageView)findViewById(R.id.imgImatge);
         descripcio = (TextView)findViewById(R.id.descripcio);

    }


    public void mostrar(int position){
        Toast.makeText(this, "This is my Toast message! "+position ,
                Toast.LENGTH_LONG).show();

        switch (position){
            case 0:
                Toast.makeText(this, "This is my Toast message! "+position ,
                        Toast.LENGTH_LONG).show();

                imatge.setImageDrawable(getResources().getDrawable(R.drawable.baguette));
                //imatge.setImageResource(R.drawable.baguette);
                //imatge.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baguette));
                /*descripcio.setText("La baguette a la Jose de \n" +
                        "Crusteau és molt bona.");*/

            case 1:
                //imatge.setImageResource(R.drawable.camut_integral);
                descripcio.setText("El camut integral a la Jose de \n" +
                        "Crusteau és molt bona.");

            case 2:
                //imatge.setImageResource(R.drawable.coca);
                descripcio.setText("La coca a la Jose de \n" +
                        "Crusteau és molt bona.");
        }


    }

}
