package irdc.example166;

import irdc.example166.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class example166 extends Activity 
{
  
  private ImageButton mImageButton1;
  private EditText mEditText1;
  private WebView mWebView1;  
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
   
    mImageButton1 = (ImageButton)findViewById(R.id.myImageButton1);
    mEditText1 = (EditText)findViewById(R.id.myEditText1);
    mWebView1 = (WebView) findViewById(R.id.myWebView1);
      
    mImageButton1.setOnClickListener(new 
                                      ImageButton.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        {                    
          mImageButton1.setImageResource(R.drawable.go_2);
          String strURI = (mEditText1.getText().toString()); 
          mWebView1.loadUrl(strURI);
          Toast.makeText(
              example166.this,getString(R.string.load)+strURI,
                          Toast.LENGTH_LONG)
              .show();          
        }   
      }      
    });
  }
}