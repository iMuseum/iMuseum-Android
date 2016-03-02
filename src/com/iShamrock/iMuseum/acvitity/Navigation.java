
package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.iShamrock.iMuseum.R;
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
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //static
        setContentView(R.layout.navigation);
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
                //toast("onClickPoi and the poiId is: " + poiId);
                switch (poiId){
                    case 485788:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "第一展览馆");
                        startActivity(intent);
                        break;
                    }
                    case 485779:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国古代雕塑馆");
                        startActivity(intent);
                        break;
                    }
                    case 485761:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国古代青铜馆");
                        startActivity(intent);
                        break;
                    }
                    case 485771:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国古代陶瓷馆");
                        startActivity(intent);
                        break;
                    }
                    case 485775:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "第二展览馆");
                        startActivity(intent);
                        break;
                    }
                    case 485773:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国历代绘画馆");
                        startActivity(intent);
                        break;
                    }
                    case 485777:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国历代书法馆");
                        startActivity(intent);
                        break;
                    }
                    case 485763:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国历代玺印馆");
                        startActivity(intent);
                        break;
                    }
                    case 485772:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国少数民族工艺馆");
                        startActivity(intent);
                        break;
                    }
                    case 485762:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国历代钱币馆");
                        startActivity(intent);
                        break;
                    }
                    case 485792:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国历代玉器馆");
                        startActivity(intent);
                        break;
                    }
                    case 485793:{
                        Intent intent = new Intent();
                        intent.setClass(Navigation.this, Showroom.class);
                        intent.putExtra("name", "中国明清家具馆");
                        startActivity(intent);
                        break;
                    }
                    default:break;
                }
                //TODO: NavEvent
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
                Log.i("get location", location.getFloorLevel() + "楼\n"
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
                //toast("requestCity onFinish");
                Iterator<Building> iterator = cities.get(0).getBuildingSet().iterator();
                Building building = iterator.next();
                Log.i("building", building.getName());//test
                map.init(building.getBuildingId());
                locator.init(building.getBuildingId());
                //test  log.v(city & building ...)
                for (City city:
                     cities) {
                    Log.i("city", city.getName());
                }
            }

            @Override
            public void onFail() {
                //toast("requestCity onFail");
            }
        });

        //test
        button = (Button)findViewById(R.id.navigate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Location location = new Location(20,20,3);
//                map.navigateTo(location);
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

}