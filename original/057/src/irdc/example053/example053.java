package irdc.example053;

/* import相关class */
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
  /*声明对象变量*/
  private Button mButton;
  private EditText mKeyword;
  private TextView mResult;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);
    
    /* 初始化对象 */
    mKeyword=(EditText)findViewById(R.id.mKeyword);
    mButton=(Button)findViewById(R.id.mButton);
    mResult=(TextView) findViewById(R.id.mResult);
    
    /* 将mButton添加onClickListener */
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
      /*取得输入的关键字*/
        String keyword = mKeyword.getText().toString();
        if(keyword.equals(""))
        {
          mResult.setText("老大，这里的关键字不能为空!!");
        }
        else
        {
          mResult.setText(searchFile(keyword));
        }
      }
    });
  }
  
  /* 搜索文件的method */
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
    if(result.equals("")) result="老大，找不到文件!!";
    return result;
  }
}
