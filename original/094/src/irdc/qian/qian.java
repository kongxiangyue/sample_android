package irdc.qian;

/* import���class */
import irdc.qian.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class qian extends Activity 
{
  private EditText et;
  private RadioButton rb1;
  private RadioButton rb2;
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ����main.xml Layout */
    setContentView(R.layout.main);
    
    /* ��findViewById()ȡ��Button���󣬲�����onClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /*ȡ�����������*/
        et = (EditText) findViewById(R.id.height);
        double height=Double.parseDouble(et.getText().toString());
        /*ȡ��ѡ����Ա�*/
        String sex="";
        rb1 = (RadioButton) findViewById(R.id.sex1);
        rb2 = (RadioButton) findViewById(R.id.sex2);
        if(rb1.isChecked())
        {
          sex="M";
        }
        else
        {
          sex="F";
        }    
        
        /*newһ��Intent���󣬲�ָ��class*/
        Intent intent = new Intent();
        intent.setClass(qian.this,qian_1.class);
        
        /*newһ��Bundle���󣬲���Ҫ���ݵ����ݴ���*/
        Bundle bundle = new Bundle();
        bundle.putDouble("height",height);
        bundle.putString("sex",sex);
      
        /*��Bundle����assign��Intent*/
        intent.putExtras(bundle);
      
        /*����Activity EX03_11_1*/
        startActivityForResult(intent,0);
      }
    });
  }
  
  /* ���� onActivityResult()*/
  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data)
  {
    switch (resultCode)
    { 
      case RESULT_OK:
        /* ȡ������Activity2�����ݣ�����ʾ�ڻ����� */  
        Bundle bunde = data.getExtras();
        String sex = bunde.getString("sex");
        double height = bunde.getDouble("height");
        
        et.setText(""+height);
        if(sex.equals("M"))
        {
          rb1.setChecked(true);
        }
        else
        {
          rb2.setChecked(true);
        }
        break;
      default:
        break;
     } 
   } 
}