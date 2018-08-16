package com.example196;

import com.example196.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Map extends Activity {
	
	EditText etinput;
	Button btquery;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setListeners();
    }
	private void setListeners() {
		// TODO Auto-generated method stub
		btquery.setOnClickListener(query);
	}
	private void findViews() {
		// TODO Auto-generated method stub
		etinput=(EditText)findViewById(R.id.edittext_main_inputaddress);
        btquery=(Button)findViewById(R.id.button_main_query);
	}
	
	Button.OnClickListener query=new Button.OnClickListener()
	{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bundle bundle=new Bundle();
			Log.d("Map_query",etinput.getText().toString());
			bundle.putString("address",etinput.getText().toString());
			Intent intent=new Intent();
			intent.setClass(Map.this,MyMap.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};
}