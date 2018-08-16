package irdc.zhihuan1;

/* import相关class */
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
    /* 载入main.xml Layout */
    setContentView(R.layout.layout2);
    
    /* 以findViewById()取得Button对象，并添加onClickListener */ 
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /* new一个Intent对象，并指定要启动的class */
        Intent intent = new Intent();
    	  intent.setClass(zhihuan1_1.this, zhihuan1.class);
    	  
    	  /* 调用一个新的Activity */
        startActivity(intent);
        /* 关闭原本的Activity */
        zhihuan1_1.this.finish();
      }
    });
    
  }
}