package com.example125;

import com.example125.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class example125 extends Activity {
	private Button saveBtn;
	private Button readBtn;
	private EditText inputEv;
	private EditText showEv;
	private SharedPreferencesUtil util;

	/**
	 * 给控件初始化
	 */
	public void init() {
		util = new SharedPreferencesUtil(this);
		saveBtn = (Button) findViewById(R.id.save_btn);
		readBtn = (Button) findViewById(R.id.read_btn);
		inputEv = (EditText) findViewById(R.id.input_et);
		showEv = (EditText) findViewById(R.id.showinfo_et);
		// 设置监听
		setListener();
	}

	/**
	 * 给Button加监听事件
	 */
	public void setListener() {
		saveBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean b = util.save("mykey", inputEv.getText().toString());
				String msg;
				if (b) {
					msg = "保存成功";
				} else {
					msg = "保存失败";
				}
				Toast.makeText(example125.this, msg,
						Toast.LENGTH_SHORT).show();
			}
		});

		readBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.out.println("read...");
				String value = util.read("mykey");
				showEv.setText(value);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}
}