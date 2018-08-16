package example050;
import irdc.example050.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class example050 extends Activity 
{
  /** Called when the activity is first created. */
  /*声明两个对象变量(按钮与编辑文字)*/
  private Button mButton;
  private EditText mEditText;
  
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*通过findViewById()取得对象 */
    mButton=(Button)findViewById(R.id.myButton);
    mEditText=(EditText)findViewById(R.id.myEditText);
    
    /*设置onClickListener给Button对象聆听onClick事件*/
    mButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
      // TODO Auto-generated method stub
    
      /*声明字符串变量并取得用户输入的EditText字符串*/
      Editable Str;
      Str=mEditText.getText();
      
      /*使用系统标准的 makeText()方式来产生Toast信息*/
      Toast.makeText(
        example050.this,
        "你的愿望  "+Str.toString()+"已送达宝贝的信箱",
        Toast.LENGTH_LONG).show();
      
      /*清空EditText*/
      mEditText.setText("");
      }   
    });
  }
}
