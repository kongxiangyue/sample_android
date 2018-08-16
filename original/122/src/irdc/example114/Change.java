package irdc.example114;

/* import���class */
import irdc.example114.R;

import java.io.IOException;
import java.util.Calendar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/* ʵ�����и������汳����Activity */
public class Change extends Activity
{
  /* �������ͼ�ļ�id������bg */
  private static final int[] bg =
    {R.drawable.b01,R.drawable.b02,R.drawable.b03,R.drawable.b04,
    R.drawable.b05,R.drawable.b06,R.drawable.b07};
  
  @Override
  protected void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ����progress.xml Layout */
    setContentView(R.layout.pro);
    /* ȡ�ý��������ڼ� */
    Calendar ca=Calendar.getInstance();
    int dayOfWeek=ca.get(Calendar.DAY_OF_WEEK)-1;
    
    /* �����ݿ���ȡ�ý���Ӧ�û���һ�ű��� */
    int DailyBg=0;
    String selection = "DailyId=?";   
    String[] selectionArgs = new String[]{""+dayOfWeek};
    Dai db=new Dai(Change.this);
    Cursor cur=db.select(selection,selectionArgs);
    while(cur.moveToNext())
    {
      DailyBg=cur.getInt(0);
    }
    cur.close();
    db.close();

    /* ���DailyBg==99����û���ã����Բ����� */
    if(DailyBg!=99)
    {
      Bitmap bmp=BitmapFactory.decodeResource
      (getResources(), bg[DailyBg]);
      try
      {
        super.setWallpaper(bmp);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    finish();
  }
}
