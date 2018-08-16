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
        btn1=(Button)findViewById(R.id.download_Btn);//���ذ�ť
        btn2=(Button)findViewById(R.id.show_pic);//��ʾͼ��ť
        
        ///Ϊ��ʾ��ť���ü���,����url������
        btn2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {	
				 Intent intent=new Intent();
					intent.setClass(Activity01.this, A1.class);
					startActivity(intent);
					Activity01.this.finish();//����	 
			}
		});
        
        
        /////����ͼƬ
        btn1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))//�ж��Ƿ���Զ�SDcard���в���
				{   
					String url=mEditText.getText().toString().trim();
					String fileName=url.substring(url.lastIndexOf('/')+1,url.length());//��ȡ����ͼƬ���ļ���
					
					Bitmap bitmap=GetNetBitmap(url);//�õ�bitmap
				    String sdCardDir = Environment.getExternalStorageDirectory()+"/hekui/";//��ȡSDCardĿ¼
				    File file = new File(sdCardDir,fileName);//��SDcard��Ŀ¼�´���ͼƬ�ļ�
				
				       
				   	try //�������϶�ȡ��ͼƬ���浽SDCard��
				   	{
			            FileOutputStream out=new FileOutputStream(file);//ΪͼƬ�ļ�ʵ���������
			            if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out))//��ͼƬ����
			            {
			                out.flush();
			                Log.v(TAG,"Success");
			                out.close();
			             info=(TextView)findViewById(R.id.textView1);
			                info.setText(fileName+" ���سɹ�����");
			                
			            }
			        } 
					catch (FileNotFoundException e) 
					{
						Log.v(TAG,"�ļ�û���֣���");
			            e.printStackTrace();
			        } catch (IOException e) 
			        {
			            e.printStackTrace();
			            Log.v(TAG,"���������󣡣�");
			        }
				}
			
			
			}
		});      
    }
   
    /////ȡ�������ϵ�ͼƬ
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
				conn.setDoInput(true);// ��������ķ�ʽ
				conn.connect();
				
				InputStream is = conn.getInputStream();// ���õ�������ת��ΪinputStream
				bitmap = BitmapFactory.decodeStream(is);// ��inputstreamת��ΪBitmap
				is.close();// �ر�����
				
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