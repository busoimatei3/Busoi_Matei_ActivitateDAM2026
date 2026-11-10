package com.example.laborator_45;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiDomeniu> lista=null;
    private Context ctx;
    private int resursaLayout;
    public ImagineAdapter(List<ImaginiDomeniu> lista, Context ctx, int resursaLayout){
        this.lista=lista;
        this.ctx=ctx;
        this.resursaLayout=resursaLayout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(resursaLayout, parent, false);
        }
        TextView tv1 = convertView.findViewById(R.id.tvimg1);
        ImageView iv = convertView.findViewById(R.id.imagevw);
        TextView tv2 = convertView.findViewById(R.id.tvimg2);
        ImaginiDomeniu item = lista.get(position);
        tv1.setText(item.getTextAfisat());
        if (item.getImagine() != null) {
            iv.setImageBitmap(item.getImagine());
        }
        tv2.setText(item.getLink());
        return convertView;
    }
}
