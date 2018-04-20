package com.gooeyapps.crusteauapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Productes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Llista
    private List<Products> llista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //llista
        llista = new ArrayList<Products>();
        llista.add(new Products("1","Baguette","baguette","És una barra molt gran","1€"));
        llista.add(new Products("2","Camut integral","camut_integral","És una camut","2€"));
        llista.add(new Products("3","Coca","coca","És una coca","3€"));

        AdapterProductes adapterProductes = new AdapterProductes(this);
        ListView listView = (ListView)findViewById(R.id.listProductes);
        listView.setAdapter(adapterProductes);

    }

    class AdapterProductes extends ArrayAdapter<Products>{

        AppCompatActivity appCompatActivity;
        AdapterProductes(AppCompatActivity context){
            super(context, R.layout.products, llista);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.products,null);

            //Nom del producte
            TextView nom = (TextView)item.findViewById(R.id.nomTxT);
            nom.setText(llista.get(position).getNom());

            //Informació del producte
            TextView informacio = (TextView)item.findViewById(R.id.informacioTxT);
            informacio.setText(llista.get(position).getInformacio());

            //Preu del producte
            TextView preu = (TextView)item.findViewById(R.id.preuTxT);
            preu.setText(llista.get(position).getPreu());

            //IMATGE del producte
            ImageView imageView = (ImageView)item.findViewById(R.id.imatgeProducte);
            int id = getResources().getIdentifier(llista.get(position).getImatge(),"drawable",getPackageName());
            imageView.setImageResource(id);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   inte();
                }
            });

            return item;
        }


    }

    public void inte(){
        Toast.makeText(this, "This is my Toast message!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.productes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_mapa) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_productes) {
            Intent intent = new Intent(this, Productes.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
