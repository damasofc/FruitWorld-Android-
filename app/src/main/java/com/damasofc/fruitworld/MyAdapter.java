package com.damasofc.fruitworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends BaseAdapter {

    private	Context	context;
    private	int	layout;
    private	List<Frutas> fruits;
    public	MyAdapter(Context context, int	layout, List<Frutas> names) {
        this.context = context;
        this.layout = layout;
        this.fruits = names;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            //inflamos	la	vista	que	nos	ha	llegado	con	nuestro	layout
            LayoutInflater layoutInflater	=	LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();
            if(parent instanceof ListView) {
                holder.imagen = (ImageView) convertView.findViewById(R.id.imageView);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.fruitName);
                holder.origen = (TextView) convertView.findViewById(R.id.fruitOrigin);
            }else{
                holder.imagen = (ImageView) convertView.findViewById(R.id.imageViewGrid);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.fruitNameGrid);
                holder.origen = (TextView) convertView.findViewById(R.id.fruitOriginGrid);
            }
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String	fruit	=	fruits.get(position).getNombre();
        String  origin = fruits.get(position).getOrigen();
        int idIm = fruits.get(position).getIdImagen();
        holder.nameTextView.setText(fruit);
        holder.origen.setText(origin);
        holder.imagen.setImageResource(idIm);
        //	devolvemos	la	vista	inflada	y	modificada	con	nuestros	datos
        return	convertView;
    }
    static class ViewHolder {
        private TextView nameTextView;
        private TextView origen;
        private ImageView imagen;
    }
}
