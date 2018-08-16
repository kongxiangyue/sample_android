package irdc.example106;

import irdc.example106.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class example106 extends Activity
{
  private TextView mTextView01;
  private Button mButton01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    if(getRequestedOrientation()==-1)
    {
      mTextView01.setText(getResources().getText
      (R.string.str_err_1001));
    }
    
    /* �������ť��ת��Ļ���� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        /* ����һ����дgetRequestedOrientation */
        
        /* ���޷�ȡ��screenOrientation���� */
        if(getRequestedOrientation()==-1)
        {
          /* ��ʾ�޷����л�����ת���ܣ����޷��б�Orientation */
          mTextView01.setText(getResources().getText
          (R.string.str_err_1001));
        }
        else
        {
          if(getRequestedOrientation()==
             ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
          {
            /* ������Ϊ���ţ������Ϊ���ų��� */
            setRequestedOrientation
            (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          }
          else if(getRequestedOrientation()==
                  ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
          {
            /* ������Ϊ���ţ������Ϊ���ų��� */
            setRequestedOrientation
            (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          }
        }
      }      
    });
  }

  @Override
  public void setRequestedOrientation(int requestedOrientation)
  {
    // TODO Auto-generated method stub
    
    /* �ж�Ҫ���ĵķ�����Toast��ʾ */
    switch(requestedOrientation)
    {
      /* ����ΪLANDSCAPE */
      case (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg1).toString(),
          false
        );
        break;
      /* ����ΪPORTRAIT */
      case (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg2).toString(),
          false
        );
        break;
    }
    super.setRequestedOrientation(requestedOrientation);
  }

  @Override
  public int getRequestedOrientation()
  {
    // TODO Auto-generated method stub
    
    /* ����дgetRequestedOrientation��������ȡ�õ�����Ļ�ķ��� */
    return super.getRequestedOrientation();
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(example106.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(example106.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}
