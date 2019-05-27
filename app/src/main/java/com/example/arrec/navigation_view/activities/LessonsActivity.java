package com.example.arrec.navigation_view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.arrec.navigation_view.R;
import com.example.arrec.navigation_view.adapters.Entidad;
import com.example.arrec.navigation_view.adapters.ListAdapter;

import java.util.ArrayList;

public class LessonsActivity extends AppCompatActivity {
    private ListView lvItems;
    private ListAdapter adaptador;
    private String path, titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        lvItems = (ListView) findViewById(R.id.lvItems);
        adaptador = new ListAdapter(this, GetArrayItems());
        lvItems.setAdapter(adaptador);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lesson_start = new Intent(getApplicationContext(), ShowLesson.class);

                switch (position){
                    case 0:
                        titulo = "Como hacer bastilla a mano fácil para ajustar el largo";
                        path = "https://www.youtube.com/embed/DmEdNUo-rnc";
                        break;
                    case 1:
                        titulo = "Como hacer ojales a mano ";
                        path = "https://www.youtube.com/embed/M-QPCiY3W3M";
                        break;
                    case 2:
                        titulo = "Como colocar un botón a un pantalon en menos de dos minutos ";
                        path = "https://www.youtube.com/embed/wxTG2exeduw";
                        break;
                    case 3:
                        titulo = "Como reemplazar un cierre roto en jeans o pantalones ";
                        path = "https://www.youtube.com/embed/pDEYEFz3sb4";
                        break;
                    case 4:
                        titulo = "Cómo tomar medidas para hacer un vestido";
                        path = "https://www.youtube.com/embed/GI1kr2NxP8c";
                        break;
                    case 5:
                        titulo = "Patrones básicos para vestido ";
                        path = "https://www.youtube.com/embed/vY2hxkjMzdQ";
                        break;
                    case 6:
                        titulo = "Cómo hacer el pantalón más fácil del mundo sin patrón. DIY. ";
                        path = "https://www.youtube.com/embed/YK-rhzslg2o";
                        break;
                }
                lesson_start.putExtra("titulo", titulo);
                lesson_start.putExtra("URL", path);
                startActivity(lesson_start);
            }
        });
    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.lesson_1,"Lección 1", "Como hacer bastilla a mano fácil para ajustar el largo"));
        listItems.add(new Entidad(R.drawable.lesson_2,"Lección 2", "Como hacer ojales a mano "));
        listItems.add(new Entidad(R.drawable.lesson_3,"Lección 3", "Como colocar un botón a un pantalon en menos de dos minutos "));
        listItems.add(new Entidad(R.drawable.lesson_4,"Lección 4", "Como reemplazar un cierre roto en jeans o pantalones "));
        listItems.add(new Entidad(R.drawable.lesson_5,"Lección 5", "Cómo tomar medidas para hacer un vestido"));
        listItems.add(new Entidad(R.drawable.lesson_6,"Lección 6", "Patrones básicos para vestido "));
        listItems.add(new Entidad(R.drawable.lesson_7,"Lección 7", "Cómo hacer el pantalón más fácil del mundo sin patrón. DIY. "));


        return listItems;
    }
}
