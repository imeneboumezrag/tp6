package com.example.epicierbouha;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int CODE_AJOUTER = 1;
    private ListView listCategoriesView;
    private Toolbar toolbar;
    private CategorieAdapter adapter;
    ArrayList<Categorie> listCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCategoriesView = findViewById(R.id.listView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ArrayList<Produit> listeProduitFarine = new ArrayList<>();
        listeProduitFarine.add(new Produit("Farine Sim 5KG", "", null));
        listeProduitFarine.add(new Produit("Farine Sosimie 5KG", "", null));

        ArrayList<Produit> listeProduitHuile = new ArrayList<>();
        listeProduitHuile.add(new Produit("Huile 1l", "", null));
        listeProduitHuile.add(new Produit("Huile 5l afia", "", null));

        listCategories.add(new Categorie("Farine", "", listeProduitFarine));
        listCategories.add(new Categorie("Fromage", "", null));
        listCategories.add(new Categorie("Huile", "", listeProduitHuile));
        listCategories.add(new Categorie("Semoule", "", null));

        adapter = new CategorieAdapter(this, listCategories);
        listCategoriesView.setAdapter(adapter);
    }



    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Ajouter_categorie) {
            go_To_ajouterCategorieActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void go_To_ajouterCategorieActivity(){
        Intent intent = new Intent(this, ajouterCategorieActivity.class);
        startActivityForResult(intent, CODE_AJOUTER);
    }

    //------------------------------------------------------------------------------------------------------------------------------

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CODE_AJOUTER) {
            if (data != null) {
                String categoryName = data.getStringExtra("CATEGORY_NAME");
                String categoryDescription = data.getStringExtra("CATEGORY_DESCRIPTION");

                Categorie categorie = new Categorie(categoryName, categoryDescription);
                Toast.makeText(this, "Data received: "  , Toast.LENGTH_SHORT).show();

                listCategories.add(categorie);
                adapter.notifyDataSetChanged();


            } else {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
            }
        }
    }

}