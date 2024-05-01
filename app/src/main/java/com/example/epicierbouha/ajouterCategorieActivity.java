package com.example.epicierbouha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ajouterCategorieActivity extends AppCompatActivity {

    private int CODE_AJOUTER ;
    private Button ajouter_btn;
    private Button annuler_btn;
    private EditText Libelle ;
    private EditText Description;
    private ImageView changeimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);

        Toolbar toolbar = findViewById(R.id.toolbar_ajouterCategorie);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("grocery Store");

        ajouter_btn  = findViewById(R.id.ajouter_button);
        annuler_btn  = findViewById(R.id.annuler_button);
        Libelle      = findViewById(R.id.Libelle_editText);
        Description  = findViewById(R.id.Description_editText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ajouter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter_Categorie();
            }
        });


        annuler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annuler_ajouterCategorie();
            }
        });

    }

    private void ajouter_Categorie(){

        String categoryName = Libelle.getText().toString();
        String categoryDescription = Description.getText().toString();



        Intent intent = new Intent();
        intent.putExtra("CATEGORY_NAME", categoryName);
        intent.putExtra("CATEGORY_DESCRIPTION", categoryDescription);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void annuler_ajouterCategorie(){
        Libelle.setText("");
        Description.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // Check if the back button is clicked
            onBackPressed(); // Navigate back when the back button is clicked
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}