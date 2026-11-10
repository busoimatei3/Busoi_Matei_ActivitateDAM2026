package com.example.laborator_45;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvRezultat;
    ArrayList<Bormasina> listaBormasini=new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    Bundle bundle = data.getBundleExtra("BUNDLE");
                    if (bundle != null) {
                        Bormasina bormasina = bundle.getParcelable("BORMASINA");
                        if (bormasina != null) {
                            tvRezultat.setText(bormasina.toString());
                            Toast.makeText(MainActivity.this, bormasina.toString(), Toast.LENGTH_LONG).show();
                            listaBormasini.add(bormasina);
                        }
                    }
                }
            }
    );
    private final ActivityResultLauncher<Intent> launcherLista = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Bormasina> listaActualizata = result.getData().getParcelableArrayListExtra("LISTA_ACTUALIZATA");
                    if (listaActualizata != null) {
                        listaBormasini = listaActualizata;
                        tvRezultat.setText("Total bormașini în listă: " + listaBormasini.size());
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvRezultat=findViewById(R.id.tv_rezultat);
    }

    public void Actiune(View view) {
        Intent intent=new Intent(MainActivity.this,Activitate_noua.class);
        launcher.launch(intent);
    }
    public void ActiuneAI(View view) {
        Intent intent = new Intent(MainActivity.this, Activitate_noua_ai.class);
        launcher.launch(intent);
    }
    public void DeschideLista(View view){
        if(listaBormasini.isEmpty()){
            Toast.makeText(this,"Lista este goala!",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent=new Intent(MainActivity.this,ListaBormasini.class);
        intent.putParcelableArrayListExtra("CHEIE",listaBormasini);
        launcherLista.launch(intent);
    }

    public void DeschideImagini(View view) {
        Intent intent=new Intent(MainActivity.this,Lista_imagini.class);
        startActivity(intent);
    }
}