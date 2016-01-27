package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.MuseumData;

import java.util.*;

/**
 * Created by wangxin on 16/1/27.
 */
public class Showroom extends Activity{
    private TextView showroom_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showroom);

        /* get the showroom of this exhibit */
        // Bundle bundle = this.getIntent().getExtras();
        //String showroom = bundle.getString("location");
        //todo
        String showroom = "中国古代陶瓷馆";
        showroom_title = (TextView) this.findViewById(R.id.showroom_title);
        showroom_title.setText(showroom);
        initShowroomList();

    }
    private void initShowroomList() {
        ListView showroomList = (ListView) findViewById(R.id.list_showroom);
        List<java.util.Map<String, Object>> data = MuseumData.getShowroomData("中国古代陶瓷馆");
        showroomList.setAdapter(new SimpleAdapter(this, data,
                R.layout.showroom_item, new String[]{"name", "img", "description"},
                new int[]{R.id.showroom_list_name, R.id.showroom_list_img, R.id.showroom_list_description}));
        showroomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(Showroom.this, Exhibit.class);
                intent.putExtra("id", (int) data.get(i).get("id"));
                startActivity(intent);
            }
        });
    }
}
