package com.sinxiao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyRadioGroup extends android.widget.RadioGroup implements OnCheckedChangeListener {

	 private String mValue;
	 
	 public MyRadioGroup(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  this.setOnCheckedChangeListener(this);
	 }

	 public MyRadioGroup(Context context) {
	  super(context);
	  this.setOnCheckedChangeListener(this);
	 }
	 private String tag ="===myRadioGroup";
	 // 设置子控件的值
	 private void setChildValue(){
	  int n = this.getChildCount();
	  Log.d(tag, "the n is "+n);
	  for(int i=0;i<n;i++){
	   MyRadioButton radio = (MyRadioButton)this.getChildAt(i);
	   if(radio.getValue().equals(this.mValue)){
	    radio.setChecked(true);
	   }else{
	    radio.setChecked(false);
	   }
	  }
	 }
	 // 获取子类的值
	 private void getChildValue(){
	  int n = this.getChildCount();
	  for(int i=0;i<n;i++){
	   MyRadioButton radio = (MyRadioButton)this.getChildAt(i);
	   if(radio.isChecked()){
	    this.mValue=radio.getValue();
	   }
	  }
	 }
	 
	 public void setTheValue(String value) {
	  this.mValue = value;
	 }
	 
	 public String getTheValue(){
	  getChildValue();
	  return this.mValue;
	  }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		setChildValue(); 
	}
}
