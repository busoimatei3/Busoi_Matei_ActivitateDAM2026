package com.example.laborator_45;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaBormasini extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Bormasina> listaBormasini;
    private BormasinaAdapter adapter = null;
    private final ActivityResultLauncher<Intent> launcherEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    Bundle bundle = data.getBundleExtra("BUNDLE");
                    if (bundle != null) {
                        Bormasina bormasinaModificata = bundle.getParcelable("BORMASINA");
                        int pos = bundle.getInt("POSITION", -1);
                        if (bormasinaModificata != null && pos != -1) {
                            listaBormasini.set(pos, bormasinaModificata);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(ListaBormasini.this, "Bormașină modificată cu succes!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );
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
        listView = findViewById(R.id.listViewBormasini);
        if (getIntent() != null && getIntent().hasExtra("CHEIE")) {
            listaBormasini = getIntent().getParcelableArrayListExtra("CHEIE");
        } else {
            listaBormasini = new ArrayList<>();
        }
        adapter = new BormasinaAdapter(listaBormasini, getApplicationContext(), R.layout.item_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Bormasina bormasinaSelectata = listaBormasini.get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelable("EDIT", bormasinaSelectata);
            bundle.putInt("POSITION", position); // Trimitem si pozitia in lista
            Intent intent = new Intent(ListaBormasini.this, Activitate_noua.class);
            intent.putExtra("EDIT_ITEM", bundle);
            launcherEdit.launch(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Bormasina bormasinaSelectata = listaBormasini.get(position);
            listaBormasini.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListaBormasini.this, "A fost sters: \n" + bormasinaSelectata.toString(), Toast.LENGTH_LONG).show();
            return true;
        });
    }
    public void finish() {
        Intent result = new Intent();
        result.putParcelableArrayListExtra("LISTA_ACTUALIZATA", listaBormasini);
        setResult(RESULT_OK, result);
        super.finish();
    }
}