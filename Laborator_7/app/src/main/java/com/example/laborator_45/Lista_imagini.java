package com.example.laborator_45;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Lista_imagini extends AppCompatActivity {
    private List<ImaginiDomeniu> lista = new ArrayList<>();
    private ImagineAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv=findViewById(R.id.imagini);
        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://www.reducerex3.ro/wp-content/uploads/2024/02/bormasina-electrica-ml-galbena-cu-2-acumulatori-statie-incarcare-si34613.jpg");
        linkuriImagini.add("https://ghidulcumparatorului.ro/wp-content/uploads/2023/04/bor4-2048x1720.jpg");
        linkuriImagini.add("https://c.cdnmp.net/287853223/p/m/7/bd1750-bormasina-de-banc-fixa-cu-coloana-procraft-19-20-kg~896097.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains2/laky.ro/files/product/original/trusa-bormasina-36v-cu-2-acumulatori-si-incarcator-accesorii-016841.png");
        linkuriImagini.add("https://tse3.mm.bing.net/th/id/OIP.H_zsz-f73YuRbwFpiphHWAHaFL?r=0&rs=1&pid=ImgDetMain&o=7&rm=3");

        List<Bitmap> listaImagini=new ArrayList<>();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            for(String s:linkuriImagini){
                try{
                    URL u=new URL(s);
                    HttpURLConnection con=(HttpURLConnection) u.openConnection();
                    InputStream is=con.getInputStream();
                    listaImagini.add(BitmapFactory.decodeStream(is));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            handler.post(()->{
                lista.add(new ImaginiDomeniu("Imagine 1", listaImagini.get(0), "https://2energy.ro/?utm_source=google&utm_medium=cpc&utm_campaign=RO%20%7C%20SRCH%20-%20Brand&utm_id=18670969670&gad_source=1&gad_campaignid=23929322824&gbraid=0AAAAA-QV-r-xbgSbpoAvT1Y0_T-6b9BRy&gclid=Cj0KCQiAnsvXBhCqARIsAHKgGNaZBGVWT34iK2nIqTHiJ4FptJtLkM9I3FoHlobZVbYsYHo-yJzZyQUaAvgxEALw_wcB"));
                lista.add(new ImaginiDomeniu("Imagine 2", listaImagini.get(1), "https://www.mistertools.ro/?gad_source=1&gad_campaignid=21344494960&gbraid=0AAAAA9wQ2u1-I0dWwm15h62d2w7rUP8bi&gclid=Cj0KCQiAnsvXBhCqARIsAHKgGNaVeBCFacfiJ36_tO9-u4w2H3Rpv7uq_lDgfz3JRmj075w0izRkwOYaAoq2EALw_wcB"));
                lista.add(new ImaginiDomeniu("Imagine 3", listaImagini.get(2), "https://mathaus.ro/blog/cum-aleg-o-bormasina-Art192?srsltid=AYCX9vLdytfsJAWi7YlYlAhjDvNkjEEd2CyBM4VF7WdZjsKwmXra8r89"));
                lista.add(new ImaginiDomeniu("Imagine 4", listaImagini.get(3), "https://blog.yalco.ro/bricolaj/bormasina-ghid-complet-pentru-alegerea-si-utilizarea-corecta-in-orice-proiect-sfaturi-utile-pentru-performanta-si-rezultate-profesionale"));
                lista.add(new ImaginiDomeniu("Imagine 5", listaImagini.get(4), "https://www.hornbach.ro/proiecte/tot-ce-trebuie-sa-stiti-despre-bormasina/"));

                adapter=new ImagineAdapter(lista,getApplicationContext(),R.layout.adapt_img);
                lv.setAdapter(adapter);
            });
        });
        lv.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
            it.putExtra("link", lista.get(position).getLink());
            startActivity(it);
        });
    }
}