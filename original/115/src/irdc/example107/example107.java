package irdc.example107;

import irdc.example107.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class example107 extends Activity
{
  private TextView mTextView1;
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    /*ͨ��findViewById����������TextView����*/
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    mTextView1.setText("�ȴ�������Ϣ��...");
  }
}
