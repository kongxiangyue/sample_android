package irdc.dengdai092;
import irdc.dengdai092.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dengdai092 extends Activity
{
  private Button mButton1;
  private TextView mTextView1;
  public ProgressDialog myDialog = null;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton1 =(Button) findViewById(R.id.myButton1);
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    mButton1.setOnClickListener(myShowProgressBar);
  }
  
  Button.OnClickListener myShowProgressBar = 
  new Button.OnClickListener()
  {
    public void onClick(View arg0)
    {
      final CharSequence strDialogTitle = 
            getString(R.string.str_dialog_title);
      final CharSequence strDialogBody =
            getString(R.string.str_dialog_body);
      
      // ��ʾProgress�Ի���
      myDialog = ProgressDialog.show
                 (
                   dengdai092.this,
                   strDialogTitle,
                   strDialogBody, 
                   true
                 );
      
      mTextView1.setText(strDialogBody);
      
      new Thread()
      { 
        public void run()
        { 
          try
          { 
            /* ������д��Ҫ�������еĳ���Ƭ�� */
            /* Ϊ�����Կ���Ч��������ͣ3����Ϊʾ�� */
            sleep(3000);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
          finally
          {
            // ж����������myDialog����
            myDialog.dismiss();
          }
        }
      }.start(); /* ��ʼ���������߳� */
    } 
  };
}
