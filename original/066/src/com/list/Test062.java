package com.list;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Test062 extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ArrayList<HashMap<String, Object>> users = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> user = new HashMap<String, Object>();
			user.put("img", R.drawable.user);
			user.put("username", "����(" + i+")");
			user.put("age", (1000 * i) + "");
			users.add(user);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this,
                users,// ������Դ
                R.layout.user,//ÿһ��user xml �൱ListView��һ����� 
                new String[] { "img", "username", "age" },

                // �ֱ��Ӧview ��id
                new int[] { R.id.img, R.id.name, R.id.age });

		// 获取listview
		((ListView) findViewById(R.id.users)).setAdapter(saImageItems);
		
	}


	
}