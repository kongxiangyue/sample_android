package irdc.example054;

import irdc.example054.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class example054 extends Activity
{
  TextView myTextView;
  ImageButton myImageButton_1;
  ImageButton myImageButton_2;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);

    /* 以findViewById()取得TextView及ImageButton对象 */
    myTextView = (TextView) findViewById(R.id.myTextView);
    myImageButton_1=(ImageButton)findViewById(R.id.myImageButton_1);
    myImageButton_2=(ImageButton)findViewById(R.id.myImageButton_2);

    /* myImageButton_1添加OnClickListener */
    myImageButton_1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        myTextView.setText("你点击的是myImageButton_1");
        /* 点击myImageButton_1时将myImageButton_1图片置换成p3图片 */
        myImageButton_1.setImageDrawable(getResources().getDrawable(
            R.drawable.p3));
        /* 点击myImageButton_1时将myImageButton_2图片置换成p2图片 */
        myImageButton_2.setImageDrawable(getResources().getDrawable(
            R.drawable.p2));
      }
    });

    /* myImageButton_2添加OnClickListener */
    myImageButton_2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        myTextView.setText("你点击的是myImageButton_2");
        /* 点击myImageButton_2时将myImageButton_1图片置换成p1图片 */
        myImageButton_1.setImageDrawable(getResources().getDrawable(
            R.drawable.p1));
        /* 点击myImageButton_2时将myImageButton_2图片置换成p3图片 */
        myImageButton_2.setImageDrawable(getResources().getDrawable(
            R.drawable.p3));
      }
    });
  }
}
