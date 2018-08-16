package com.example197;

import java.util.List;

import com.example197.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class GooglemapActivity extends MapActivity {
	/** Called when the activity is first created. */
	Button locBn;
	RadioGroup mapType;
	MapView mv;
	EditText etLng, etLat;
	MapController controller;
	Bitmap posBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		posBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon);
		mv = (MapView) findViewById(R.id.mv);
		etLng = (EditText) findViewById(R.id.lng);
		etLat = (EditText) findViewById(R.id.lat);
		// 显示放大缩小控制按钮
		mv.setBuiltInZoomControls(true);
		controller = mv.getController();
		locBn = (Button) findViewById(R.id.loc);
		locBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String lng = etLng.getEditableText().toString().trim();
				String lat = etLat.getEditableText().toString().trim();
				if (lng.equals("") || lat.equals("")) {
					Toast.makeText(GooglemapActivity.this, "输入有效经度维度",
							Toast.LENGTH_LONG).show();
				} else {
					double dlong = Double.parseDouble(lng);
					double dLat = Double.parseDouble(lat);
					UpdateMapView(dlong, dLat);
				}
			}

		});
		locBn.performClick();
		mapType = (RadioGroup) findViewById(R.id.rg);
		mapType.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.normal:
					mv.setSatellite(false);
					break;
				case R.id.satellite:
					mv.setSatellite(true);
					break;
				}
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}

	private void UpdateMapView(double dlong, double dLat) {
	GeoPoint gp = new GeoPoint((int)(dLat*1E6), (int)(dlong*1E6));
	mv.displayZoomControls(true);
	controller.animateTo(gp);
	List<Overlay> ol = mv.getOverlays();
	ol.clear();
	ol.add(new PosOverLay(gp, posBitmap));
	}
}