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

public class AddressActivity extends MapActivity {
	/** Called when the activity is first created. */
	Button locBn;
	MapView mv;
	EditText address_value;
	MapController controller;
	Bitmap posBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address);
		posBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon);
		mv = (MapView) findViewById(R.id.mv);
		address_value = (EditText) findViewById(R.id.address_value);
		// 显示放大缩小控制按钮
		mv.setBuiltInZoomControls(true);
		controller = mv.getController();
		locBn = (Button) findViewById(R.id.location);
		locBn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String address = address_value.getEditableText().toString().trim();
				if (address.equals("")) {
					Toast.makeText(AddressActivity.this, "输入有效地址",
							Toast.LENGTH_LONG).show();
				} else {
					double[] result=ConvertUtil.getLocationInfo(address);
					UpdateMapView(result[0], result[1]);
				}
			}

		});
		locBn.performClick();
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