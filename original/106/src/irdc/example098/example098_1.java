package irdc.example098;

/* import���class */
import android.app.Activity; 
import android.os.Bundle; 
import android.widget.Toast;

/* ��user���Notification������ʱ�������е�Activity */
public class example098_1 extends Activity 
{ 
  @Override 
  protected void onCreate(Bundle savedInstanceState) 
  {  
    super.onCreate(savedInstanceState); 
    
    /* ����Toast */
    Toast.makeText(example098_1.this,
                      "ģ��ʵ���л���¼״̬",
                      Toast.LENGTH_SHORT
                     ).show();
    finish();
  } 
}
