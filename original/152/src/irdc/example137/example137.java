package irdc.example137;

import irdc.example137.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Contacts;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class example137 extends Activity
{
  private TextView myTextView1;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    myTextView1 = (TextView) findViewById(R.id.myTextView1);
    
    /* 添加自己实现的PhoneStateListener */
    exPhoneCallListener myPhoneCallListener = 
    new exPhoneCallListener();
    
    /* 获取电话服务 */
    TelephonyManager tm =
    (TelephonyManager) this.getSystemService
    (Context.TELEPHONY_SERVICE);
    
    /* 注册电话通信Listener */
    tm.listen
    (
      myPhoneCallListener,
      PhoneStateListener.LISTEN_CALL_STATE
    );

  }

  /* 内部class类继承PhoneStateListener */
  public class exPhoneCallListener extends PhoneStateListener
  {
    /* 重写onCallStateChanged
    当状态改变时改变myTextView1的文字及颜色 */
    public void onCallStateChanged(int state, String incomingNumber)
    {
      switch (state)
      {
        /* 无任何状态时 */
        case TelephonyManager.CALL_STATE_IDLE:
          myTextView1.setTextColor
          (
            getResources().getColor(R.drawable.red)
          );
          myTextView1.setText("CALL_STATE_IDLE");
          break;
        /* 接起电话时 */
        case TelephonyManager.CALL_STATE_OFFHOOK:
          myTextView1.setTextColor
          (
            getResources().getColor(R.drawable.green)
          );
          myTextView1.setText("CALL_STATE_OFFHOOK");
          break;
        /* 电话进来时 */
        case TelephonyManager.CALL_STATE_RINGING:
          getContactPeople(incomingNumber);
          break;
        default:
          break;
      }
      super.onCallStateChanged(state, incomingNumber);
    }
  }

  private void getContactPeople(String incomingNumber)
  {
    myTextView1.setTextColor(Color.BLUE);
    ContentResolver contentResolver = getContentResolver();
    Cursor cursor = null;

    /* cursor里要放的字段名称 */
    String[] projection = new String[]
    {
      Contacts.People._ID,
      Contacts.People.NAME,
      Contacts.People.NUMBER
    };

    /* 用来电电话号码去找该联系人 */
    cursor = contentResolver.query
    (
      Contacts.People.CONTENT_URI, projection,
      Contacts.People.NUMBER + "=?",
      new String[]
      {
        incomingNumber
      },
      Contacts.People.DEFAULT_SORT_ORDER
    );

    /* 找不到联系人 */
    if (cursor.getCount() == 0)
    {
      myTextView1.setText("unknown Number:" + incomingNumber);
    }
    else if (cursor.getCount() > 0)
    {
      cursor.moveToFirst();
      /* 在projection这个数组里名字是放在第1个位置 */
      String name = cursor.getString(1);
      myTextView1.setText(name + ":" + incomingNumber);
    }
  }
}
