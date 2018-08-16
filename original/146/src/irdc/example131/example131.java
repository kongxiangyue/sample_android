package irdc.example131;

import android.app.Activity; 
/*����PendingIntent�����ʹ��getBrocast()*/
import android.app.PendingIntent; 
import android.content.Intent; 
import android.os.Bundle;
/*����telephony.gsm.SmsManager�����ʹ��sendTextMessage()*/
import android.telephony.gsm.SmsManager;
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.Toast;
import irdc.example131.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class example131 extends Activity  
{ 
  /*��������һ��Button������EditText*/
  private Button mButton1; 
  private EditText mEditText1; 
  private EditText mEditText2; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*
    * ͨ��findViewById������������
    * EditText1,EditText2��Button����
    */
    mEditText1 = (EditText) findViewById(R.id.myEditText1); 
    mEditText2 = (EditText) findViewById(R.id.myEditText2); 
    mButton1 = (Button) findViewById(R.id.myButton1); 
    
    /*��Ĭ�����ּ���EditText��*/
    mEditText1.setText("���������"); 
    mEditText2.setText("����������!!"); 
    
    /*����onClickListener ���û����EditTextʱ������Ӧ*/
    mEditText1.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*���EditTextʱ�������*/
        mEditText1.setText("");
      }
    }
    );
    
    /*����onClickListener ���û����EditTextʱ������Ӧ*/
    mEditText2.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*���EditTextʱ�������*/
        mEditText2.setText("");
      }
    }
    );    
    /*����onClickListener ���û����Buttonʱ������Ӧ*/
    mButton1.setOnClickListener(new Button.OnClickListener()
    { 
      @Override 
      public void onClick(View v) 
      { 
        /*��EditText1ȡ�ö����ռ��˵绰*/
        String strDestAddress = mEditText1.getText().toString();
        /*��EditText2ȡ�ö�����������*/
        String strMessage = mEditText2.getText().toString(); 
        /*����һȡ��default instance�� SmsManager���� */
        SmsManager smsManager = SmsManager.getDefault(); 
      
        // TODO Auto-generated method stub 
        /*����ռ��˵绰��ʽ����������Ƿ񳬹�70�ַ�*/
        if(isPhoneNumberValid(strDestAddress)==true &&
           iswithin70(strMessage)==true)
        {
          try 
          {
            /*
            * �������������ͨ���������,���Ͷ���
            * �Ƚ���һPendingIntent����ʹ��getBroadcast()�㲥
            * ��PendingIntent,�绰,�������ֵȲ���
            * ����sendTextMessage()�������Ͷ���
            */
            PendingIntent mPI = PendingIntent.getBroadcast
            (example131.this, 0, new Intent(), 0);
            smsManager.sendTextMessage
            (strDestAddress, null, strMessage, mPI, null); 
          } 
          catch(Exception e) 
          { 
            e.printStackTrace(); 
          } 
          Toast.makeText
          (
            example131.this,"�ͳ��ɹ�!!" , 
            Toast.LENGTH_SHORT
          ).show();
          mEditText1.setText("");
          mEditText2.setText("");
        }
        else 
        {
          /* �绰��ʽ��������ֲ���������ʱ,��Toast���� */
          if (isPhoneNumberValid(strDestAddress)==false)
          { /*����������70�ַ�*/
            if(iswithin70(strMessage)==false)
            {
              Toast.makeText
              (
                example131.this, 
                "�绰�����ʽ����/�������ݳ���70��!!!",
                Toast.LENGTH_SHORT
              ).show();
            }
            else
            {
              Toast.makeText
              (
                example131.this,
                "�绰�����ʽ����!!" , 
                Toast.LENGTH_SHORT
              ).show();
            }
          }
          /*��������70�ַ�*/
          else if (iswithin70(strMessage)==false)
          {
            Toast.makeText
            (
              example131.this, 
              "�������ݳ���70��,��ɾ����������!!",
              Toast.LENGTH_SHORT
            ).show();
          }
        }
      }
    }); 
     
  }
  /*����ַ����Ƿ�Ϊ�绰����ķ���,������true or false���ж�ֵ*/
  public static boolean isPhoneNumberValid(String phoneNumber)
  {
    boolean isValid = false;
    /* �ɽ��ܵĵ绰��ʽ��:
     * ^\\(? : ����ʹ�� "(" ��Ϊ��ͷ
     * (\\d{3}): ��������������
     * \\)? : ����ʹ��")"����
     * [- ]? : ��������ʽ�����ʹ�þ�ѡ���Ե� "-".
     * (\\d{3}) : �ٽ�������������
     * [- ]? : ����ʹ�þ�ѡ���Ե� "-" ����.
     * (\\d{5})$: ��������ֽ���.
     * ���ԱȽ��������ָ�ʽ:
     * (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890  
    */
    String expression = 
    "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
    
    /* �ɽ��ܵĵ绰��ʽ��:
     * ^\\(? : ����ʹ�� "(" ��Ϊ��ͷ
     * (\\d{3}): ��������������
     * \\)? : ����ʹ��")"����
     * [- ]? : ��������ʽ�����ʹ�þ�ѡ���Ե� "-".
     * (\\d{4}) : �ٽ������ĸ�����
     * [- ]? : ����ʹ�þ�ѡ���Ե� "-" ����.
     * (\\d{4})$: ���ĸ����ֽ���.
     * ���ԱȽ��������ָ�ʽ:
     * (02)3456-7890, 02-3456-7890, 0234567890, (02)-3456-7890  
    */
    String expression2=
    "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
    
    CharSequence inputStr = phoneNumber;
    /*����Pattern*/
    Pattern pattern = Pattern.compile(expression);
    /*��Pattern �Բ�������Matcher��Regular expression*/ 
    Matcher matcher = pattern.matcher(inputStr);
    /*����Pattern2*/
    Pattern pattern2 =Pattern.compile(expression2);
    /*��Pattern2 �Բ�������Matcher2��Regular expression*/ 
    Matcher matcher2= pattern2.matcher(inputStr);
    if(matcher.matches()||matcher2.matches())
    {
      isValid = true;
    }
    return isValid; 
  }
  
  public static boolean iswithin70(String text)
  {
    if (text.length()<= 70)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
