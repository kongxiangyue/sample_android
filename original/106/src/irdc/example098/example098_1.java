package irdc.example098;

/* import相关class */
import android.app.Activity; 
import android.os.Bundle; 
import android.widget.Toast;

/* 当user点击Notification留言条时，会运行的Activity */
public class example098_1 extends Activity 
{ 
  @Override 
  protected void onCreate(Bundle savedInstanceState) 
  {  
    super.onCreate(savedInstanceState); 
    
    /* 发出Toast */
    Toast.makeText(example098_1.this,
                      "模拟实现切换登录状态",
                      Toast.LENGTH_SHORT
                     ).show();
    finish();
  } 
}
