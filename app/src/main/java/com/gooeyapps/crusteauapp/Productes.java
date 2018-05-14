package com.gooeyapps.crusteauapp;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
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
    public double total;
    String euros="€";
    TextView preufinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No permetre girar el Layout

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Typeface exo = Typeface.createFromAsset(getAssets(), "fonts/Exo2-Regular.otf"); //Es crea el typeface (font)

        TextView txtPreuTotal = (TextView)findViewById(R.id.txtPreuTotal);

        txtPreuTotal.setTypeface(exo); //S'assigna el typeface als TextView

        //Preu final
        try {
            preufinal = (TextView)findViewById(R.id.txtPreuTotal);
            Log.d("1","El total es:  "+total);
            String tot = new Double(total).toString();
            preufinal.setText("Total: "+tot+euros);

        }catch (Exception e){
            e.printStackTrace();
        }

        //Llista de productes
        llista = new ArrayList<Products>();
        llista.add(new Products("1",this.getString(R.string.baguette),"baguette","És una barra molt gran.","1.60"));
        llista.add(new Products("2",this.getString(R.string.camut_integral),"camut_integral","És una camut.","3.50"));
        llista.add(new Products("3",this.getString(R.string.coca),"coca","És una coca.","1.50"));
        llista.add(new Products("4",this.getString(R.string.croissant),"croissant","És un croissant.","1.50"));
        llista.add(new Products("5",this.getString(R.string.espelta_integral),"espelta_integral","És un pa d'espelta integral.","3.50"));
        llista.add(new Products("6",this.getString(R.string.fajol),"fajol","És un fajol.","4.90"));
        llista.add(new Products("7",this.getString(R.string.magdalena),"magdalena","És una magdalena.","1.50"));
        llista.add(new Products("8",this.getString(R.string.pa_moresc),"pa_moresc","És pa de moresc.","4.50"));
        llista.add(new Products("9",this.getString(R.string.pa_motlle),"pa_motlle","És pa de motlle.","3.50"));
        llista.add(new Products("10",this.getString(R.string.pages),"pages","És un pa de pagès.","5"));
        llista.add(new Products("11",this.getString(R.string.rustic),"rustic","És un pa rústic.","2.50"));
        llista.add(new Products("12",this.getString(R.string.segol_integral),"segol_integral","És pa de sègol integral","3.50"));
        llista.add(new Products("13",this.getString(R.string.sis_cereals),"sis_cereals","És pa 6 cereals.","3.50"));

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
            numeroProductes.setText(llista.get(position).getQuantitat());


            //Boto Resta
            Button resta = (Button) item.findViewById(R.id.btnResta);
            resta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero;
                    try{
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
                        numero = Integer.parseInt(String.valueOf(llista.get(position).getQuantitat()));

                        if (numero<100) {
                            numero++;
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

            //Preu del producte
            TextView preu = (TextView)item.findViewById(R.id.preuTxT);
            preu.setText(llista.get(position).getPreu()+euros);

            //Imatge del producte
            final ImageView imageView = (ImageView)item.findViewById(R.id.imatgeProducte);
            final int id = getResources().getIdentifier(llista.get(position).getImatge(),"drawable",getPackageName());
            imageView.setImageResource(id);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   mostrarDades(position);
                }
            });

            //Es creen els typeface (font)
            Typeface exoCond = Typeface.createFromAsset(getAssets(), "fonts/Exo2-RegularCondensed.otf");
            Typeface exoCondBold = Typeface.createFromAsset(getAssets(), "fonts/Exo2-BoldCondensed.otf");

            TextView nomTxt = (TextView)item.findViewById(R.id.nomTxT);
            TextView preuTextTxt = (TextView)item.findViewById(R.id.preuTextTxT);
            TextView preuTxt = (TextView)item.findViewById(R.id.preuTxT);
            TextView numProductesTextTxt = (TextView)item.findViewById(R.id.numProductesTextTxT);
            TextView numProductesTxt = (TextView)item.findViewById(R.id.numProductesTxT);

            nomTxt.setTypeface(exoCondBold);//S'assigna el typeface en negreta als TextView dels títols
            preuTextTxt.setTypeface(exoCondBold);
            numProductesTextTxt.setTypeface(exoCondBold);

            preuTxt.setTypeface(exoCond);//S'assigna el typeface regular als TextView dels valors
            numProductesTxt.setTypeface(exoCond);




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
        Log.d("1","El total es:  "+total);

        total=0;
       // double quantitat=0;
        //double preu=0;

        for (int i=0;i<llista.size();i++){
            //preu = Double.parseDouble(llista.get(i).getPreu());
            //quantitat = Double.parseDouble(llista.get(i).getQuantitat());
            //total = total + (quantitat * preu);
            total = total + Double.parseDouble(llista.get(i).getPreu())* Double.parseDouble(llista.get(i).getQuantitat());
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
        }else if (id == R.id.nav_telf) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:684011253"));
            startActivity(intent);
        }else if(id == R.id.information){
            Intent intent = new Intent(this, Information.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
