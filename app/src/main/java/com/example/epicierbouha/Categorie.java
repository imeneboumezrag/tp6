package com.example.epicierbouha;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Categorie implements Parcelable {
    private String nom;
    private String description;
    private ArrayList<Produit> listeProduit;

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.listeProduit = new ArrayList<>();
    }

    public Categorie(String nom) {
        this.nom = nom;
        this.description = "";
        this.listeProduit = new ArrayList<>();
    }

    public Categorie(String nom, String description, ArrayList<Produit> listeProduit) {
        this.nom = nom;
        this.description = description;
        this.listeProduit = listeProduit;
    }

    // Getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Produit> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(ArrayList<Produit> listeProduit) {
        this.listeProduit = listeProduit;
    }

    public void ajouterProduit(Produit produit) {
        listeProduit.add(produit);
    }

    public void supprimerProduit(Produit produit) {
        listeProduit.remove(produit);
    }

    // Parcelable implementation
    protected Categorie(Parcel in) {
        nom = in.readString();
        description = in.readString();
        listeProduit = in.createTypedArrayList(Produit.CREATOR);
    }

    public static final Creator<Categorie> CREATOR = new Creator<Categorie>() {
        @Override
        public Categorie createFromParcel(Parcel in) {
            return new Categorie(in);
        }

        @Override
        public Categorie[] newArray(int size) {
            return new Categorie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeTypedList(listeProduit);
    }
}
