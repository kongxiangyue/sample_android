package pic.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import java.io.File;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class A1 extends Activity {
	ImageView	imageview;
	TextView	textview;
	int			image_alpha	= 255;
	int i = 0;
//	Handler		mHandler	= new Handler();
	
    public void onCreate(Bundle savedInstanceState) {  	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);
 
		imageview = (ImageView) this.findViewById(R.id.ImageView01);
		textview = (TextView) this.findViewById(R.id.TextView01);
		
		
		//开启一个线程
		new Thread(new Runnable() {
			public void run()
			{
			}
		}).start();
		
        final sdk_fileclass file01 = new sdk_fileclass();
        final int length = file01.filenames.length;
        
        Bitmap bit = null;
        bit = file01.getlmageAt(file01.filenames,i);
        imageview.setImageBitmap(bit); 
		imageview.invalidate();
		imageview.setAlpha(image_alpha);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button.OnClickListener(){
		
			
            Bitmap bit = null;
			public void onClick(View v) {
				if(i<length){
					i++;
					if(i == length)
						i = 0;
					bit = file01.getlmageAt(file01.filenames,i);
				}
				imageview.setImageBitmap(bit); 
				imageview.invalidate();
				imageview.setAlpha(image_alpha);
			}
			

		});
		 Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new Button.OnClickListener(){

			Bitmap bit = null;
			public void onClick(View v) {
				if(i<length){
					i--;
					if(i < 0)
						i = length-1;
					bit = file01.getlmageAt(file01.filenames,i);
				}
				imageview.setImageBitmap(bit); 
				imageview.invalidate();
				imageview.setAlpha(image_alpha);
			}
			
		});
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(A1.this, Activity01.class);
				startActivity(intent);
				A1.this.finish();//结束	 
			}
			

		});

	}
}