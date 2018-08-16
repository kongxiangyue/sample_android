package irdc.example136;

import irdc.example136.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class example136 extends Activity
{
  /* 创建两个mServiceReceiver对象，作为类成员变量 */
  private mServiceReceiver mReceiver01, mReceiver02;
  private Button mButton1;
  private TextView mTextView01;
  private EditText mEditText1, mEditText2;
  
  /* 自定义ACTION常数，作为广播的Intent Filter识别常数 */
  private String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
  private String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    /* 电话号码 */
    mEditText1 = (EditText) findViewById(R.id.myEditText1);
    
    /* 短信内容 */
    mEditText2 = (EditText) findViewById(R.id.myEditText2);
    mButton1 = (Button) findViewById(R.id.myButton1);
    
    //mEditText1.setText("+886935123456");
    /* 设置默认为5556表示第二个模拟器的Port */
    mEditText1.setText("5556");
    mEditText2.setText("Hello AAA!");
    
    /* 发送SMS短信按钮事件处理 */
    mButton1.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* 欲发送的电话号码 */
        String strDestAddress = mEditText1.getText().toString();
        
        /* 欲发送的短信内容 */
        String strMessage = mEditText2.getText().toString();
        
        /* 创建SmsManager对象 */
        SmsManager smsManager = SmsManager.getDefault();
        
        // TODO Auto-generated method stub
        try
        {
          /* 创建自定义Action常数的Intent(给PendingIntent参数之用) */
          Intent itSend = new Intent(SMS_SEND_ACTIOIN);
          Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);
          
          /* sentIntent参数为传送后接受的广播信息PendingIntent */
          PendingIntent mSendPI = PendingIntent.getBroadcast
          (getApplicationContext(), 0, itSend, 0);
          
          /* deliveryIntent参数为送达后接受的广播信息PendingIntent */
          PendingIntent mDeliverPI = PendingIntent.getBroadcast
          (getApplicationContext(), 0, itDeliver, 0);
          
          /* 发送SMS短信，注意倒数的两个PendingIntent参数 */
          smsManager.sendTextMessage
          (strDestAddress, null, strMessage, mSendPI, mDeliverPI);
          
          mTextView01.setText(R.string.str_sms_sending);
        }
        catch(Exception e)
        {
          mTextView01.setText(e.toString());
          e.printStackTrace();
        }
      }
    });
  }
  
  /* 自定义mServiceReceiver覆盖BroadcastReceiver聆听短信状态信息 */
  public class mServiceReceiver extends BroadcastReceiver
  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      // TODO Auto-generated method stub
      
      try
      {
        /* android.content.BroadcastReceiver.getResultCode()方法 */
        switch(getResultCode())
        {
          case Activity.RESULT_OK:
            /* 发送短信成功 */
            //mTextView01.setText(R.string.str_sms_sent_success);
            mMakeTextToast
            (
              getResources().getText
              (R.string.str_sms_sent_success).toString(),
              true
            );
            break;
          case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
            /* 发送短信失败 */
            //mTextView01.setText(R.string.str_sms_sent_failed);
            mMakeTextToast
            (
              getResources().getText
              (R.string.str_sms_sent_failed).toString(),
              true
            );
            break;
          case SmsManager.RESULT_ERROR_RADIO_OFF:
            break;
          case SmsManager.RESULT_ERROR_NULL_PDU:
            break;
        }        
      }
      catch(Exception e)
      {
        mTextView01.setText(e.toString());
        e.getStackTrace();
      }
    }
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(example136.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(example136.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    
    /* 自定义IntentFilter为SENT_SMS_ACTIOIN Receiver */
    IntentFilter mFilter01;
    mFilter01 = new IntentFilter(SMS_SEND_ACTIOIN);
    mReceiver01 = new mServiceReceiver();
    registerReceiver(mReceiver01, mFilter01);
    
    /* 自定义IntentFilter为DELIVERED_SMS_ACTION Receiver */
    mFilter01 = new IntentFilter(SMS_DELIVERED_ACTION);
    mReceiver02 = new mServiceReceiver();
    registerReceiver(mReceiver02, mFilter01);
    
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    
    /* 取消注册自定义Receiver */
    unregisterReceiver(mReceiver01);
    unregisterReceiver(mReceiver02);
    
    super.onPause();
  }
}
