package pic.com;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class sdk_fileclass {
	private String dirName = Environment/*.getDownloadCacheDirectory()获取 Android 下载/缓存内容目录*/
	.getExternalStorageDirectory().toString()+"/hekui/";
	public String[] filenames = new File(dirName).list();

	
	public int getCount(){
		if(filenames == null)
			return 0;
		return filenames.length;
	}
	
	public Bitmap getlmageAt(String[] filenames, int i){
		String path = dirName;
		if(i>=filenames.length)
			return null;
		path+=filenames[i];
		
		Bitmap b = BitmapFactory.decodeFile(path);
		return b;
}
}
