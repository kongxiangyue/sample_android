package irdc.example115;

import irdc.example115.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class example115 extends Activity
{
  /* ������ֻҪ����һ�Σ��ͻ����պ󿪻�ʱ�Զ����� */
  private TextView mTextView01; 
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /* Ϊ�˿���ʾ�⣬������Ի�ӭ��TextView������Ϊչʾ */
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mTextView01.setText(R.string.str_welcome);
  }
}
