package irdc.example107;

/*��������BroadcastReceiver��*/
import android.content.BroadcastReceiver;
import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
/*��������telephoney.gsm.SmsMessage����ȡ����*/
import android.telephony.gsm.SmsMessage; 
/*��������Toast������֪�û��յ�����*/
import android.widget.Toast; 

/* �Զ���̳���BroadcastReceiver��,����ϵͳ����㲥����Ϣ */
public class example107_SMS extends BroadcastReceiver 
{ 
  /*������̬�ַ���,��ʹ��android.provider.Telephony.SMS_RECEIVED
  ��ΪActionΪ���ŵ�����*/
  private static final String mACTION = 
  "android.provider.Telephony.SMS_RECEIVED"; 
  
  @Override 
  public void onReceive(Context context, Intent intent) 
  { 
    // TODO Auto-generated method stub 
    /* �жϴ���Intent�Ƿ�Ϊ����*/
    if (intent.getAction().equals(mACTION)) 
    { 
      /*����һ�ַ������ϱ���sb*/
      StringBuilder sb = new StringBuilder(); 
      /*������Intent����������*/
      Bundle bundle = intent.getExtras(); 
      /*�ж�Intent��������*/
      if (bundle != null) 
      { 
        /* pdusΪ android���ö��Ų��� identifier
         * ͨ��bundle.get("")����һ����pdus�Ķ���*/
        Object[] myOBJpdus = (Object[]) bundle.get("pdus"); 
        /*�������Ŷ���array,�������յ��Ķ��󳤶�������array�Ĵ�С*/
        SmsMessage[] messages = new SmsMessage[myOBJpdus.length];  
        for (int i = 0; i<myOBJpdus.length; i++)
        {  
          messages[i] =
          SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
        }
          
        /* �������Ķ��źϲ��Զ�����Ϣ��StringBuilder���� */  
        for (SmsMessage currentMessage : messages) 
        {  
          sb.append("���ڽ��յ�����:\n");  
          /* ��Ѷ�ߵĵ绰���� */ 
          sb.append(currentMessage.getDisplayOriginatingAddress());
          sb.append("\n------�����Ķ���------\n");  
          /* ȡ�ô�����Ϣ��BODY */  
          sb.append(currentMessage.getDisplayMessageBody());  
        }  
      }    
      /* ��Notification(Toase)��ʾ��Ѷ��Ϣ  */
      Toast.makeText
      (
        context, sb.toString(), Toast.LENGTH_LONG
      ).show();
       
      /* ������Activity */ 
      Intent i = new Intent(context, example107.class); 
      /*��������һ��ȫ�µ�task������*/
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
      context.startActivity(i); 
    } 
  } 
}
