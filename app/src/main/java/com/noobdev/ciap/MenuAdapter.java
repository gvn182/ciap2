package com.noobdev.ciap;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GiovanniGomes on 02/03/2015.
 */
public class MenuAdapter extends ArrayAdapter<MenuClass> {


    Context context;
    int layoutResourceId;
    MenuClass data[] = null;

    public MenuAdapter(Context context, int layoutResourceId, MenuClass[] data) {
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
            holder.txtMenu = (TextView) row.findViewById(R.id.txtMenu);
            holder.imgMenu = (ImageView) row.findViewById(R.id.imgMenu);
            row.setTag(holder);
        } else {
            holder = (WeatherHolder) row.getTag();
        }

        MenuClass item = data[position];
        if (item != null) {
            holder.txtMenu.setText(item.Titulo);
            holder.imgMenu.setImageResource(item.ResID);
        }

        return row;
    }

    static class WeatherHolder {
        ImageView imgMenu;
        TextView txtMenu;
    }
}