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
    
    /* ����Լ�ʵ�ֵ�PhoneStateListener */
    exPhoneCallListener myPhoneCallListener = 
    new exPhoneCallListener();
    
    /* ��ȡ�绰���� */
    TelephonyManager tm =
    (TelephonyManager) this.getSystemService
    (Context.TELEPHONY_SERVICE);
    
    /* ע��绰ͨ��Listener */
    tm.listen
    (
      myPhoneCallListener,
      PhoneStateListener.LISTEN_CALL_STATE
    );

  }

  /* �ڲ�class��̳�PhoneStateListener */
  public class exPhoneCallListener extends PhoneStateListener
  {
    /* ��дonCallStateChanged
    ��״̬�ı�ʱ�ı�myTextView1�����ּ���ɫ */
    public void onCallStateChanged(int state, String incomingNumber)
    {
      switch (state)
      {
        /* ���κ�״̬ʱ */
        case TelephonyManager.CALL_STATE_IDLE:
          myTextView1.setTextColor
          (
            getResources().getColor(R.drawable.red)
          );
          myTextView1.setText("CALL_STATE_IDLE");
          break;
        /* ����绰ʱ */
        case TelephonyManager.CALL_STATE_OFFHOOK:
          myTextView1.setTextColor
          (
            getResources().getColor(R.drawable.green)
          );
          myTextView1.setText("CALL_STATE_OFFHOOK");
          break;
        /* �绰����ʱ */
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

    /* cursor��Ҫ�ŵ��ֶ����� */
    String[] projection = new String[]
    {
      Contacts.People._ID,
      Contacts.People.NAME,
      Contacts.People.NUMBER
    };

    /* ������绰����ȥ�Ҹ���ϵ�� */
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

    /* �Ҳ�����ϵ�� */
    if (cursor.getCount() == 0)
    {
      myTextView1.setText("unknown Number:" + incomingNumber);
    }
    else if (cursor.getCount() > 0)
    {
      cursor.moveToFirst();
      /* ��projection��������������Ƿ��ڵ�1��λ�� */
      String name = cursor.getString(1);
      myTextView1.setText(name + ":" + incomingNumber);
    }
  }
}
