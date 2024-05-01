package com.example.epicierbouha;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import android.Manifest;

public class AjouterProduitActivity extends AppCompatActivity {

    private static final int RESULT_OK_PRODUIT = 1 ;
    private static final int REQUEST_CAMERA_PERMISSION = 100;


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private EditText editTextName, editTextDescription;
    private ImageView imageView;
    private androidx.appcompat.widget.Toolbar toolbar2;
    private Button buttonAdd, buttonCancel;
    private Bitmap capturedImageBitmap;
    private ImageButton changeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        imageView = findViewById(R.id.imageView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);
        changeimage = findViewById(R.id.changePhotoButton);

        toolbar2 = findViewById(R.id.toolbar_ajouter_produit);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Grocery Store");



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add button click
                ajouterProduit();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cancel button click
                annulerAjout();
            }
        });

        changeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void ajouterProduit() {

        String productName = editTextName.getText().toString();
        String productDescription = editTextDescription.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("PRODUIT_NAME", productName);
        intent.putExtra("PRODUIT_DESCRIPTION", productDescription);
        intent.putExtra("PRODUIT_IMAGE", capturedImageBitmap);

        setResult(RESULT_OK_PRODUIT, intent);
        finish();
    }


    private void annulerAjout() {

        editTextName.setText("");
        editTextDescription.setText("");
        finish();
    }

    private void dispatchTakePictureIntent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");
        String[] options = {"Take Photo", "Choose from Gallery"};
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA_PERMISSION);
           System.out.println("permession granted!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:





                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            System.out.println("camera avail");
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                        }
                        else {
                            System.out.println("camera not avail");
                        }
                        break;
                    case 1:
                        // Choose from Gallery
                        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        if (pickPhotoIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);

                        }
                        break;
                }
            }
        });
        builder.show();
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


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Now you have the captured image bitmap, you can use it as needed
                imageView.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    // Now you have the selected image bitmap, you can use it as needed
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
