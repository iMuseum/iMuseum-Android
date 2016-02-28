package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.os.Bundle;
import com.iShamrock.iMuseum.R;
import com.ids.model.map.City;
import com.ids.sdk.android.map.*;
import com.ids.sdk.android.map.Map;
import com.ids.sdk.android.model.Building;
import com.ids.sdk.android.model.RequestCallback;

import java.util.List;


/**
 * Created by wangxin on 16/2/28.
 */
public class Navigation extends Activity {
    private MapView mapView;
    private Map map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        mapView = (MapView) findViewById(R.id.map_view);
        map = mapView.getMap(); //Map对象是地图显示的关键，对地图的调用都来自该对象。

        City.requestCity(new RequestCallback<List<City>>() {
            @Override
            public void onFinish(List<City> cities) {
                //简单起见，使用第一个城市的第一个建筑
                if (cities.get(0).getBuildingSet().size() > 0) if (cities.size() > 0) {
                    Building building = cities.get(0).getBuildingSet().iterator().next();
                    map.init(building.getBuildingId()); //加载地图
                }
            }

            @Override
            public void onFail() {
            }
        });
    }

}
