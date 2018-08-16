package irdc.example053;

/* import���class */
import irdc.example053.R;

import java.io.File;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class example053 extends Activity
{
  /*�����������*/
  private Button mButton;
  private EditText mKeyword;
  private TextView mResult;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ����main.xml Layout */
    setContentView(R.layout.main);
    
    /* ��ʼ������ */
    mKeyword=(EditText)findViewById(R.id.mKeyword);
    mButton=(Button)findViewById(R.id.mButton);
    mResult=(TextView) findViewById(R.id.mResult);
    
    /* ��mButton���onClickListener */
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
      /*ȡ������Ĺؼ���*/
        String keyword = mKeyword.getText().toString();
        if(keyword.equals(""))
        {
          mResult.setText("�ϴ�����Ĺؼ��ֲ���Ϊ��!!");
        }
        else
        {
          mResult.setText(searchFile(keyword));
        }
      }
    });
  }
  
  /* �����ļ���method */
  private String searchFile(String keyword)
  {
    String result="";
    File[] files=new File("/").listFiles();
    for( File f : files )
    {
      if(f.getName().indexOf(keyword)>=0)
      {
        result+=f.getPath()+"\n";
      }
    }
    if(result.equals("")) result="�ϴ��Ҳ����ļ�!!";
    return result;
  }
}
