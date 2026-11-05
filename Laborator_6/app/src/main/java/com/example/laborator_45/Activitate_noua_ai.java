package com.example.laborator_45;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitate_noua_ai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua_ai);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Populam Spinner-ul pentru gradul de uzare
        Integer[] valori = {500, 750, 1000, 1250, 1500};
        Spinner spinner = findViewById(R.id.spinnerGrad_ai);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valori);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void Adauga(View view) {
        EditText et = findViewById(R.id.edit_txt_ai);
        CheckBox cb = findViewById(R.id.cbox_ai);
        RadioButton rbNou = findViewById(R.id.radbtnNOUA_ai);
        Spinner sp = findViewById(R.id.spinnerGrad_ai);
        RatingBar ratingBar = findViewById(R.id.ratingBar_ai);

        String nume = et.getText().toString();
        Boolean folosita = cb.isChecked();
        int gradUzare = (Integer) sp.getSelectedItem();
        Boolean nou = rbNou.isChecked();
        Float rating = ratingBar.getRating();

        // Cream obiectul Bormasina
        Bormasina bm = new Bormasina(nume, folosita, nou, gradUzare, rating);

        // Impachetam obiectul in Intent / Bundle
        Intent rezultat = new Intent();
        Bundle rez = new Bundle();
        rez.putParcelable("BORMASINA", bm);
        rezultat.putExtra("BUNDLE", rez);

        setResult(RESULT_OK, rezultat);
        finish();
    }
}