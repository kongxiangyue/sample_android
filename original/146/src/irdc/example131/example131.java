package irdc.example131;

import android.app.Activity; 
/*引用PendingIntent类才能使用getBrocast()*/
import android.app.PendingIntent; 
import android.content.Intent; 
import android.os.Bundle;
/*引用telephony.gsm.SmsManager类才能使用sendTextMessage()*/
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
  /*声明变量一个Button与两个EditText*/
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
    * 通过findViewById构造器来建构
    * EditText1,EditText2与Button对象
    */
    mEditText1 = (EditText) findViewById(R.id.myEditText1); 
    mEditText2 = (EditText) findViewById(R.id.myEditText2); 
    mButton1 = (Button) findViewById(R.id.myButton1); 
    
    /*将默认文字加载EditText中*/
    mEditText1.setText("请输入号码"); 
    mEditText2.setText("请输入内容!!"); 
    
    /*设置onClickListener 让用户点击EditText时做出反应*/
    mEditText1.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*点击EditText时清空正文*/
        mEditText1.setText("");
      }
    }
    );
    
    /*设置onClickListener 让用户点击EditText时做出反应*/
    mEditText2.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*点击EditText时清空正文*/
        mEditText2.setText("");
      }
    }
    );    
    /*设置onClickListener 让用户点击Button时做出反应*/
    mButton1.setOnClickListener(new Button.OnClickListener()
    { 
      @Override 
      public void onClick(View v) 
      { 
        /*由EditText1取得短信收件人电话*/
        String strDestAddress = mEditText1.getText().toString();
        /*由EditText2取得短信文字内容*/
        String strMessage = mEditText2.getText().toString(); 
        /*建构一取得default instance的 SmsManager对象 */
        SmsManager smsManager = SmsManager.getDefault(); 
      
        // TODO Auto-generated method stub 
        /*检查收件人电话格式与短信字数是否超过70字符*/
        if(isPhoneNumberValid(strDestAddress)==true &&
           iswithin70(strMessage)==true)
        {
          try 
          {
            /*
            * 两个条件都检查通过的情况下,发送短信
            * 先建构一PendingIntent对象并使用getBroadcast()广播
            * 将PendingIntent,电话,短信文字等参数
            * 传入sendTextMessage()方法发送短信
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
            example131.this,"送出成功!!" , 
            Toast.LENGTH_SHORT
          ).show();
          mEditText1.setText("");
          mEditText2.setText("");
        }
        else 
        {
          /* 电话格式与短信文字不符合条件时,以Toast提醒 */
          if (isPhoneNumberValid(strDestAddress)==false)
          { /*且字数超过70字符*/
            if(iswithin70(strMessage)==false)
            {
              Toast.makeText
              (
                example131.this, 
                "电话号码格式错误/短信内容超过70字!!!",
                Toast.LENGTH_SHORT
              ).show();
            }
            else
            {
              Toast.makeText
              (
                example131.this,
                "电话号码格式错误!!" , 
                Toast.LENGTH_SHORT
              ).show();
            }
          }
          /*字数超过70字符*/
          else if (iswithin70(strMessage)==false)
          {
            Toast.makeText
            (
              example131.this, 
              "短信内容超过70字,请删除部分内容!!",
              Toast.LENGTH_SHORT
            ).show();
          }
        }
      }
    }); 
     
  }
  /*检查字符串是否为电话号码的方法,并返回true or false的判断值*/
  public static boolean isPhoneNumberValid(String phoneNumber)
  {
    boolean isValid = false;
    /* 可接受的电话格式有:
     * ^\\(? : 可以使用 "(" 作为开头
     * (\\d{3}): 紧接着三个数字
     * \\)? : 可以使用")"接续
     * [- ]? : 在上述格式后可以使用具选择性的 "-".
     * (\\d{3}) : 再紧接着三个数字
     * [- ]? : 可以使用具选择性的 "-" 接续.
     * (\\d{5})$: 以五个数字结束.
     * 可以比较下列数字格式:
     * (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890  
    */
    String expression = 
    "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
    
    /* 可接受的电话格式有:
     * ^\\(? : 可以使用 "(" 作为开头
     * (\\d{3}): 紧接着三个数字
     * \\)? : 可以使用")"接续
     * [- ]? : 在上述格式后可以使用具选择性的 "-".
     * (\\d{4}) : 再紧接着四个数字
     * [- ]? : 可以使用具选择性的 "-" 接续.
     * (\\d{4})$: 以四个数字结束.
     * 可以比较下列数字格式:
     * (02)3456-7890, 02-3456-7890, 0234567890, (02)-3456-7890  
    */
    String expression2=
    "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
    
    CharSequence inputStr = phoneNumber;
    /*创建Pattern*/
    Pattern pattern = Pattern.compile(expression);
    /*将Pattern 以参数传入Matcher作Regular expression*/ 
    Matcher matcher = pattern.matcher(inputStr);
    /*创建Pattern2*/
    Pattern pattern2 =Pattern.compile(expression2);
    /*将Pattern2 以参数传入Matcher2作Regular expression*/ 
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
