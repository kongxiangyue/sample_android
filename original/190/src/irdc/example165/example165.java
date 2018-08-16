package irdc.example165;


/*必需引用apache.http相关类?来建立HTTP联机*/
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.message.BasicNameValuePair; 
import org.apache.http.protocol.HTTP; 
import org.apache.http.util.EntityUtils; 
/*必需引用java.io 与java.util相关类?来读写档案*/
import irdc.example165.R;

import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 

public class example165 extends Activity 
{ 
  /*宣╳两个Button物件,与几个TextView物件*/
  private Button mButton1,mButton2; 
  private TextView mTextView1; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    /*透过findViewById建构巳建立TextView与Button对象*/ 
    mButton1 =(Button) findViewById(R.id.myButton1); 
    mButton2 =(Button) findViewById(R.id.myButton2);
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
     
    /*设定OnClickListener来聆听OnClick事件*/
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      /*覆写onClick事件*/
      @Override 
      public void onClick(View v) 
      { 
        /*宣╳网份?串*/
        String uriAPI = "http://www.dubblogs.cc:8751/Android/Test/API/Post/index.php";
        /*建立HTTP Post联机*/
        HttpPost httpRequest = new HttpPost(uriAPI); 
        /*
         * Post运囫传送变量必须用NameValuePair[]阵在储?
        */
        List <NameValuePair> params = new ArrayList <NameValuePair>(); 
        params.add(new BasicNameValuePair("str", "I am Post String")); 
        try 
        { 
          /*发叨HTTP request*/
          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); 
          /*取得HTTP response*/
          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
          /*若状态码为200*/
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          { 
            /*获取字符串*/
            String strResult = EntityUtils.toString(httpResponse.getEntity()); 
            mTextView1.setText(strResult); 
          } 
          else 
          { 
            mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace();  
        }  
         
      } 
    }); 
    mButton2.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        String uriAPI = "http://www.126.com/str=I+am+Get+String"; 
        /*建立HTTP Get联机*/
        HttpGet httpRequest = new HttpGet(uriAPI); 
        try 
        { 
          /*发送获取的HTTP request*/
          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
          /*若状态码为200*/
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          { 
            /*取叨并应?串*/
            String strResult = EntityUtils.toString(httpResponse.getEntity());
            strResult = eregi_replace("(\r\n|\r|\n|\n\r)","",strResult);
            mTextView1.setText(strResult); 
          } 
          else 
          { 
            mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace();  
        }  
      } 
    }); 
  }
    public String eregi_replace(String strFrom, String strTo, String strTarget)
    {
      String strPattern = "(?i)"+strFrom;
      Pattern p = Pattern.compile(strPattern);
      Matcher m = p.matcher(strTarget);
      if(m.find())
      {
        return strTarget.replaceAll(strFrom, strTo);
      }
      else
      {
        return strTarget;
      }
    }
} 

