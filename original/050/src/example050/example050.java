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
  /*���������������(��ť��༭����)*/
  private Button mButton;
  private EditText mEditText;
  
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*ͨ��findViewById()ȡ�ö��� */
    mButton=(Button)findViewById(R.id.myButton);
    mEditText=(EditText)findViewById(R.id.myEditText);
    
    /*����onClickListener��Button��������onClick�¼�*/
    mButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
      // TODO Auto-generated method stub
    
      /*�����ַ���������ȡ���û������EditText�ַ���*/
      Editable Str;
      Str=mEditText.getText();
      
      /*ʹ��ϵͳ��׼�� makeText()��ʽ������Toast��Ϣ*/
      Toast.makeText(
        example050.this,
        "���Ը��  "+Str.toString()+"���ʹﱦ��������",
        Toast.LENGTH_LONG).show();
      
      /*���EditText*/
      mEditText.setText("");
      }   
    });
  }
}
