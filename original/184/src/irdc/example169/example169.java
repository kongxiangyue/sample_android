package irdc.example169;

import irdc.example169.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class example169 extends Activity
{
  private Gallery myGallery01;
  /* 地址栏字符串 */
  private String[] myImageURL = new String[]
  {
      "http://b43.photo.store.qq.com/http_imgload.cgi?/"
          + "rurl4_b=17a39f8fd6536fa1e8752d5874fdd24aef3c61fd94bc9063b81dccb1dd93900f3dbe3a22a3396bc02595cdf8c82c4dd74274b59a9a3e34f2ffad62467626e06f1b84e0e548cb8e7539d1dc0d541dbb085f1105d0&a=43&b=43",
      "http://b27.photo.store.qq.com/http_imgload.cgi?/"
          + "rurl4_b=086a67cbd6a8cfb4389ea2b48efab6f3ea78f5797abbbaa617259f2d2a980a5468f2801897cfcc2b78af92fbb87565ed7a3a08041daff2dd9ccd26d3cc6198e41f2d205c8a0c445325771e8a179215999afaf9f3&a=27&b=27",
      "http://b27.photo.store.qq.com/http_imgload.cgi?/"
          + "rurl4_b=2a9dcf1fd909a7ed3ce8951f738608982f26d812b3a5fc96e221b85fc085e7cc3264ee20730f0fd3a1f7aca06740db7a6153d9357467ca39f82b866b6fbe3cd94bbdd10ed01841e67c95d8e4af8890b7ced40869&a=30&b=27",
      "http://b27.photo.store.qq.com/http_imgload.cgi?/"
          + "rurl4_b=2a9dcf1fd909a7ed3ce8951f73860898bb7ff57a8cb7747c9f0eb6a02124850b709c0b86f086a4ba5653eeb71dd4b01e4a58f407e2eec9433cd8d4bc0b88fda56260c2c8beb34ebab77b610c7131393f82e774ef&a=27&b=27",
      "http://b27.photo.store.qq.com/http_imgload.cgi?/"
          + "rurl4_b=2a9dcf1fd909a7ed3ce8951f73860898158d252489f84e7d2a83d44c01b7bb12b2c19ca0efdd555dba788407fd01e9de45524b11a9793f532624197bc8d14c84ae78ddebafe4357e4eedc60e9e510224367490bf&a=27&b=27" };

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myGallery01 = (Gallery) findViewById(R.id.myGallery01);
    myGallery01.setAdapter(new myInternetGalleryAdapter(this));
  }

  /* 用BaseAdapter */
  public class myInternetGalleryAdapter extends BaseAdapter
  {
    /* 类成员myContext Context对象 */
    private Context myContext;
    private int mGalleryItemBackground;

    /*构造器自由一个参数，即要存储的Context */
    public myInternetGalleryAdapter(Context c)
    {
      this.myContext = c;
      TypedArray a = myContext
          .obtainStyledAttributes(R.styleable.Gallery);

      /* 获取Gallery属性的Index id */
      mGalleryItemBackground = a.getResourceId(
          R.styleable.Gallery_android_galleryItemBackground, 0);

      /* 把对象的styleable属性能够反复使用 */
      a.recycle();
    }

    /* 返回全部已定义图片的总量 */
    public int getCount()
    {
      return myImageURL.length;
    }

    /* 使用getItem方法获取当前容器中图像数的数组ID */
    public Object getItem(int position)
    {
      return position;
    }

    public long getItemId(int position)
    {
      return position;
    }

    /* 根据中央位移量，利用getScale返回views的大小(0.0f to 1.0f) */
    public float getScale(boolean focused, int offset)
    {
      /* Formula: 1 / (2 ^ offset) */
      return Math.max(0, 1.0f / (float) Math.pow(2, Math
          .abs(offset)));
    }

    /* 获取当前要显示的图像View，传入数组ID值使之读取并成像处理 */
    @Override
    public View getView(int position, View convertView,
        ViewGroup parent)
    {
      // TODO Auto-generated method stub
      /* 创建ImageView对象*/

      ImageView imageView = new ImageView(this.myContext);
      try
      {
        /* new URL将对象网址传入 */
        URL aryURI = new URL(myImageURL[position]);
        /* 获取连接 */
        URLConnection conn = aryURI.openConnection();
        conn.connect();
        /* 获取返回的InputStream */
        InputStream is = conn.getInputStream();
        /* 将InputStream变为Bitmap */
        Bitmap bm = BitmapFactory.decodeStream(is);
        /* 关闭InputStream */
        is.close();
        /* 设置Bitmap到ImageView中 */
        imageView.setImageBitmap(bm);
      } catch (IOException e)
      {
        e.printStackTrace();
      }

      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      /* 设置ImageView的宽和高，单位是dip 。*/
      imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
      /* 设置Gallery背景图*/
      imageView.setBackgroundResource(mGalleryItemBackground);
      return imageView;
    }
  }
}