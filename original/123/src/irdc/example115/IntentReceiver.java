package irdc.example115;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* ��׽android.intent.action.BOOT_COMPLETED��Receiver�� */
public class IntentReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // TODO Auto-generated method stub
    
    /* ���յ�Receiverʱ��ָ���򿪴˳���EX06_16.class�� */
    Intent mBootIntent = new Intent(context, example115.class);
    
    /* ����Intent��ΪFLAG_ACTIVITY_NEW_TASK */ 
    mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    
    /* ��Intent��startActivity���͸�����ϵͳ */ 
    context.startActivity(mBootIntent);
  }
}
