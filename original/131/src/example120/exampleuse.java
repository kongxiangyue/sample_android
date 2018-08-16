package example120;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/*
 * to access from: data/data/com.android.SharedPreferences/share_prefs
 */
public class exampleuse extends Activity {
	public final static String COLUMN_NAME ="name";
	public final static String COLUMN_MOBILE ="mobile";
	
	exampleHelper sp;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        sp = new exampleHelper(this, "contacts");
        
        //1. to store some value
        sp.putValue(COLUMN_NAME, "Mr WANG");
        sp.putValue(COLUMN_MOBILE, "1506907XXXX");
        
        
        //2. to fetch the value
        String name = sp.getValue(COLUMN_NAME);
        String mobile = sp.getValue(COLUMN_MOBILE);
        
        TextView tv = new TextView(this);
        tv.setText("NAME:"+ name + "\n" + "MOBILE:" + mobile);
        
        setContentView(tv);
    }
}