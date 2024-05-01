package com.example.epicierbouha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProduitActivity extends AppCompatActivity {

    private static final int CODE_AJOUTER_PRODUIT = 1;
    private static final int RESULT_OK_PRODUIT = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private GridView gridView;
    private ArrayList<Produit> productList;
    private ProduitAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Grocery Store");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // GridView setup
        gridView = findViewById(R.id.gridView);

        Categorie category = getIntent().getParcelableExtra("CATEGORY_OBJECT");

        productList = category.getListeProduit();


        adapter2 = new ProduitAdapter (this, productList);
        gridView.setAdapter(adapter2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grille_produits, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Ajouter_produit) {
            go_To_ajouterProduitActivity();
            return true;
        }

        if (id == android.R.id.home) { // Check if the back button is clicked
            onBackPressed(); // Navigate back when the back button is clicked
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }

    public void go_To_ajouterProduitActivity(){
        Intent intent = new Intent(this, AjouterProduitActivity.class);
        startActivityForResult(intent, CODE_AJOUTER_PRODUIT);
    }


    //------------------------------------------------------------------------------------------------------------------------------

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK_PRODUIT && requestCode == CODE_AJOUTER_PRODUIT) {
            if (data != null) {
                String produitName = data.getStringExtra("PRODUIT_NAME");
                String produitDescription = data.getStringExtra("PRODUIT_DESCRIPTION");
                Bitmap productImage = data.getParcelableExtra("PRODUIT_IMAGE");


                Produit produit = new Produit(produitName, produitDescription, productImage );

                Log.d("ProduitActivity", "New product added: " + produitName + ", " + produitDescription);

                productList.add(produit);
                adapter2.notifyDataSetChanged();

                // Optional: Toast message after successful update
                Toast.makeText(this, "Produit ajouté!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle other activity results (optional)
        }
    }

}