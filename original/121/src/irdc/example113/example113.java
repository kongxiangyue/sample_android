package irdc.example113;

import irdc.example113.R;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class example113 extends Activity {
  private TextView mTextView01;
  private TextView mTextView03;
  private EditText mEditText1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mPhoneCallListener phoneListener=new mPhoneCallListener(); 
    TelephonyManager telMgr = (TelephonyManager)getSystemService(
        TELEPHONY_SERVICE);
    telMgr.listen(phoneListener, mPhoneCallListener.
        LISTEN_CALL_STATE); 
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
    mTextView03 = (TextView)findViewById(R.id.myTextView3);
    mEditText1 = (EditText)findViewById(R.id.myEditText1);
    
  } 
  public class mPhoneCallListener extends PhoneStateListener { 
    @Override 
    public void onCallStateChanged(int state, String incomingNumber) 
    { 
      // TODO Auto-generated method stub 
      switch(state)  
      {  
        case TelephonyManager.CALL_STATE_IDLE: 
          mTextView01.setText(R.string.str_CALL_STATE_IDLE); 
          
          try
          { 
            AudioManager audioManager = (AudioManager)
                           getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) 
            { 
              audioManager.setRingerMode(AudioManager.
                  RINGER_MODE_NORMAL);               
              audioManager.getStreamVolume(
                  AudioManager.STREAM_RING);
            } 
          }         
          catch(Exception e)
          { 
           mTextView01.setText(e.toString()); 
           e.printStackTrace(); 
          }
          break;
        case TelephonyManager.CALL_STATE_OFFHOOK: 
          mTextView01.setText(R.string.str_CALL_STATE_OFFHOOK); 
          break;
 
        case TelephonyManager.CALL_STATE_RINGING: 
          mTextView01.setText( 
              getResources().getText(R.string.str_CALL_STATE_RINGING)+ 
            incomingNumber);        
          if(incomingNumber.equals(mTextView03.getText().toString()))
          {                 
          try
          {             
            AudioManager audioManager = (AudioManager)
                            getSystemService(Context.AUDIO_SERVICE);
          if (audioManager != null)
            {
            audioManager.setRingerMode(AudioManager.
                  RINGER_MODE_SILENT); 
            audioManager.getStreamVolume(
                  AudioManager.STREAM_RING);
            Toast.makeText(example113.this, getString(R.string.str_msg) 
                ,Toast.LENGTH_SHORT).show(); 
            } 
          }
          catch(Exception e)
          { 
           mTextView01.setText(e.toString()); 
           e.printStackTrace();         
          break;
          }      
        } 
      }     
      super.onCallStateChanged(state, incomingNumber);     
      mEditText1.setOnKeyListener(new EditText.OnKeyListener()
      {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
          mTextView03.setText(mEditText1.getText());
          return false;
        }
      });
    }
  }
}