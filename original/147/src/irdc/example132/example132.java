package irdc.example132;

import irdc.example132.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class example132 extends Activity
{
  private ImageButton myImageButton;
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myImageButton = (ImageButton) findViewById(R.id.myImageButton);

    myImageButton.setOnClickListener(new ImageButton.OnClickListener()
    {
      public void onClick(View v)
      {
        /* 调用拨号的画面 */
        Intent myIntentDial = new Intent("android.intent.action.CALL_BUTTON");
        startActivity(myIntentDial);
      }
    });
  }
}