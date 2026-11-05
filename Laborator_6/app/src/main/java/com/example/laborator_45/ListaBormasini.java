package com.example.laborator_45;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaBormasini extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Bormasina> listaBormasini;
    private BormasinaAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_bormasini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.listViewBormasini);
        if (getIntent() != null && getIntent().hasExtra("CHEIE")) {
            listaBormasini = getIntent().getParcelableArrayListExtra("CHEIE");
        } else {
            listaBormasini = new ArrayList<>();
        }
        adapter=new BormasinaAdapter(listaBormasini,getApplicationContext(),R.layout.item_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent,view,position,id)->{
            Bormasina bormasinaSelectata=listaBormasini.get(position);
            Toast.makeText(ListaBormasini.this,"Bormasina selectata: \n"+bormasinaSelectata.toString(),Toast.LENGTH_LONG).show();
            Bundle bundle=new Bundle();
            bundle.putParcelable("EDIT",bormasinaSelectata);
            Intent intent=new Intent(ListaBormasini.this,Activitate_noua.class);
            intent.putExtra("EDIT_ITEM",bundle);
            startActivity(intent);
        });
        listView.setOnItemLongClickListener((parent,view,position,id)->{
            Bormasina bormasinaSelectata=listaBormasini.get(position);
            listaBormasini.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListaBormasini.this,"A fost sters: \n"+bormasinaSelectata.toString(),Toast.LENGTH_LONG).show();
            return true;
        });
    }
    public void finish(){
        Intent result=new Intent();
        result.putParcelableArrayListExtra("LISTA_ACTUALIZATA",listaBormasini);
        setResult(RESULT_OK,result);
        super.finish();
    }
}