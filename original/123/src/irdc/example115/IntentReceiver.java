package irdc.example115;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* 捕捉android.intent.action.BOOT_COMPLETED的Receiver类 */
public class IntentReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // TODO Auto-generated method stub
    
    /* 当收到Receiver时，指定打开此程序（EX06_16.class） */
    Intent mBootIntent = new Intent(context, example115.class);
    
    /* 设置Intent打开为FLAG_ACTIVITY_NEW_TASK */ 
    mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    
    /* 将Intent以startActivity传送给操作系统 */ 
    context.startActivity(mBootIntent);
  }
}
