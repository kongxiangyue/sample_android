package irdc.example096;

import irdc.example096.R;
import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class example096 extends Activity 
{
  private Vibrator mVibrator01;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);  
  
    /*����ToggleButton�Ķ���*/
    mVibrator01 = ( Vibrator )getApplication().getSystemService
    (Service.VIBRATOR_SERVICE);
  
    final ToggleButton mtogglebutton1 = 
    (ToggleButton) findViewById(R.id.myTogglebutton1);
  
    final ToggleButton mtogglebutton2 = 
    (ToggleButton) findViewById(R.id.myTogglebutton2);
  
    final ToggleButton mtogglebutton3 =
    (ToggleButton) findViewById(R.id.myTogglebutton3);
  
    /* ���� */
    mtogglebutton1.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v) 
      {
        if (mtogglebutton1.isChecked())
        {
          /* �����𶯵����� */
          mVibrator01.vibrate( new long[]{100,10,100,1000},-1);
          /*��Toast��ʾ������*/
          Toast.makeText
          (
            example096.this,
            getString(R.string.str_ok),
            Toast.LENGTH_SHORT
          ).show();
        }
        else
        {
          /* ȡ���� */
          mVibrator01.cancel();   
          /*��Toast��ʾ���ѱ�ȡ��*/
          Toast.makeText
          (
            example096.this,
            getString(R.string.str_end),
            Toast.LENGTH_SHORT
          ).show();
        } 
      }
    });
  
    /* ���� */
    mtogglebutton2.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v) 
      {
        if (mtogglebutton2.isChecked())
        {
          /*�����𶯵�����*/
          mVibrator01.vibrate(new long[]{100,100,100,1000},0);
        
          /*��Toast��ʾ������*/
          Toast.makeText
          (
            example096.this,
            getString(R.string.str_ok),
            Toast.LENGTH_SHORT
          ).show();
        }
        else
        {
          /* ȡ���� */
          mVibrator01.cancel();
          
          /* ��Toast��ʾ��ȡ�� */
          Toast.makeText
          (
            example096.this,
            getString(R.string.str_end),
            Toast.LENGTH_SHORT
          ).show();
        } 
      }
    });  
    
    /* ������ */
    mtogglebutton3.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v) 
      {
        if (mtogglebutton3.isChecked())
        {
          /* �����𶯵����� */
          mVibrator01.vibrate( new long[]{1000,50,1000,50,1000},0);
        
          /*��Toast��ʾ������*/
          Toast.makeText
          (
            example096.this, getString(R.string.str_ok),
            Toast.LENGTH_SHORT
          ).show();
        }
        else
        {
          /* ȡ���� */
          mVibrator01.cancel();
          /* ��Toast��ʾ��ȡ�� */
          Toast.makeText
          (
            example096.this,
            getString(R.string.str_end),
            Toast.LENGTH_SHORT
          ).show();
        } 
      }
    });
  }
}
