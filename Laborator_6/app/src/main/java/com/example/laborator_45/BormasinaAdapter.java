package com.example.laborator_45;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BormasinaAdapter extends BaseAdapter {
    private List<Bormasina> listaBormasini=null;
    private Context ctx;
    private int resursaLayout;

    public BormasinaAdapter(List<Bormasina> listaBormasini, Context ctx, int resursaLayout) {
        this.listaBormasini = listaBormasini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return listaBormasini.size();
    }

    @Override
    public Object getItem(int position) {
        return listaBormasini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,parent,false);

        TextView tv1=v.findViewById(R.id.tv1);
        TextView tv2=v.findViewById(R.id.tv2);
        TextView tv3=v.findViewById(R.id.tv3);
        TextView tv4=v.findViewById(R.id.tv4);
        TextView tv5=v.findViewById(R.id.tv5);

        Bormasina b=listaBormasini.get(position);

        tv1.setText("Nume: " + b.getNume());
        tv2.setText("Grad uzare: " +b.getGradUzare());
        tv3.setText("Folosita: " +(b.getFolosita() ? 0:1 ));
        tv4.setText("Stare: " +(b.getNou()? "Nou" : "Vechi"));
        tv5.setText("Rating: "+ b.getRating());

        return v;
    }
}
