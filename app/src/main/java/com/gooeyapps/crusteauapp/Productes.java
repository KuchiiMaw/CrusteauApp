package com.gooeyapps.crusteauapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Productes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Llista
    public List<Products> llista;
    public double total=0;
    String euros="€";

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

        //Llista de productes
        llista = new ArrayList<Products>();
        llista.add(new Products("1","Baguette","baguette","És una barra molt gran.","1"));
        llista.add(new Products("2","Camut integral","camut_integral","És una camut.","2"));
        llista.add(new Products("3","Coca","coca","És una coca.","3"));
        llista.add(new Products("4","Croissant","croissant","És un croissant.","4"));
        llista.add(new Products("5","Pa d'espelta integral","espelta_integral","És un pa d'espelta integral.","5"));
        llista.add(new Products("6","Fajol","fajol","És un fajol.","6"));
        llista.add(new Products("7","Magdalena","magdalena","És una magdalena.","7"));
        llista.add(new Products("8","Pa de moresc","pa_moresc","És pa de moresc.","8"));
        llista.add(new Products("9","Pa de motlle","pa_motlle","És pa de motlle.","9"));
        llista.add(new Products("10","Pa de pagès","pages","És un pa de pagès.","10"));
        llista.add(new Products("11","Pa rústic","rustic","És un pa rústic.","11"));
        llista.add(new Products("12","Pa de sègol integral","segol_integral","És pa de sègol integral","12"));
        llista.add(new Products("13","Pa 6 cereals","sis_cereals","És pa 6 cereals.","1" +
                "3"));

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

        public View getView(final int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.products,null);

            //Nom del producte
            TextView nom = (TextView)item.findViewById(R.id.nomTxT);
            nom.setText(llista.get(position).getNom());

            //Mostra els productes triats.
            final TextView numeroProductes = (TextView)item.findViewById(R.id.numProductesTxT);
            numeroProductes.setText("0");

            //Preu final
            try {
                TextView preufinal = (TextView)findViewById(R.id.txtPreuTotal);
                Log.d("1","El total es:  "+total);
                String tot = new Double(total).toString();
                preufinal.setText("Total: "+tot);

            }catch (Exception e){
                e.printStackTrace();
            }


            //Boto Resta
            Button resta = (Button) item.findViewById(R.id.btnResta);
            resta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        int numero;
                        numero = Integer.parseInt(String.valueOf(llista.get(position).getQuantitat()));

                        if (numero>0) {
                            numero--;
                            String resultat = String.valueOf(Integer.valueOf(numero));
                            llista.get(position).setQuantitat(resultat);
                            numeroProductes.setText(llista.get(position).getQuantitat());

                            //calcular preu final
                            calcularPreu();

                        }


                    }catch (Exception e){
                    e.printStackTrace();
                    }

                }
            });


            //Boto Sumar
            Button sumar = (Button) item.findViewById(R.id.btnSuma);
            sumar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero;
                    try {
                        Log.d("1","LA variable te "+llista.get(position).getQuantitat());
                        numero = Integer.parseInt(String.valueOf(llista.get(position).getQuantitat()));
                        numero++;
                        String resultat = String.valueOf(Integer.valueOf(numero));
                        llista.get(position).setQuantitat(resultat);
                        numeroProductes.setText(llista.get(position).getQuantitat());

                        //calcular preu final
                        calcularPreu();




                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
            });

            //Preu del producte
            TextView preu = (TextView)item.findViewById(R.id.preuTxT);
            preu.setText(llista.get(position).getPreu()+euros);

            //IMATGE del producte
            final ImageView imageView = (ImageView)item.findViewById(R.id.imatgeProducte);
            final int id = getResources().getIdentifier(llista.get(position).getImatge(),"drawable",getPackageName());
            imageView.setImageResource(id);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   mostrarDades(position);
                }
            });




            return item;
        }


    }



    //Mètode per saber quina foto s'ha clicat i ens portara a un layout mostrant la informació de forma més visual.
    public void mostrarDades(int position){
        /*Toast.makeText(this, "This is my Toast message! "+position,
                Toast.LENGTH_LONG).show();*/

        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);

    }

    //Mètode que calcula el preu final a partir de la quantitat demanada per cada producte
    public void calcularPreu(){
        TextView preufinal = (TextView)findViewById(R.id.txtPreuTotal);
        Log.d("1","El total es:  "+total);

        double total=0;
        double quantitat=0;
        double preu=0;

        for (int i=0;i<llista.size();i++){
            preu = Double.parseDouble(llista.get(i).getPreu());
            quantitat = Double.parseDouble(llista.get(i).getQuantitat());
            total = total + (quantitat * preu);
        }

        String tot = new Double(total).toString();
        preufinal.setText("Total: "+tot+euros);
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
