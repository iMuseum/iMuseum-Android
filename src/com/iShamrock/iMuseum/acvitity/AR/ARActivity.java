package com.iShamrock.iMuseum.acvitity.AR;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.iShamrock.iMuseum.acvitity.Navigation;
import com.iShamrock.iMuseum.data.MuseumData;
import com.iShamrock.iMuseum.data.ShowroomItem;
import com.ids.sdk.android.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逢双 on 14-2-22.
 */
public class ARActivity extends Activity {

    //用定位更新currentPoint
    private Location currentLocation = Navigation.currentLocation;
    LBSPoint currentPoint = new LBSPoint(currentLocation);
    CameraPreview cameraPreview;
    SensorManager sensorManager;
    Sensor orientalSensor;
    ARTextView arTextView;
    Context context;
    public static DisplayMetrics dm;
    public static ArrayList<Angle> angleArray;
    float values[] = new float[3];//get from orientationalSensor
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                arTextView.invalidate();
            } else {
                //这里主要用来处理点击事件。
                //再ARTextView的onTouchEvent里面调用handler发到这里
                //可以用来实现点击相机上的文字显示导航或者跳到展馆页面
//                Intent intent = new Intent(context, Discussion.class);
//                intent.putExtra("position", msg.what);
//                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //这里面的基本不用管
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dm = getResources().getDisplayMetrics();
        cameraPreview = new CameraPreview(this);
        this.context = this;
        setContentView(cameraPreview);
        initOrientalSensor();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAngles();
                addARTextView();
                while (true) {
                    try {
                        //这里是以1秒为间隔，可以适当缩短
                        Thread.sleep(1000);
                        for (int i = 0; i < angleArray.size(); i++) {
                            angleArray.get(i).reset((values[0] * Math.PI / 180), currentPoint);
                        }
                        handler.sendEmptyMessage(-1);
                    } catch (Exception e) {
//                        System.out.println("eeee");
                    }
                }
            }
        }).start();
    }

    private void addARTextView() {
        //这里面耶基本不用管
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        arTextView = new ARTextView(this, this);
        addContentView(arTextView, layoutParams);
    }

    private void getAngles() {
        double angle = values[0] * Math.PI / 180;
        angleArray = new ArrayList<>();
        //这里在angleArray里面加入展馆的坐标（固定值）
        //new Angle(angle, 现在坐标, 展馆坐标);
//        MuseumData.initData(this);
        List<ShowroomItem> exhibitionHalls = MuseumData.getExhibitionHalls();
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            angleArray.add(new Angle(angle, new LBSPoint(currentLocation), new LBSPoint(exhibitionHall.getLocation()), exhibitionHall.getName()));
        }
    }

    private void initOrientalSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        orientalSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, orientalSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            values = event.values;
            Log.i("SensorValue", values[0]+"    "+values[1]+"   "+values[2]);
            for (int i = 0; i < angleArray.size(); i++) {
                //这里是重新设定现在的地理位置和方向，不用修改
                angleArray.get(i).reset((double)values[0], currentPoint);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
