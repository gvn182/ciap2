package com.noobdev.ciap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GiovanniGomes on 02/03/2015.
 */
public class CategoriaAdapter extends ArrayAdapter<Categoria> {


    Context context;
    int layoutResourceId;
    Categoria data[] = null;

    public CategoriaAdapter(Context context, int layoutResourceId, Categoria[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
            holder.txtTitulo = (TextView) row.findViewById(R.id.txtTitulo);
            holder.txtDescricao = (TextView) row.findViewById(R.id.txtDescricao);
            row.setTag(holder);
        } else {
            holder = (WeatherHolder) row.getTag();
        }

        Categoria item = data[position];
        if (item != null) {
            holder.txtTitulo.setText(item.Titulo);
            holder.txtDescricao.setText(item.Descricao);

        }

        return row;
    }

    static class WeatherHolder {
        TextView txtDescricao;
        TextView txtTitulo;
    }
}