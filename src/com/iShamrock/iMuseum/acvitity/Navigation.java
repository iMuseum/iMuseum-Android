
package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.acvitity.AR.ARActivity;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import com.ids.sdk.android.locate.Locator;
import com.ids.sdk.android.map.*;
import com.ids.sdk.android.map.Map;
import com.ids.sdk.android.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    private Location location;
    private Location destination;
    private ListView drawerList;
    private ImageButton leftDrawerBtn;
    private DrawerLayout drawerLayout;
    private ImageButton visionBtn;
    public static Location currentLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        initLeftDrawer();

        //open leftDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_navigation);
        leftDrawerBtn = (ImageButton) findViewById(R.id.left_drawer_btn_nav);
        leftDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });

        visionBtn = (ImageButton) findViewById(R.id.to_vision);
        visionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Navigation.this, ARActivity.class);
                startActivity(intent);
            }
        });

        mapView = (MapView) findViewById(R.id.map_view);
        map = mapView.getMap();
        Marker m0 = new Marker((float) 58.9, (float)14.9, 3);
        Marker m1 = new Marker((float) 38.0, (float)14.9, 3);
        Marker m2 = new Marker((float) 18.4, (float)14.9, 3);
        Marker m3 = new Marker((float) 10.0, (float)20.0, 3);
        Marker m4 = new Marker((float) 10.0, (float)35.0, 3);
        Marker m5 = new Marker((float) 10.0, (float)40.0, 3);
        Marker m6 = new Marker((float) 10.0, (float)50.0, 3);
        Marker m7 = new Marker((float) 15.0, (float)50.0, 3);
        Marker m8 = new Marker((float) 26.2, (float)51.1, 3);
        Marker m9 = new Marker((float) 40.0, (float)50.0, 3);
        Marker m10 = new Marker((float) 52.0, (float)30.0, 4);
        Marker m11 = new Marker((float) 52.0, (float)45.0, 4);

        map.setOnMapLoadListener(new Map.OnMapLoadListener() {
            @Override
            public void onStartLoading() {
                toast("start loading");
            }

            @Override
            public void onLoaded() {
                toast("loaded");
                //add marker
//                map.addMarker(m0, new Map.MarkerCallback() {
//                            @Override
//                            public View getView(Marker marker) {
//                                return createTextView("第一展览馆");
//                            }
//                });
//                map.addMarker(m1, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国古代雕塑馆");
//                    }
//                });
//                map.addMarker(m2, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国古代青铜馆");
//                    }
//                });
//                map.addMarker(m3, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国古代陶瓷馆");
//                    }
//                });
//                map.addMarker(m4, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("第二展览馆");
//                    }
//                });
//                map.addMarker(m5, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国历代绘画馆");
//                    }
//                });
//                map.addMarker(m6, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国历代书法馆");
//                    }
//                });
//                map.addMarker(m7, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国历代玺印馆");
//                    }
//                });
//                map.addMarker(m8, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国少数民族工艺馆");
//                    }
//
//                });
//                map.addMarker(m9, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国历代钱币馆");
//                    }
//                });
//                map.addMarker(m10, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国历代玉器馆");
//                    }
//                });
//                map.addMarker(m11, new Map.MarkerCallback() {
//                    @Override
//                    public View getView(Marker marker) {
//                        return createTextView("中国明清家具馆");
//                    }
//                });
//                toast("marker done");
                if(destination != null) {
                    map.navigate(new Location((float)50.0, (float) 10.0, 3), destination);//test
                }
            }
        });

        map.setOnMapClickListener(new Map.OnMapClickListener() {
            @Override
            public void onClickPoi(int poiId, int floorLevel, int buildingId) {
                switch (poiId){
                    case 485788:{
                        exhibitionHallName = "第一展览馆";
                        location = new Location((float) 58.9, (float)14.9, 3);
                        break;
                    }
                    case 485779:{
                        exhibitionHallName = "中国古代雕塑馆";
                        location = new Location((float) 38.0, (float)14.9, 3);
                        break;
                    }
                    case 485761:{
                        exhibitionHallName = "中国古代青铜馆";
                        location = new Location((float) 18.4, (float)14.9, 3);
                        break;
                    }
                    case 485771:{
                        exhibitionHallName = "中国古代陶瓷馆";
                        location = new Location((float) 10.0, (float)20.0, 3);
                        break;
                    }
                    case 485775:{
                        exhibitionHallName = "第二展览馆";
                        location = new Location((float) 10.0, (float)35.0, 3);
                        break;
                    }
                    case 485773:{
                        exhibitionHallName = "中国历代绘画馆";
                        location = new Location((float) 10.0, (float)40.0, 3);
                        break;
                    }
                    case 485777:{
                        exhibitionHallName = "中国历代书法馆";
                        location = new Location((float) 10.0, (float)50.0, 3);
                        break;
                    }
                    case 485763:{
                        exhibitionHallName = "中国历代玺印馆";
                        location = new Location((float) 15.0, (float)50.0, 3);
                        break;
                    }
                    case 485772:{
                        exhibitionHallName = "中国少数民族工艺馆";
                        location = new Location((float) 26.2, (float)51.1, 3);
                        break;
                    }
                    case 485762:{
                        exhibitionHallName = "中国历代钱币馆";
                        location = new Location((float) 40.0, (float)50.0, 3);
                        break;
                    }
                    case 485792:{
                        exhibitionHallName = "中国历代玉器馆";
                        location = new Location((float) 52.0, (float)30.0, 4);
                        break;
                    }
                    case 485793:{
                        exhibitionHallName = "中国明清家具馆";
                        location = new Location((float) 52.0, (float)45.0, 4);
                        break;
                    }
                    default:{
                        exhibitionHallName = null;
                        break;
                    }
                }
            }

            @Override
            public void onClickNothing() {
                toast("Please go to the third and fourth floor and have a look!");
            }
        });

        map.setOnCameraChangeListener(new Map.OnCameraChangeListener() {
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
                if (locator.isReady()) {
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
                currentLocation = location;
                //test
                Log.i("get location", "\n"+location.getFloorLevel() + "楼\n"
                + "x坐标: "+location.getX() + "\ny坐标: " + location.getY());
            }

            @Override
            public void onLocatingFail() {
                map.hideLocation();
//                toast("Locator Fail!");
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
                Building.requestBuilding(building.getBuildingId(), new RequestCallback<Building>() {
                    @Override
                    public void onFinish(Building building) {
                        Set<Poi> poiSet = building.getFloorList().get(2).getPoiSet();
                        Iterator<Poi> iteratorSet = poiSet.iterator();
                        while (iteratorSet.hasNext()) {
                            Poi poi = iteratorSet.next();
                            Log.i("Poi", "PoiID: " + poi.getPoiId()+ "floor: "+poi.getFloorLevel()+"x:"+poi.getX()+" ; y:"+poi.getY());
                        }
                    }

                    @Override
                    public void onFail() {
                        Log.i("Building", "requestBuilding onFail");
                    }
                });
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
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {//from Exhibit
            float x = bundle.getFloat("x");
            float y = bundle.getFloat("y");
            int floor = bundle.getInt("floor");
            destination = new Location(x, y, floor);
        }

        navBtn = (TextView) findViewById(R.id.navigate_button);
        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (exhibitionHallName != null) {
                    map.navigateTo(location);
                }
                else {
                    toast("Please choose an exhibitionHall to be navigated!");
                }
            }
        });

        //startActivity
        jumpBtn = (Button) findViewById(R.id.jump_button);
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exhibitionHallName != null) {
                    Intent intent = new Intent();
                    intent.setClass(Navigation.this, Showroom.class);
                    intent.putExtra("name", exhibitionHallName);
                    startActivity(intent);
                }
                else{
                    toast("Please choose an exhibitionHall to Jump into!");
                }
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
        drawerList = (ListView) findViewById(R.id.left_drawer_navigation);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_navigation);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 5);
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

    private View createTextView(String markerStr){
        //动态添加布局(java方式)
        TextView tv1 = new TextView(Navigation.this);
        tv1.setTextSize(10);
        tv1.setText(markerStr);
        return tv1;
    }
}