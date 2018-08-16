package com.sinxiao.myview;

import com.sinxiao.view.MyRadioButton;
import com.sinxiao.view.MyRadioGroup;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyView extends Activity {
	private String tag ="---myview";
	private MyRadioButton  isPayDepositTrue;
	private MyRadioGroup group  ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        isPayDepositTrue =(MyRadioButton) findViewById(R.id.isPayDepositTrue);
        isPayDepositTrue.setChecked(true);  
        group =(MyRadioGroup) findViewById(R.id.radPayOrNot);
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(android.widget.RadioGroup group1, int checkedId) {
				Log.d(tag, "the value is "+group.getTheValue());
			}
		});
    }
}