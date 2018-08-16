import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
 
public class AndroidImage extends Activity {
 
private String imageFile = "/sdcard/AndroidSharedPreferencesEditor.png";
/** Called when the activity is first created. */
 
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main);
   
ImageView myImageView = (ImageView)findViewById(R.id.imageview);
//Bitmap bitmap = BitmapFactory.decodeFile(imageFile);
//myImageView.setImageBitmap(bitmap);
 
Bitmap bitmap;
float imagew = 300;
float imageh = 300;
 
BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
bitmapFactoryOptions.inJustDecodeBounds = true;
bitmap = BitmapFactory.decodeFile(imageFile, bitmapFactoryOptions);
 
int yRatio = (int)Math.ceil(bitmapFactoryOptions.outHeight/imageh);
int xRatio = (int)Math.ceil(bitmapFactoryOptions.outWidth/imagew);
 
if (yRatio > 1 || xRatio > 1){
 if (yRatio > xRatio) {
  bitmapFactoryOptions.inSampleSize = yRatio;
  Toast.makeText(this,
    "yRatio = " + String.valueOf(yRatio),
    Toast.LENGTH_LONG).show();
 }
 else {
  bitmapFactoryOptions.inSampleSize = xRatio;
  Toast.makeText(this,
    "xRatio = " + String.valueOf(xRatio),
    Toast.LENGTH_LONG).show();
 }
}
else{
 Toast.makeText(this,
   "inSampleSize = 1",
   Toast.LENGTH_LONG).show();
}
 
bitmapFactoryOptions.inJustDecodeBounds = false;
bitmap = BitmapFactory.decodeFile(imageFile, bitmapFactoryOptions);
myImageView.setImageBitmap(bitmap);
}
 
}