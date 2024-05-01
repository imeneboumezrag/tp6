package com.example.epicierbouha;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategorieAdapter extends ArrayAdapter<Categorie> {

    private final Context context;
    private final List<Categorie> values;

    public CategorieAdapter(Context context, List<Categorie> values) {
        super(context, R.layout.custom_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        TextView textView = rowView.findViewById(R.id.item_text);
        textView.setText(values.get(position).getNom());

        ImageButton imageButton = rowView.findViewById(R.id.delete_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(position);
            }
        });

        return rowView;
    }

    private void deleteItem(int position) {
        values.remove(position);
        notifyDataSetChanged();
    }

    private void onItemClick(int position) {
        Categorie category = values.get(position);
        Intent intent = new Intent(context, ProduitActivity.class);
        intent.putExtra("CATEGORY_OBJECT", category);
        context.startActivity(intent);
    }
}