package irdc.zhihuan1;

/* import���class */
import irdc.zhihuan1.R;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class zhihuan1_1 extends Activity
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ����main.xml Layout */
    setContentView(R.layout.layout2);
    
    /* ��findViewById()ȡ��Button���󣬲����onClickListener */ 
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /* newһ��Intent���󣬲�ָ��Ҫ������class */
        Intent intent = new Intent();
    	  intent.setClass(zhihuan1_1.this, zhihuan1.class);
    	  
    	  /* ����һ���µ�Activity */
        startActivity(intent);
        /* �ر�ԭ����Activity */
        zhihuan1_1.this.finish();
      }
    });
    
  }
}