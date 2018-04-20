package com.gooeyapps.crusteauapp;

public class Products {
    private String id_producte;
    private String nom;
    private String imatge;
    private String informacio;
    private String preu;

    //constructor
    public Products(String id_producte, String nom, String imatge, String informacio, String preu) {
        this.id_producte = id_producte;
        this.nom = nom;
        this.imatge = imatge;
        this.informacio = informacio;
        this.preu = preu;
    }

    //metodes


    public String getId_producte() {
        return id_producte;
    }

    public void setId_producte(String id_producte) {
        this.id_producte = id_producte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getInformacio() {
        return informacio;
    }

    public void setInformacio(String informacio) {
        this.informacio = informacio;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }
}
