package com.example.always_refugally;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.always_refugally.DBCLASS.Store;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import static com.example.always_refugally.R.id.button2;
import static com.example.always_refugally.R.id.button3;
import static com.example.always_refugally.R.id.button4;
import static com.example.always_refugally.R.id.button6;
import static com.example.always_refugally.R.id.texttext;


public class MapActivity extends FragmentActivity
        implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {
    private MapReverseGeoCoder mReverseGeoCoder = null;
    public static final MapPoint DEFAULT_MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.29814, 126.972169);


    public static MapPoint CurrPoint = MapPoint.mapPointWithGeoCoord(0,0);

    private MapPOIItem mDefaultMarker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//다음이 제공하는 MapView객체 생성 및 API Key 설정
        final MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("1581af30c6260a7eba804e18e5319ddb");
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setCurrentLocationEventListener(this);
        Button btn = (Button) findViewById(button2);
        Button btn2 = (Button) findViewById(button3);
        Button btn3 = (Button) findViewById(button4);
        Button btn4 = (Button) findViewById(button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReverseGeoCoder = new MapReverseGeoCoder("1581af30c6260a7eba804e18e5319ddb", mapView.getMapCenterPoint(), MapActivity.this, MapActivity.this);
                mReverseGeoCoder.startFindingAddress();
                CurrPoint=mapView.getMapCenterPoint();
                mapView.setCurrentLocationRadius(700); // meter
                mapView.setCurrentLocationRadiusFillColor(Color.argb(77, 255, 255, 0));
                mapView.setCurrentLocationRadiusStrokeColor(Color.argb(77, 255, 165, 0));
                mapView.setZoomLevel(3, true);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                ArrayList<Store> sl = (ArrayList)intent.getSerializableExtra("store");
                ArrayList<MapPoint> MapPo = new ArrayList<>();
                ArrayList<MapPOIItem> MapPoI = new ArrayList<>();
                for (int i=0;i<sl.size();i++)
                {
                    Store s = sl.get(i);
                    s.setdis(distanceBetweenLoc(s.getLat(),s.getLon()));
                    MapPo.add(MapPoint.mapPointWithGeoCoord(s.getLat(),s.getLon()));
                    MapPoI.add(new MapPOIItem());
                    MapPOIItem mpmp = MapPoI.get(i);
                    mpmp.setItemName(s.getName());
                    mpmp.setTag(i);
                    mpmp.setMapPoint(MapPo.get(i));
                    mpmp.setMarkerType(MapPOIItem.MarkerType.BluePin);
                    mpmp.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                    mapView.addPOIItem(mpmp);
                }
                String s;
                s=Integer.toString(distanceBetween(MapPo.get(0)));
                Toast.makeText(MapActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapPoint CMP = CurrPoint;
                StringBuffer result =new StringBuffer("daummaps://route?sp=");
                result.append(CMP.getMapPointGeoCoord().latitude);
                result.append(",");
                result.append(CMP.getMapPointGeoCoord().longitude);
                result.append("&ep=");
                result.append(DEFAULT_MARKER_POINT1.getMapPointGeoCoord().latitude);
                result.append(",");
                result.append(DEFAULT_MARKER_POINT1.getMapPointGeoCoord().longitude);
                result.append("&by=FOOT");
                Uri uri = Uri.parse(result.toString());
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        onFinishReverseGeoCoding(s);

    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    private void onFinishReverseGeoCoding(String result) {
        TextView localtext = (TextView) findViewById(texttext);
        Toast.makeText(MapActivity.this, "현재 위치 : " + result, Toast.LENGTH_SHORT).show();
        localtext.setText(result);
        Intent i = new Intent(MapActivity.this, MainActivity.class);
        i.putExtra("my_address", result);
        startActivity(i);
    }

    public int distanceBetween(MapPoint MP) {
        MapPoint CMP = CurrPoint;
        double y = (CMP.getMapPointGeoCoord().latitude - MP.getMapPointGeoCoord().latitude) * 110988.09668599277;
        double x = (CMP.getMapPointGeoCoord().longitude - MP.getMapPointGeoCoord().longitude) * 88359.11356077534;

        return (int)Math.sqrt(y * y + x * x);
    }

    public int distanceBetweenLoc(double lat, double lon) {
        MapPoint CMP = CurrPoint;
        double y = (CMP.getMapPointGeoCoord().latitude - lat) * 110988.09668599277;
        double x = (CMP.getMapPointGeoCoord().longitude - lon) * 88359.11356077534;

        return (int)Math.sqrt(y * y + x * x);
    }
}
