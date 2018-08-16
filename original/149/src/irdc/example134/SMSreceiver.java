package irdc.example134;

/*引用BroadcastReceiver类*/
import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
/*引用telephoney.gsm.SmsMessage来收取短信*/
import android.telephony.gsm.SmsMessage; 
/*引用Toast类来告知用户收到短信*/
import android.widget.Toast;

/*定义类SMSreceiver监听系统服务广播的信息 */
public class SMSreceiver extends BroadcastReceiver 
{ 
  /*
  * 声明静态字符串
  */
  private static final String mACTION ="android.provider.Telephony.SMS_RECEIVED";
  
  private String shoudao="已经收到了短信!";
  
  @Override 
  public void onReceive(Context context, Intent intent)
  {
    Toast.makeText(context, shoudao.toString(),
    Toast.LENGTH_LONG).show();
    
    /*通过equals进行比较，判断Intent是否为短信*/
    if (intent.getAction().equals(mACTION))
    { 
      /*构建字符串集合变量mm*/
      StringBuilder mm = new StringBuilder(); 
      /*接收从Intent传来的数据*/
      Bundle bundle = intent.getExtras(); 
      /*判断在Intent中是否有传递来的数据*/
      if (bundle != null) 
      { 
        /*通过bundle.get("")返回一包含pdus的对象*/
        Object[] nn = (Object[]) bundle.get("pdus");
        
        /*根据收到对象长度设置array的大小*/
        SmsMessage[] messages = new SmsMessage[nn.length];  
        
        for (int i = 0; i<nn.length; i++) 
        {  
          messages[i] =
          SmsMessage.createFromPdu((byte[]) nn[i]);
        }
          
        /* 将送来的短信合并自定义信息于StringBuilder当中 */  
        for (SmsMessage currentMessage : messages) 
        {  
          mm.append("正在接收到来自“\n");  
          /* 收信人的手机号码 */ 
          mm.append(currentMessage.getDisplayOriginatingAddress());
          mm.append("\n”的短信------\n");  
          /* 获取信息正文 */  
          mm.append(currentMessage.getDisplayMessageBody()); 
          Toast.makeText
          (
            context, mm.toString(), Toast.LENGTH_LONG
          ).show();
        }
      }
      
      /* 使用Notification提醒显示来信信息  */
      Toast.makeText
      (
        context, mm.toString(), Toast.LENGTH_LONG
      ).show();
       
      /* 返回主Activity */ 
      Intent i = new Intent(context, example134.class); 
      /*自定义一Bundle*/
      Bundle mbundle = new Bundle(); 
      /*将短信信息以putString()方法存入自定义的bundle内*/
      mbundle.putString("STR_INPUT",  mm.toString()); 
      /*将自定义bundle写入Intent中*/
      i.putExtras(mbundle); 
      /*设置用全新的进程来运行Intent*/
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
      context.startActivity(i); 
    } 
  } 
}
