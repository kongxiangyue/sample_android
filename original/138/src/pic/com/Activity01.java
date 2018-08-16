package pic.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import android.app.Activity;
import android.content.Intent;
import android.graphics.*;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class Activity01 extends Activity 
{
	private final String TAG="Activity01";
	private EditText mEditText;
	private Button btn1;
	private Button btn2;
	private   TextView info;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mEditText=(EditText)findViewById(R.id.edit);
        btn1=(Button)findViewById(R.id.download_Btn);//下载按钮
        btn2=(Button)findViewById(R.id.show_pic);//显示图按钮
        
        ///为显示按钮设置监听,保存url的数据
        btn2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {	
				 Intent intent=new Intent();
					intent.setClass(Activity01.this, A1.class);
					startActivity(intent);
					Activity01.this.finish();//结束	 
			}
		});
        
        
        /////下载图片
        btn1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))//判断是否可以对SDcard进行操作
				{   
					String url=mEditText.getText().toString().trim();
					String fileName=url.substring(url.lastIndexOf('/')+1,url.length());//提取下载图片的文件名
					
					Bitmap bitmap=GetNetBitmap(url);//得到bitmap
				    String sdCardDir = Environment.getExternalStorageDirectory()+"/hekui/";//获取SDCard目录
				    File file = new File(sdCardDir,fileName);//在SDcard的目录下创建图片文件
				
				       
				   	try //将网络上读取的图片保存到SDCard中
				   	{
			            FileOutputStream out=new FileOutputStream(file);//为图片文件实例化输出流
			            if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out))//对图片保存
			            {
			                out.flush();
			                Log.v(TAG,"Success");
			                out.close();
			             info=(TextView)findViewById(R.id.textView1);
			                info.setText(fileName+" 下载成功！！");
			                
			            }
			        } 
					catch (FileNotFoundException e) 
					{
						Log.v(TAG,"文件没发现！！");
			            e.printStackTrace();
			        } catch (IOException e) 
			        {
			            e.printStackTrace();
			            Log.v(TAG,"数据流错误！！");
			        }
				}
			
			
			}
		});      
    }
   
    /////取得网络上的图片
    public Bitmap GetNetBitmap(String url)
    {
		URL imageUrl = null;
		Bitmap bitmap=null;
		try 
		{
			imageUrl = new URL(url);
		}
		catch (MalformedURLException e) 
		{
			Log.v(TAG, e.getMessage());
		}
		
		if (imageUrl != null) {

			try {

				HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
				conn.setDoInput(true);// 设置请求的方式
				conn.connect();
				
				InputStream is = conn.getInputStream();// 将得到的数据转化为inputStream
				bitmap = BitmapFactory.decodeStream(is);// 将inputstream转化为Bitmap
				is.close();// 关闭数据
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			Log.v(TAG,"Url is Null!!");
		}
		return bitmap;
    }   
}