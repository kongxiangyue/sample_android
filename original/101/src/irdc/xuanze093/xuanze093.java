package irdc.xuanze093;

import irdc.xuanze093.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class xuanze093 extends Activity
{
  public Button mButton1;
  public TextView mTextView1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton1 =(Button) findViewById(R.id.myButton1);
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    mButton1.setOnClickListener(myShowAlertDialog);
  }
  
  Button.OnClickListener myShowAlertDialog = new Button.OnClickListener()
  {
    public void onClick(View arg0)
    {
      new AlertDialog.Builder(xuanze093.this)
        .setTitle(R.string.str_alert_title)
        .setItems(R.array.items_irdc_dialog,
        new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichcountry)
          {
            CharSequence strDialogBody = getString(R.string.str_alert_body);
            String[] aryShop = 
            getResources().getStringArray(R.array.items_irdc_dialog);                                           
              new AlertDialog.Builder(xuanze093.this)                        
              .setMessage(strDialogBody + aryShop[whichcountry])                        
              .setNeutralButton(R.string.str_ok, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog, int whichButton)
                {
                 
                }
              })
              .show();
          }
        })
        .setNegativeButton("¨ú®ø", new DialogInterface.OnClickListener()
        { 
          @Override 
          public void onClick(DialogInterface d, int which)
          { 
            d.dismiss(); 
          } 
        })
        .show();
    } /*End: public void onClick(View arg0)*/
  };
}