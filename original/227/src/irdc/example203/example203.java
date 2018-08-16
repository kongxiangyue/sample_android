package irdc.example203;

import irdc.example203.R;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class example203 extends Activity 
implements SurfaceHolder.Callback
{
  /* ����˽��Camera���� */
  private Camera mCamera01;
  private Button mButton01, mButton02, mButton03;
    
  /* ��Ϊreview����������Ƭ֮�� */
  private ImageView mImageView01;
  private String TAG = "HIPPO";
  private SurfaceView mSurfaceView01;
  private SurfaceHolder mSurfaceHolder01;
  
  /* Ĭ�����Ԥ��ģʽΪfalse */
  private boolean bIfPreview = false;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /* ʹӦ�ó���ȫ��Ļ���У���ʹ��title bar */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    
    setContentView(R.layout.main);
    
    /* ��Ӻ�ɫ�����κ��View����User��׼������ */
    DrawCaptureRect mDraw = new DrawCaptureRect
    (
      example203.this,
      110, 10, 100, 100,
      getResources().getColor(R.drawable.lightred)
    );
    
    /* �������ĺ�ɫ�����������Activity�� */
    addContentView
    (
      mDraw,
      new LayoutParams
      (
        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
      )
    );
    
    /* ȡ����Ļ�������� */
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    
    mImageView01 = (ImageView) findViewById(R.id.myImageView1);
    
    /* ��SurfaceView��Ϊ���Preview֮�� */
    mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
    
    /* ��SurfaceView��ȡ��SurfaceHolder���� */
    mSurfaceHolder01 = mSurfaceView01.getHolder();
    
    /* Activity����ʵ��SurfaceHolder.Callback */
    mSurfaceHolder01.addCallback(example203.this);
    
    /* ���������Ԥ����С���ã��ڴ˲�ʹ�� */
    //mSurfaceHolder01.setFixedSize(320, 240);
      
    /*
     * ��SURFACE_TYPE_PUSH_BUFFERS(3)
     * ��ΪSurfaceHolder��ʾ���� 
     * */
    mSurfaceHolder01.setType
    (SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton02 = (Button)findViewById(R.id.myButton2);
    mButton03 = (Button)findViewById(R.id.myButton3);
    
    /* �������Ԥ����ά������ */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* �Զ����ʼ����������� */
        try
        {
          initCamera();
        } catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    
    /* ֹͣԤ�� */
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* �Զ���������������ر����Ԥ������ */
        resetCamera();
      }
    });
    
    /* ����QR Code��ά������ */
    mButton03.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        /* �Զ������պ��� */
        takePicture();
      }
    });
  }
  
  /* �Զ����ʼ������� */
  private void initCamera() throws IOException
  {
    if(!bIfPreview)
    {
      /* ���������Ԥ��ģʽ�������� */
      mCamera01 = Camera.open();
    }
    
    if (mCamera01 != null && !bIfPreview)
    {
      Log.i(TAG, "inside the camera");
      
      /* ����Camera.Parameters���� */
      Camera.Parameters parameters = mCamera01.getParameters();
      
      /* ������Ƭ��ʽΪJPEG */
      parameters.setPictureFormat(PixelFormat.JPEG);
      
      /* ָ��preview����Ļ��С */
      parameters.setPreviewSize(160, 120);
      
      /* ����ͼƬ�ֱ��ʴ�С */
      parameters.setPictureSize(160, 120);
      
      /* ��Camera.Parameters������Camera */
      mCamera01.setParameters(parameters);
      
      /* setPreviewDisplayΨһ�Ĳ���ΪSurfaceHolder */
      mCamera01.setPreviewDisplay(mSurfaceHolder01);
      
      /* ��������Preview */
      mCamera01.startPreview();
      bIfPreview = true;
    }
  }
  
  /* ����ߢȡͼ�� */ 
  private void takePicture() 
  {
    if (mCamera01 != null && bIfPreview) 
    {
      /* ����takePicture()�������� */
      mCamera01.takePicture
      (shutterCallback, rawCallback, jpegCallback);
    }
  }
  
  /* ������� */
  private void resetCamera()
  {
    if (mCamera01 != null && bIfPreview)
    {
      mCamera01.stopPreview();
      /* ��չѧϰ���ͷ�Camera���� */
      //mCamera01.release();
      mCamera01 = null;
      bIfPreview = false;
    }
  }
   
  private ShutterCallback shutterCallback = new ShutterCallback()
  { 
    public void onShutter() 
    { 
      // Shutter has closed 
    } 
  }; 
   
  private PictureCallback rawCallback = new PictureCallback() 
  { 
    public void onPictureTaken(byte[] _data, Camera _camera) 
    { 
      // TODO Handle RAW image data 
    } 
  }; 

  private PictureCallback jpegCallback = new PictureCallback() 
  {
    public void onPictureTaken(byte[] _data, Camera _camera)
    {
      // TODO Handle JPEG image data
      try
      {
        /* onPictureTaken����ĵ�һ��������Ϊ��Ƭ��byte */
        Bitmap bm =
        BitmapFactory.decodeByteArray(_data, 0, _data.length);
        
        int resizeWidth = 160;
        int resizeHeight = 120;
        float scaleWidth = ((float) resizeWidth) / bm.getWidth();
        float scaleHeight = ((float) resizeHeight) / bm.getHeight();
        
        Matrix matrix = new Matrix();
        /* ʹ��Matrix.postScale������С Bitmap Size*/
        matrix.postScale(scaleWidth, scaleHeight);
        
        /* �����µ�Bitmap���� */
        Bitmap resizedBitmap = Bitmap.createBitmap
        (bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        
        /* ߢȡ4:3ͼƬ�ľ��к�ɫ�򲿷�100x100���� */
        Bitmap resizedBitmapSquare = Bitmap.createBitmap
        (resizedBitmap, 30, 10, 100, 100);
        
        /* �����յ�ͼ�ļ���ImageView��ʾ���� */
        mImageView01.setImageBitmap(resizedBitmapSquare);
        
        /* �������ͼ�ļ�������ַ��� */
        String strQR2 = decodeQRImage(resizedBitmapSquare);
        if(strQR2!="")
        {
          if (URLUtil.isNetworkUrl(strQR2))
          {
            /* OMIA�淶����ַ�����룬����������� */
            mMakeTextToast(strQR2, true);
            Uri mUri = Uri.parse(strQR2);
            Intent intent = new Intent(Intent.ACTION_VIEW, mUri);
            startActivity(intent);
          }
          else if(eregi("wtai://",strQR2))
          {
            /* OMIA�淶���ֻ�����绰��ʽ */
            String[] aryTemp01 = strQR2.split("wtai://");
            Intent myIntentDial = new Intent
            (
              "android.intent.action.CALL",
              Uri.parse("tel:"+aryTemp01[1])
            );
            startActivity(myIntentDial); 
          }
          else if(eregi("TEL:",strQR2))
          {
            /* OMIA�淶���ֻ�����绰��ʽ */
            String[] aryTemp01 = strQR2.split("TEL:");
            Intent myIntentDial = new Intent
            (
              "android.intent.action.CALL",
              Uri.parse("tel:"+aryTemp01[1])
            );
            startActivity(myIntentDial);
          }
          else
          {
            /* ���������֣�����Toast��ʾ���� */
            mMakeTextToast(strQR2, true);
          }
        }
        
        /* ��ʾ��ͼ�ļ�������������������ر�Ԥ�� */
        resetCamera();
        
        /* �����������������Ԥ�� */
        initCamera();
      }
      catch (Exception e)
      {
        Log.e(TAG, e.getMessage());
      }
    }
  };
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(example203.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(example203.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  private boolean checkSDCard()
  {
    /* �жϼ��俨�Ƿ���� */
    if(android.os.Environment.getExternalStorageState().equals
    (android.os.Environment.MEDIA_MOUNTED))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /* ��a�����BitmapͼƬ */
  public String decodeQRImage(Bitmap myBmp)
  {
    String strDecodedData = "";
    try
    {
      QRCodeDecoder decoder = new QRCodeDecoder();
      strDecodedData  = new String
      (decoder.decode(new AndroidQRCodeImage(myBmp)));
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return strDecodedData; 
  }
  
  /* �Զ���ʵ��QRCodeImage�� */
  class AndroidQRCodeImage implements QRCodeImage
  {
    Bitmap image;
    
    public AndroidQRCodeImage(Bitmap image)
    {
      this.image = image;
    }
    
    public int getWidth()
    {
      return image.getWidth();
    }
    
    public int getHeight()
    {
      return image.getHeight();
    }
    
    public int getPixel(int x, int y)
    {
      return image.getPixel(x, y);
    }   
  }
  
  /* �������Ԥ��������������η��� */
  class DrawCaptureRect extends View
  {
    private int colorFill;
    private int intLeft,intTop,intWidth,intHeight;
    
    public DrawCaptureRect
    (
      Context context, int intX, int intY, int intWidth,
      int intHeight, int colorFill
    )
    {
      super(context);
      this.colorFill = colorFill;
      this.intLeft = intX;
      this.intTop = intY;
      this.intWidth = intWidth;
      this.intHeight = intHeight;
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
      Paint mPaint01 = new Paint();
      mPaint01.setStyle(Paint.Style.FILL);
      mPaint01.setColor(colorFill);
      mPaint01.setStrokeWidth(1.0F);
      /* �ڻ����ϻ��ƺ�ɫ���������߿���Ϊ��׼�� */
      canvas.drawLine
      (
        this.intLeft, this.intTop,
        this.intLeft+intWidth, this.intTop, mPaint01
      );
      canvas.drawLine
      (
        this.intLeft, this.intTop,
        this.intLeft, this.intTop+intHeight, mPaint01
      );
      canvas.drawLine
      (
        this.intLeft+intWidth, this.intTop,
        this.intLeft+intWidth, this.intTop+intHeight, mPaint01
      );
      canvas.drawLine
      (
        this.intLeft, this.intTop+intHeight,
        this.intLeft+intWidth, this.intTop+intHeight, mPaint01
      );
      super.onDraw(canvas);
    }
  }
  
  /* �Զ���Ƚ��ַ������� */
  public static boolean eregi(String strPat, String strUnknow)
  {
    String strPattern = "(?i)"+strPat;
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strUnknow);
    return m.find();
  }
  
  @Override
  public void surfaceChanged
  (SurfaceHolder surfaceholder, int format, int w, int h)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceCreated(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Destroyed");
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
  }
}
