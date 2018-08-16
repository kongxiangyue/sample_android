package irdc.example133;

import irdc.example133.R;
import android.app.Activity; 
import android.app.PendingIntent;
import android.content.Intent; 
import android.database.Cursor; 
import android.net.Uri; 
import android.os.Bundle; 
import android.provider.Contacts.People; 
import android.telephony.gsm.SmsManager;
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 
import android.widget.Toast;

public class example133 extends Activity 
{ 
  private TextView mTextView01;
  private TextView mTextView3;
  private TextView mTextView5;
  private Button mButton01;
  private Button mButton02;
  /*������strMessageΪString*/
  String strMessage;
   
  private static final int PICK_CONTACT_SUBACTIVITY = 2; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mButton01 = (Button)findViewById(R.id.myButton1);
    mTextView3 = (TextView)findViewById(R.id.myTextView3);
    mButton02 = (Button)findViewById(R.id.myButton2);
    mTextView5= (TextView)findViewById(R.id.myTextView5);
    
    /*���õ�һ��Button�¼�*/
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        
        Uri uri = Uri.parse("content://contacts/people"); 
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        /*ȥߢȡmTextView3�������*/
        strMessage = mTextView3.getText().toString();
        
        startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
      }
    }); 
    
    /*���õڶ���Button�¼�*/
    mButton02.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        Uri uri = Uri.parse("content://contacts/people"); 
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        /*ȥߢȡmTextView5�������*/
        strMessage = mTextView5.getText().toString();
        
        startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY); 
      } 
    }); 
  } 

  @Override 
  protected void onActivityResult 
  (int requestCode, int resultCode, Intent data) 
  { 
    // TODO Auto-generated method stub 
    switch (requestCode) 
    {  
      case PICK_CONTACT_SUBACTIVITY: 
        final Uri uriRet = data.getData(); 
        if(uriRet != null) 
        { 
          try 
          { 
            /* ����Ҫ��android.permission.READ_CONTACTSȨ�� */ 
            Cursor c = managedQuery(uriRet, null, null, null, null); 
            c.moveToFirst(); 
            /*ץȡͨѶ¼������*/
            String strName =  
            c.getString(c.getColumnIndexOrThrow(People.NAME)); 
            /*ץȡͨѶ¼�ĵ绰*/
            String strPhone =  
            c.getString(c.getColumnIndexOrThrow(People.NUMBER)); 
            
            /*����Ҫ�ĸ�ͨѶ¼��ĵ绰*/
            String strDestAddress = strPhone; 
            System.out.println(strMessage);
            SmsManager smsManager = SmsManager.getDefault();
            
            PendingIntent mPI = PendingIntent.getBroadcast
            (example133.this, 0, new Intent(), 0);
            /*�ĳ�����*/
            smsManager.sendTextMessage
            (
              strDestAddress, null, strMessage, mPI, null
            );
            /*��Toast��ʾ������*/
            Toast.makeText
            (
              example133.this,
              getString(R.string.str_msg)+strName,
              Toast.LENGTH_SHORT
            ).show();
             
           mTextView01.setText(strName+":"+strPhone); 
          } 
          catch(Exception e) 
          {             
            mTextView01.setText(e.toString()); 
            e.printStackTrace(); 
          } 
        } 
        break; 
    }   
    super.onActivityResult(requestCode, resultCode, data);    
  } 
}
