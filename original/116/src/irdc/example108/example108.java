package irdc.example108;

/* import���class */
import irdc.example108.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class example108 extends Activity
{
  /* �������� */
  private int intLevel;
  private int intScale;
  private Button mButton01;

  /* ����BroadcastReceiver */
  private BroadcastReceiver mBatInfoReceiver=new BroadcastReceiver()
  {
    public void onReceive(Context context, Intent intent)
    {
      String action = intent.getAction();
      /* �����׽����action��ACTION_BATTERY_CHANGED��
       * ������onBatteryInfoReceiver() */
      if (Intent.ACTION_BATTERY_CHANGED.equals(action))
      {
        intLevel = intent.getIntExtra("level", 0);
        intScale = intent.getIntExtra("scale", 100);
        onBatteryInfoReceiver(intLevel,intScale);
      }
    }
  };

  /** Called when the activity is first created. */
  @Override 
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ����main.xml Layout */
    setContentView(R.layout.main);

    /* ��ʼ��Button�������õ����Ķ��� */
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        /* ע��һ��ϵͳ BroadcastReceiver����Ϊ���ʵ�ؼ���֮�� */
        registerReceiver
        (
          mBatInfoReceiver,
          new IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        );
      }
    });
  }

  /* ��׽��ACTION_BATTERY_CHANGEDʱҪ���е�method */
  public void onBatteryInfoReceiver(int intLevel, int intScale)
  {
    /* create �����ĶԻ����� */
    final Dialog d = new Dialog(example108.this);
    d.setTitle(R.string.str_dialog_title);
    d.setContentView(R.layout.mydialog);

    /* ����һ������ģ����Window���ҽ��Ի����ڷ���ǰ�� */
    Window window = d.getWindow();
    window.setFlags
    (
      WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
      WindowManager.LayoutParams.FLAG_BLUR_BEHIND
    );

    /* ��ȡ�õĵ�ؼ�����ʾ��Dialog�� */
    TextView mTextView02=(TextView)d.findViewById(R.id.myTextView2);
    mTextView02.setText 
    (
      getResources().getText(R.string.str_dialog_body)+ 
      String.valueOf(intLevel * 100 / intScale) + "%" 
    );

    /* ���÷���������İ�ť */
    Button mButton02 = (Button)d.findViewById(R.id.myButton2); 
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        /* ��ע��Receiver�����رնԻ����� */
        unregisterReceiver(mBatInfoReceiver);
        d.dismiss(); 
      }
    });
    d.show(); 
  }
}
