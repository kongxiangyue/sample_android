package com.example188;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example188.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class example188 extends MapActivity {
	
    MapView map;
    
    MapController ctrlMap;
    Button inBtn;
    Button outBtn;
    ToggleButton switchMap; 
    
    @Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView)findViewById(R.id.myMapView); 
        List<Overlay> overlays = map.getOverlays();
        MyLocationOverlay myLocation = new MyLocationOverlay(this,map);
        myLocation.enableMyLocation();
        overlays.add(myLocation);
        
        ctrlMap = map.getController();
        inBtn = (Button)findViewById(R.id.in);
        outBtn = (Button)findViewById(R.id.out);
        switchMap = (ToggleButton)findViewById(R.id.switchMap);
        
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                case R.id.in:
                    ctrlMap.zoomIn();
                    break;
                case R.id.out:
                    ctrlMap.zoomOut();
                    break;
                default:
                    break;
                }
            }
        };
        inBtn.setOnClickListener(listener);
        outBtn.setOnClickListener(listener);   
        switchMap.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cBtn, boolean isChecked) {
                if (isChecked == true) {
                    map.setSatellite(true);
                } else {
                    map.setSatellite(false);
                }
            }
        });
        
        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(context);
        //String provider = LocationManager.GPS_PROVIDER;
        
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);
        
        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);
        locationManager.requestLocationUpdates(provider, 2000, 10,
        		locationListener);
    }
   private final LocationListener locationListener = new LocationListener() {
    	public void onLocationChanged(Location location) {
    	updateWithNewLocation(location);
    	}
    	public void onProviderDisabled(String provider){
    	updateWithNewLocation(null);
    	}
    	public void onProviderEnabled(String provider){ }
    	public void onStatusChanged(String provider, int status,
    	Bundle extras){ }
    };
    private void updateWithNewLocation(Location location) {
    	String latLongString;
    	TextView myLocationText;
    	myLocationText = (TextView)findViewById(R.id.myLocationText);
    	if (location != null) {
    	    double lat = location.getLatitude();
    	    double lng = location.getLongitude();
    	    latLongString = "纬度:" + lat + "\n经度:" + lng;
    	    
    	    ctrlMap.animateTo(new GeoPoint((int)(lat*1E6),(int)(lng*1E6)));
    	} else {
    	    latLongString = "获取失败！";
    	}
    	myLocationText.setText("现在的位置是:\n" +
    	latLongString);
        
    }
}