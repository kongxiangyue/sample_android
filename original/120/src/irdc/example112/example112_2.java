package irdc.example112;

/* import���class */
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.Bundle;

/* ��������Alert��Receiver */
public class example112_2 extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    /* create Intent������AlarmAlert.class */
    Intent i = new Intent(context, example112_1.class);
        
    Bundle bundleRet = new Bundle();
    bundleRet.putString("STR_CALLER", "");
    i.putExtras(bundleRet);
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);
  }
}
