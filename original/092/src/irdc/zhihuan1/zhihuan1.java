package irdc.zhihuan1;

/* import���class */
import irdc.zhihuan1.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class zhihuan1 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ����mylayout.xml Layout */
    setContentView(R.layout.main);
    
    /* ��findViewById()ȡ��Button���󣬲����onClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /* newһ��Intent���󣬲�ָ��Ҫ������class */
        Intent intent = new Intent();
    	  intent.setClass(zhihuan1.this, zhihuan1_1.class);
    	  
    	  /* ����һ���µ�Activity */
    	  startActivity(intent);
    	  /* �ر�ԭ����Activity */
    	  zhihuan1.this.finish();
      }
    });
  }
}