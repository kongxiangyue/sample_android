package irdc.example060;

/* import相关class */
import irdc.example060.R;

import java.io.File;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class example060 extends Activity
{
  /*声明对象变量*/
  private ImageView mImageView;
  private Button mButton;
  private TextView mTextView;
  private String fileName="/data/data/irdc.ex04_22/ex04_22_2.png";
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);
    
    /* 取得Button对象，并添加onClickListener */
    mButton = (Button)findViewById(R.id.mButton);
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
      /* 取得对象 */
        mImageView = (ImageView)findViewById(R.id.mImageView);
        mTextView=(TextView)findViewById(R.id.mTextView);
        /* 检查文件是否存在 */
        File f=new File(fileName);   
        if(f.exists()) 
        { 
          /* 产生Bitmap对象，并放入mImageView中 */
          Bitmap bm = BitmapFactory.decodeFile(fileName);
          mImageView.setImageBitmap(bm);
          mTextView.setText(fileName); 
        } 
        else 
        {  
          mTextView.setText("此文件不存在"); 
        }
      } 
    });
  }
}
