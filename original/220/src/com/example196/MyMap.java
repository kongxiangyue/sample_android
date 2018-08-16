package com.example196;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example196.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MyMap extends MapActivity{//程序列表中要添加联网的权限还要加一个类库
	
	MapView mapview;
    private MapController mapcontroller;
    private GeoPoint geopoint;
    protected String addressname;
    
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);
		//用于显示地图上的一个ViewGroup
		mapview=(MapView)findViewById(R.id.mapview_mymap_display);
		
		
		Bundle bundle=getIntent().getExtras();
		Log.d("MyMap_Oncreate_bundle",bundle+"");
		addressname=bundle.getString("address");
		Log.d("MyMap_oncreate",addressname);
		//使得这个view可以获得点击事件
		mapview.setClickable(true);
		//是否可以设置自动缩放设置
		mapview.setBuiltInZoomControls(true);
		
		//获取控制缩放的操作对象
		mapcontroller=mapview.getController();
		//通过系统默认区域设置进行地图的定位
		Geocoder geocoder=new Geocoder(this);
		
		mapview.setTraffic(true);
		try
		{
				List<Address> addresses=geocoder.getFromLocationName(addressname,2);
			Log.d("MyMap_oncreate_addressname3",addressname);
			geopoint = new GeoPoint(
					(int) (addresses.get(0).getLatitude() * 1E6),
					(int) (addresses.get(0).getLongitude() * 1E6));
				MyOverlay myoverlay=new MyOverlay();
			    
			    mapview.getOverlays().add(myoverlay);
			    mapcontroller.setZoom(20);
			    mapcontroller.animateTo(geopoint);
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	class MyOverlay extends Overlay
	{
		@Override
		public boolean draw(Canvas canvas, MapView mapview, boolean shadow, long when) {
			// TODO Auto-generated method stub
			Paint paint=new Paint();
			Point screenPoint=new Point();
			mapview.getProjection().toPixels(geopoint, screenPoint);//??经纬度坐标和屏幕像素坐标的一个映射
			//并且这个映射可以把地理上的经纬度转换在屏幕上的像素点
			Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.flag1);
			canvas.drawBitmap(bitmap,screenPoint.x,screenPoint.y, paint);
			canvas.drawText(addressname,screenPoint.x,screenPoint.y, paint);
			return super.draw(canvas, mapview, shadow, when);
		}
	}
}
