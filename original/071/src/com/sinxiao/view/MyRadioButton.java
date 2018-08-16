package com.sinxiao.view;

import com.sinxiao.myview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MyRadioButton extends android.widget.RadioButton implements OnCheckedChangeListener {

	 private String mValue;

	 public MyRadioButton(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	 }
	 
	 public String getValue() {
	  return this.mValue;
	 }

	 public void setValue(String value) {
	  this.mValue = value;
	 }
	 public MyRadioButton(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  try {
	   /**
	    * 跟values/attrs.xml里面定义的属性绑定
	    */
	   TypedArray a = context.obtainStyledAttributes(attrs,
	                 R.styleable.RadioButton);
	   this.mValue = a.getString(R.styleable.RadioButton_value);
	   a.recycle();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  setOnCheckedChangeListener(this);
	 }

	 public MyRadioButton(Context context) {
	  super(context);
	 }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		MyRadioGroup group = (MyRadioGroup) getParent();
		group.setTheValue(this.getValue());
		Log.d("-------Main", "the new value is ===>"+this.getValue());
	}
}
