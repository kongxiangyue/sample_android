package irdc.example134;

/*����BroadcastReceiver��*/
import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
/*����telephoney.gsm.SmsMessage����ȡ����*/
import android.telephony.gsm.SmsMessage; 
/*����Toast������֪�û��յ�����*/
import android.widget.Toast;

/*������SMSreceiver����ϵͳ����㲥����Ϣ */
public class SMSreceiver extends BroadcastReceiver 
{ 
  /*
  * ������̬�ַ���
  */
  private static final String mACTION ="android.provider.Telephony.SMS_RECEIVED";
  
  private String shoudao="�Ѿ��յ��˶���!";
  
  @Override 
  public void onReceive(Context context, Intent intent)
  {
    Toast.makeText(context, shoudao.toString(),
    Toast.LENGTH_LONG).show();
    
    /*ͨ��equals���бȽϣ��ж�Intent�Ƿ�Ϊ����*/
    if (intent.getAction().equals(mACTION))
    { 
      /*�����ַ������ϱ���mm*/
      StringBuilder mm = new StringBuilder(); 
      /*���մ�Intent����������*/
      Bundle bundle = intent.getExtras(); 
      /*�ж���Intent���Ƿ��д�����������*/
      if (bundle != null) 
      { 
        /*ͨ��bundle.get("")����һ����pdus�Ķ���*/
        Object[] nn = (Object[]) bundle.get("pdus");
        
        /*�����յ����󳤶�����array�Ĵ�С*/
        SmsMessage[] messages = new SmsMessage[nn.length];  
        
        for (int i = 0; i<nn.length; i++) 
        {  
          messages[i] =
          SmsMessage.createFromPdu((byte[]) nn[i]);
        }
          
        /* �������Ķ��źϲ��Զ�����Ϣ��StringBuilder���� */  
        for (SmsMessage currentMessage : messages) 
        {  
          mm.append("���ڽ��յ����ԡ�\n");  
          /* �����˵��ֻ����� */ 
          mm.append(currentMessage.getDisplayOriginatingAddress());
          mm.append("\n���Ķ���------\n");  
          /* ��ȡ��Ϣ���� */  
          mm.append(currentMessage.getDisplayMessageBody()); 
          Toast.makeText
          (
            context, mm.toString(), Toast.LENGTH_LONG
          ).show();
        }
      }
      
      /* ʹ��Notification������ʾ������Ϣ  */
      Toast.makeText
      (
        context, mm.toString(), Toast.LENGTH_LONG
      ).show();
       
      /* ������Activity */ 
      Intent i = new Intent(context, example134.class); 
      /*�Զ���һBundle*/
      Bundle mbundle = new Bundle(); 
      /*��������Ϣ��putString()���������Զ����bundle��*/
      mbundle.putString("STR_INPUT",  mm.toString()); 
      /*���Զ���bundleд��Intent��*/
      i.putExtras(mbundle); 
      /*������ȫ�µĽ���������Intent*/
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
      context.startActivity(i); 
    } 
  } 
}
