package org.lansir;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class SharePrefersActivity extends Activity {
    
	private ListSelectView mListSelectView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListSelectView = (ListSelectView)findViewById(R.id.lsvTest);
        mListSelectView.setHeader("Hello");
        mListSelectView.setContent("world");
        
        
        //AlertDialog ad;
        //PreferenceScreen mPreferenceScreen;
    }
}