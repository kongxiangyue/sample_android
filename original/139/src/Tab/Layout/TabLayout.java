package Tab.Layout;
import android.app.Activity;  
import android.content.SharedPreferences;  
import android.os.Bundle;  
import android.widget.CheckBox;  
import android.widget.CompoundButton;  
import android.widget.EditText; 

public class TabLayout extends Activity {
    /** Called when the activity is first created. */
    private final String PREFERENCES_NAME = "userinfo";  
    private EditText username,password;  
    private CheckBox cbRemember;  
      
    private String userName,passWord;   
    private Boolean isRemember = false;  
      
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
          
        initializeViews();  
          
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);  
        username.setText(preferences.getString("UserName", null));  
        cbRemember.setChecked(preferences.getBoolean("Remember", true));  
        if(cbRemember.isChecked()){  
            password.setText(preferences.getString("PassWord", null));  
        }else{  
            password.setText(null);  
        }  
    }  
      
    /** 
     * ³õÊ¼»¯UI¿Ø¼þ 
     */  
    private void initializeViews(){  
        username = (EditText)findViewById(R.id.username);  
        password = (EditText)findViewById(R.id.password);  
          
        cbRemember = (CheckBox)findViewById(R.id.ischecked);  
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
              
            @Override  
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  
                isRemember = isChecked;  
            }  
        });  
    }  
    @Override  
    public void onStop() {  
        super.onStop();  
        SharedPreferences agPreferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);  
        SharedPreferences.Editor editor = agPreferences.edit();  
          
        userName = username.getText().toString();  
        passWord = password.getText().toString();  
        editor.putString("UserName", userName);  
        editor.putString("PassWord", passWord);  
        editor.putBoolean("Remember", isRemember);  
        editor.commit(); 
}
}