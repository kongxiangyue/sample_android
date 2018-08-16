package com.example125;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	private SharedPreferences sp;
	private Editor editor;
	private final static String SP_NAME = "mydata";
	private final static int MODE = Context.MODE_WORLD_READABLE
			+ Context.MODE_WORLD_WRITEABLE;

	public SharedPreferencesUtil(Context context) {
		sp = context.getSharedPreferences(SP_NAME, MODE);
		editor = sp.edit();
	}

	public boolean save(String key, String value) {
		editor.putString(key, value);
		// 不要忘了加commit
		return editor.commit();
	}

	public String read(String key) {
		String str = null;
		str = sp.getString(key, null);
		return str;
	}
}
