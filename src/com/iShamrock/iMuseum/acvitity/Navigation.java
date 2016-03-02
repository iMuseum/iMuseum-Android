
package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import com.ids.sdk.android.locate.Locator;
import com.ids.sdk.android.map.*;
import com.ids.sdk.android.map.Map;
import com.ids.sdk.android.model.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by mayezhou on 16/2/26.
 */
public class Navigation extends Activity {
    private Map map;
    private MapView mapView;
    private Locator locator;
    private TextView navBtn;
    private Button jumpBtn;
    private String exhibitionHallName = null;
    private Location location = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //static
        setContentView(R.layout.navigation);
        initLeftDrawer();
        mapView = (MapView) findViewById(R.id.map_view);

        map = mapView.getMap();

        map.setOnMapLoadListener(new Map.OnMapLoadListener() {
            @Override
            public void onStartLoading() {
                toast("稍等片刻,马上就好~");
            }

            @Override
            public void onLoaded() {
                toast("(*^_^*)");
            }
        });

        map.setOnMapClickListener(new Map.OnMapClickListener() {
            @Override
            public void onClickPoi(int poiId, int floorLevel, int buildingId) {
                switch (poiId){
                    case 485788:{
                        exhibitionHallName = "第一展览馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485779:{
                        exhibitionHallName = "中国古代雕塑馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485761:{
                        exhibitionHallName = "中国古代青铜馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485771:{
                        exhibitionHallName = "中国古代陶瓷馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485775:{
                        exhibitionHallName = "第二展览馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485773:{
                        exhibitionHallName = "中国历代绘画馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485777:{
                        exhibitionHallName = "中国历代书法馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485763:{
                        exhibitionHallName = "中国历代玺印馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485772:{
                        exhibitionHallName = "中国少数民族工艺馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485762:{
                        exhibitionHallName = "中国历代钱币馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485792:{
                        exhibitionHallName = "中国历代玉器馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    case 485793:{
                        exhibitionHallName = "中国明清家具馆";
                        location = new Location((float) 55.0, (float)15.5, 3);
                        break;
                    }
                    default:break;
                }
            }

            @Override
            public void onClickNothing() {
                toast("Please go to the third and fourth floor and have a look!");
            }
        });

        map.setOnCameraChangeListener(new Map.OnCameraChangeListener() {//TODO
            @Override
            public void onCameraChange() {
            }

            @Override
            public void onCameraChangeFinished() {
            }
        });

        map.setNavListener(new Map.NavListener() {
            @Override
            public void startUpdatingLocation() {
                if (locator.isReady()) {//TODO:  must be false
                    locator.startLocating();
                }
            }

            @Override
            public void stopUpdatingLocation() {
                if (locator.isReady()) {
                    locator.stopLocating();
                }
            }

            @Override
            public void navigationInfoUpdate(NavigationInfo navigationInfo) {
                Log.v("Navigation", navigationInfo.toString());
                //TODO: put info on the screen:
            }

            //following code need not change
            @Override
            public float getMinFarDistanceFromRoute() {
                return 15;
            }

            @Override
            public float getMaxNearDistanceToTarget() {
                return 10;
            }

            @Override
            public void locationFarFromRoute(float distance) {
                Log.v("Navigation", "locationFarFromRoute");
            }

            @Override
            public void locationNearToTarget(float distance) {
                Log.v("Navigation", "locationNearToTarget");
            }
        });

        map.setOnErrorListener(new Map.OnErrorListener() {
            @Override
            public void onError(String description) {
                Log.e("onError", description);
            }
        });

        locator = Locator.getInstance(this);
        locator.setListener(new Locator.Listener() {
            @Override
            public void onReady() {
            }

            @Override
            public void onFail() {
                toast("Locator Fail?");//debug
            }

            @Override
            public void onLocatingSucceed(Location location) {
                map.setLocation(location);
                //test
                Log.i("get location", "\n"+location.getFloorLevel() + "楼\n"
                + "x坐标: "+location.getX() + "\ny坐标: " + location.getY());
            }

            @Override
            public void onLocatingFail() {
                map.hideLocation();
                toast("Locator Fail!");//debug
            }
        });

        City.requestCity(new RequestCallback<List<City>>() {
            @Override
            public void onFinish(List<City> cities) {
                Iterator<Building> iterator = cities.get(0).getBuildingSet().iterator();
                Building building = iterator.next();
                Log.i("building", building.getName());
                map.init(building.getBuildingId());
                locator.init(building.getBuildingId());
                for (City city:
                     cities) {
                    Log.i("city", city.getName());
                }
            }

            @Override
            public void onFail() {
                toast("requestCity onFail");
            }
        });

        //navigation
        navBtn = (TextView) findViewById(R.id.navigate_button);
        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.navigateTo(location);
            }
        });

        //startActivity
        jumpBtn = (Button) findViewById(R.id.jump_button);
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Navigation.this, Showroom.class);
                intent.putExtra("name", exhibitionHallName);
                startActivity(intent);
            }
        });
    }

    private void toast(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Navigation.this, content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //activity methods
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locator.onDestory();
    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_navigation);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_navigation);
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