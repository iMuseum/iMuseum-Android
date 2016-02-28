package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.MuseumData;

import java.util.*;

/**
 * Created by mayezhou on 16/2/28.
 */
public class ExhibitionHallOfFloor extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibitionhalloffloor);

        /* get the floor of homepage */
        Bundle bundle = this.getIntent().getExtras();
        int floor = bundle.getInt("floor");
        List<java.util.Map<String, Object>> data = MuseumData.getExhibitionHallByFloor2(floor);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new SimpleAdapter(this, data,
                R.layout.exhibitionhall_gridview, new String[]{"img", "name", "englishName"},
                new int[]{R.id.exhibitionHall_img, R.id.exhibitionHall_name, R.id.exhibitionHall_englishName}));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(ExhibitionHallOfFloor.this, Showroom.class);
                intent.putExtra("name", (String) data.get(i).get("name"));
                startActivity(intent);
            }
        });
    }
}
