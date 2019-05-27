package com.example.arrec.navigation_view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arrec.navigation_view.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;

    public ListAdapter(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad Item = (Entidad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_lessons, null);
        ImageView imgMiniatura = (ImageView) convertView.findViewById(R.id.imgMiniatura);
        TextView txtTitulo = convertView.findViewById(R.id.txtVwTitulo);
        TextView txtDescripcion = convertView.findViewById(R.id.txtVwDescripcion);

        imgMiniatura.setImageResource(Item.getImgMiniatura());
        txtTitulo.setText(Item.getTitulo());
        txtDescripcion.setText(Item.getDescripcion());
        return convertView;
    }
}
