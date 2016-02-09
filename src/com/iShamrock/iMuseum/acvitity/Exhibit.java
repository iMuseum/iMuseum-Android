package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.DataItem;
import com.iShamrock.iMuseum.data.MuseumData;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;

/**
 * Created by lifengshuang on 11/22/15.
 */
public class Exhibit extends Activity{
    //wx start here
    private LinearLayout likeLayout;
    private ImageButton back;
    private ImageButton like;
    private ImageButton go;
    /* the boolean isLiked is true when the user have liked it */
    private boolean isLiked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit);
        initLeftDrawer();

        /* get the id of this exhibit */
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");

        /* get the information of this exhibit */
        DataItem exhibit = MuseumData.getDataById(id);
        TextView exhibit_name = (TextView) this.findViewById(R.id.exhibit_name);
        ImageView exhibit_view = (ImageView) this.findViewById(R.id.exhibit_view);
        TextView exhibit_hall = (TextView) this.findViewById(R.id.exhibit_hall);
        TextView exhibit_introduce = (TextView) this.findViewById(R.id.exhibit_introduce);
        /* set the title of the exhibit as the name */
        exhibit_name.setText(exhibit.getName());
        /* set the image of the exhibit */
        String drawableName = exhibit.getImgId();
        int res = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        exhibit_view.setImageDrawable(getResources().getDrawable(res));
        /* set the hall of the exhibit */
        exhibit_hall.setText("Exhibition Hall: " + exhibit.getLocation());
        /* set the introduce of the exhibit */
        exhibit_introduce.setText("朝代：" + exhibit.getDynasty() + "  类型：" + exhibit.getType() + "\n" + exhibit.getDescription());
        Activity activity = this;

        isLiked = MuseumData.isFavored(id);
        likeLayout = (LinearLayout)this.findViewById(R.id.likeLayout);
        back = (ImageButton)this.findViewById(R.id.exhibit_back);
        like = (ImageButton)this.findViewById(R.id.exhibit_like);
        go = (ImageButton)this.findViewById(R.id.exhibit_go);

        if (isLiked) {
            like.setImageDrawable(getResources().getDrawable(R.drawable.liked));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Vision.class);
                startActivity(intent);
            }
        });
        /* when the user click the like button, change the image it shows */
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLiked) {
                    like.setImageDrawable(getResources().getDrawable(R.drawable.liked));
                    MuseumData.addFavorItem(id);
                    isLiked = true;
                }
                else {
                    like.setImageDrawable(getResources().getDrawable(R.drawable.likeit));
                    MuseumData.deleteFavorItem(id);
                    isLiked = false;
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Map.class);
                //intent.putExtra("floor", (int)exhibit.getFloor());
                startActivity(intent);
            }
        });
    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_exhibit);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_exhibit);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 1);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);
    }
}
