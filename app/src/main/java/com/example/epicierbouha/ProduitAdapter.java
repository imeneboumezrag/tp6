package com.example.epicierbouha;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ProduitAdapter extends ArrayAdapter<Produit> {

    private Context context;
    private ArrayList<Produit> productList;

    public ProduitAdapter(Context context, ArrayList<Produit> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false);
        }

        Produit currentProduct = productList.get(position);

        ImageView imageView = listItemView.findViewById(R.id.produit_image);
        TextView textViewName = listItemView.findViewById(R.id.produit_name);

        ImageButton imageButton_edit = listItemView.findViewById(R.id.editButton);
        ImageButton imageButton_delete  = listItemView.findViewById(R.id.deleteButton);

        imageButton_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_produit(position);

            }
        });

        imageButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_produit(position);
            }
        });

        Bitmap imageBitmap = currentProduct.getImage();
        if (imageBitmap != null) {
            // The image bitmap is not null, so it's valid
            // Log a message indicating that the image bitmap is valid
            Log.d("Image Debug", "Image bitmap is valid");

            // Set the image bitmap to the ImageView
            imageView.setImageBitmap(imageBitmap);
        } else {
            // The image bitmap is null, which indicates that there may be an issue
            // Log a message indicating that the image bitmap is null
            Log.d("Image Debug", "Image bitmap is null");
        }

        // Set product data to views
        imageView.setImageBitmap(currentProduct.getImage());
        textViewName.setText(currentProduct.getNom());


        return listItemView;
    }

    public void edit_produit(int position) {
        Produit currentProduct = productList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.edit_product_dialog, null);
        builder.setView(dialogView);

        // Find views in the dialog layout
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
        ImageView imageView = dialogView.findViewById(R.id.imageView);
        Button changePhotoButton = dialogView.findViewById(R.id.changePhotoButton);
        Button saveButton = dialogView.findViewById(R.id.saveButton);

        // Set initial values for EditText fields and ImageView
        editTextName.setText(currentProduct.getNom());
        editTextDescription.setText(currentProduct.getDescription());
        imageView.setImageBitmap(currentProduct.getImage());

        AlertDialog dialog = builder.create();
        dialog.show();

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement change photo logic
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save edited product information
                String newName = editTextName.getText().toString();
                String newDescription = editTextDescription.getText().toString();

                currentProduct.setNom(newName);
                currentProduct.setDescription(newDescription);

                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    public void delete_produit(int position) {

        productList.remove(position);
        notifyDataSetChanged();
    }

}
