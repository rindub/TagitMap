package com.betsy.chatsy.tagitmap;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.betsy.chatsy.tagitmap.icons.Icon;
import com.betsy.chatsy.tagitmap.model.Building;
import com.eegeo.indoors.IndoorMapView;
import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.geometry.LatLng;
import com.eegeo.mapapi.map.OnMapReadyCallback;
import com.eegeo.mapapi.markers.Marker;
import com.eegeo.mapapi.markers.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private EegeoMap eegooMap = null;
    private IndoorMapView indoorMapView = null;
    RelativeLayout container;
    private List<Marker> markers = new ArrayList<>();
    private Building building;

    private String AOS_INDOORMAPID="california_academy_of_sciences_19794";
    private double AOS_LAT=37.7697877;
    private double AOS_LON=-122.4666901;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, getString(R.string.eegeo_api_key));

        setLockedOrientation();

        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);



        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {

                initBuilding();

                eegooMap=map;

                container = findViewById(R.id.eegeo_ui_container);
                indoorMapView = new IndoorMapView(mapView, container, eegooMap);


                Marker marker = eegooMap.addMarker(new MarkerOptions()
                        .position(new LatLng(building.getLatitude(), building.getLongitude()))
                        .indoor(building.getIndoorMapId(), 0)
                        .labelText("Coffee")
                        .iconKey(Icon.COFFEE));

                markers.add(marker);

            }
        });

    }

    private void initBuilding() {

        building=new Building();
        building.setIndoorMapId(AOS_INDOORMAPID);
        building.setLatitude(AOS_LAT);
        building.setLongitude(AOS_LON);

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

        if (eegooMap != null) {
            for (Marker marker : markers) {
                eegooMap.removeMarker(marker);
            }
        }

        mapView.onDestroy();
    }


    private void setLockedOrientation() {
        if (getResources().getBoolean(R.bool.is_large_device)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}
