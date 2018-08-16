package com.ExPanel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class Panel extends LinearLayout implements GestureDetector.OnGestureListener{
	
	public interface PanelClosedEvent {
		void onPanelClosed(View panel);
	}
	
	public interface PanelOpenedEvent {
		void onPanelOpened(View panel);
	}
	
	private final static int HANDLE_WIDTH=30;
	private final static int MOVE_WIDTH=20;
	private Button btnHandler;
	private LinearLayout panelContainer;
	private int mRightMargin=0;
	private Context mContext;
	private GestureDetector mGestureDetector;
	private boolean mIsScrolling=false;
	private float mScrollX;
	private PanelClosedEvent panelClosedEvent=null;
	private PanelOpenedEvent panelOpenedEvent=null;
	
	public Panel(Context context,View otherView,int width,int height) {
		super(context);
		this.mContext=context;
		
		mGestureDetector = new GestureDetector(mContext,this);
		mGestureDetector.setIsLongpressEnabled(false);
		
		LayoutParams otherLP=(LayoutParams) otherView.getLayoutParams();
		otherLP.weight=1;
		otherView.setLayoutParams(otherLP);
		
		LayoutParams lp=new LayoutParams(width, height);
		lp.rightMargin=-lp.width+HANDLE_WIDTH;
		mRightMargin=Math.abs(lp.rightMargin);
		this.setLayoutParams(lp);
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		btnHandler=new Button(context);
		btnHandler.setLayoutParams(new LayoutParams(HANDLE_WIDTH,height));
		//btnHandler.setOnClickListener(handlerClickEvent);
		btnHandler.setOnTouchListener(handlerTouchEvent);
		this.addView(btnHandler);

		panelContainer=new LinearLayout(context);
		panelContainer.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		this.addView(panelContainer);

	}

	private View.OnTouchListener handlerTouchEvent=new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_UP && 
					mIsScrolling==true)
			{
				LayoutParams lp=(LayoutParams) Panel.this.getLayoutParams();
				if (lp.rightMargin >= (-mRightMargin/2)) {
					new AsynMove().execute(new Integer[] { MOVE_WIDTH });
				} 
				else if (lp.rightMargin < (-mRightMargin/2)) {
					new AsynMove().execute(new Integer[] { -MOVE_WIDTH });
				}
			}
			return mGestureDetector.onTouchEvent(event); 
		}
	};


	public void setPanelClosedEvent(PanelClosedEvent event)
	{
		this.panelClosedEvent=event;
	}
	


	public void setPanelOpenedEvent(PanelOpenedEvent event)
	{
		this.panelOpenedEvent=event;
	}
	

	public void fillPanelContainer(View v)
	{
		panelContainer.addView(v);
	}
	

	class AsynMove extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			int times;
			if (mRightMargin % Math.abs(params[0]) == 0)
				times = mRightMargin / Math.abs(params[0]);
			else
				times = mRightMargin / Math.abs(params[0]) + 1;

			for (int i = 0; i < times; i++) {
				publishProgress(params);
				try {
					Thread.sleep(Math.abs(params[0]));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... params) {
			LayoutParams lp = (LayoutParams) Panel.this.getLayoutParams();
			if (params[0] < 0)
				lp.rightMargin = Math.max(lp.rightMargin + params[0],
						(-mRightMargin));
			else
				lp.rightMargin = Math.min(lp.rightMargin + params[0], 0);

			if(lp.rightMargin==0 && panelOpenedEvent!=null){
				panelOpenedEvent.onPanelOpened(Panel.this);
			}
			else if(lp.rightMargin==-(mRightMargin) && panelClosedEvent!=null){
				panelClosedEvent.onPanelClosed(Panel.this);
			}
			Panel.this.setLayoutParams(lp);
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		mScrollX=0;
		mIsScrolling=false;
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		LayoutParams lp = (LayoutParams) Panel.this.getLayoutParams();
		if (lp.rightMargin < 0)
			new AsynMove().execute(new Integer[] { MOVE_WIDTH });
		else if (lp.rightMargin >= 0)
			new AsynMove().execute(new Integer[] { -MOVE_WIDTH });
		return false;
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		mIsScrolling=true;
		mScrollX+=distanceX;
		
		LayoutParams lp=(LayoutParams) Panel.this.getLayoutParams();
		if (lp.rightMargin < -1 && mScrollX > 0) {
			lp.rightMargin = Math.min((lp.rightMargin + (int) mScrollX),0);
			Panel.this.setLayoutParams(lp);
			Log.e("onScroll",lp.rightMargin+"");
		} 
		else if (lp.rightMargin > -(mRightMargin) && mScrollX < 0) {
			lp.rightMargin = Math.max((lp.rightMargin + (int) mScrollX),-mRightMargin);
			Panel.this.setLayoutParams(lp);
		}
		
		if(lp.rightMargin==0 && panelOpenedEvent!=null){
			panelOpenedEvent.onPanelOpened(Panel.this);
		}
		else if(lp.rightMargin==-(mRightMargin) && panelClosedEvent!=null){
			panelClosedEvent.onPanelClosed(Panel.this);
		}
		Log.e("onScroll",lp.rightMargin+"");
		
		return false;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {return false;}
	@Override
	public void onLongPress(MotionEvent e) {}
	@Override
	public void onShowPress(MotionEvent e) {}

}
