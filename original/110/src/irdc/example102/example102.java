package irdc.example102;

/* import相关class */
import irdc.example102.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class example102 extends ListActivity
{
  /* 对象声明 
     items：存放显示的名称
     paths：存放文件路径
     rootPath：起始目录
  */
  private List<String> items=null;
  private List<String> paths=null;
  private String rootPath="/";
  private TextView mPath;
  private View myView;
  private EditText myEditText;

  @Override
  protected void onCreate(Bundle icicle)
  {
    super.onCreate(icicle);
    /* 载入main.xml Layout */

    setContentView(R.layout.main);
    /* 初始化mPath，用以显示目前路径 */
    mPath=(TextView)findViewById(R.id.mPath);
    getFileDir(rootPath);
  }
  
  /* 取得文件架构的方法 */
  private void getFileDir(String filePath)
  {
    /* 设置目前所在路径 */
    mPath.setText(filePath);
    items=new ArrayList<String>();
    paths=new ArrayList<String>();
    
    File f=new File(filePath);  
    File[] files=f.listFiles();

    if(!filePath.equals(rootPath))
    {
      /* 第一笔设置为[回到根目录] */
      items.add("b1");
      paths.add(rootPath);
      /* 第二笔设置为[回上层] */
      items.add("b2");
      paths.add(f.getParent());
    }
    /* 将所有文件添加ArrayList中 */
    for(int i=0;i<files.length;i++)
    {
      File file=files[i];
      items.add(file.getName());
      paths.add(file.getPath());
    }

    /* 使用自定义的MyAdapter来将数据传入ListActivity */
    setListAdapter(new MyAdapter(this,items,paths));
  }

  /* 设置ListItem被点击时要做的动作 */
  @Override
  protected void onListItemClick(ListView l,View v,int position,
                                 long id)
  {
    File file = new File(paths.get(position));

    if(file.isDirectory())
    {
      /* 如果是文件夹就再运行getFileDir() */
      getFileDir(paths.get(position));
    }
    else
    {
      /* 如果是文件调用fileHandle() */
      fileHandle(file);
    }
  }

  /* 处理文件的方法 */
  private void fileHandle(final File file){
    /* 点击文件时的OnClickListener */
    OnClickListener listener1=new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog,int which)
      {
        if(which==0)
        {
          /* 选择的item为打开文件 */
          openFile(file);
        }
        else if(which==1)
        {
          /* 选择的item为更改档名 */
          LayoutInflater factory=LayoutInflater.from(example102.this);
          /* 初始化myChoiceView，使用rename_alert_dialog为layout */
          myView=factory.inflate(R.layout.alert_dialog,null);
          myEditText=(EditText)myView.findViewById(R.id.mEdit);
          /* 将原始文件名先放入EditText中 */
          myEditText.setText(file.getName());

          /* new一个更改文件名的Dialog的确定按钮的listener */
          OnClickListener listener2=
          new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
              /* 取得修改后的文件路径 */
              String modName=myEditText.getText().toString();
              final String pFile=file.getParentFile().getPath()+"/";
              final String newPath=pFile+modName;

              /* 判断档名是否已存在 */
              if(new File(newPath).exists())
              {
                /* 排除修改档名时没修改直接送出的状况 */
                if(!modName.equals(file.getName()))
                {
                  /* 跳出Alert警告档名重复，并确认是否修改 */
                  new AlertDialog.Builder(example102.this)
                      .setTitle("注意!")
                      .setMessage("已经存在，是否要覆盖?")
                      .setPositiveButton("确定",
                       new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                          /* 档名重复仍然修改会覆改掉已存在的文件 */
                          file.renameTo(new File(newPath));
                          /* 重新产生文件列表的ListView */
                          getFileDir(pFile);
                        }
                      })
                      .setNegativeButton("取消",
                       new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                        }
                      }).show();
                }
              }
              else
              {
                /* 档名不存在，直接做修改动作 */
                file.renameTo(new File(newPath));
                /* 重新产生文件列表的ListView */
                getFileDir(pFile);
              }
            }
          };

          /* create更改档名时跳出的Dialog */
          AlertDialog renameDialog=
            new AlertDialog.Builder(example102.this).create();
          renameDialog.setView(myView);

          /* 设置更改档名点击确认后的Listener */
          renameDialog.setButton("确定",listener2);
          renameDialog.setButton2("取消",
          new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
            }
          });
          renameDialog.show();
        }
        else
        {
          /* 选择的item为删除文件 */
          new AlertDialog.Builder(example102.this).setTitle("请注意!")
              .setMessage("确定删除?")
              .setPositiveButton("确定",
               new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog,
                                    int which)
                {          
                  /* 删除文件 */
                  file.delete();
                  getFileDir(file.getParent());
                }
              })
              .setNegativeButton("取消",
               new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog,
                                    int which)
                {
                }
              }).show();
        }
      }
    };

    /* 选择一个文件时，跳出要如何处理文件的ListDialog */
    String[] menu={"打开文件","改名","删除"};
    new AlertDialog.Builder(example102.this)
        .setTitle("准备干啥?")
        .setItems(menu,listener1)
        .setPositiveButton("取消",
         new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int which)
          {
          }
        })
        .show();
  }

  /* 在手机上打开文件的方法 */
  private void openFile(File f) 
  {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(android.content.Intent.ACTION_VIEW);

    /* 调用getMIMEType()来取得MimeType */
    String type = getMIMEType(f);
    /* 设置intent的file与MimeType */
    intent.setDataAndType(Uri.fromFile(f),type);
    startActivity(intent); 
  }

  /* 判断文件MimeType的方法 */
  private String getMIMEType(File f) 
  { 
    String type="";
    String fName=f.getName();
    /* 取得扩展名 */
    String end=fName.substring(fName.lastIndexOf(".")+1,
                               fName.length()).toLowerCase(); 

    /* 依附档名的类型决定MimeType */
    if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")
       ||end.equals("xmf")||end.equals("ogg")||end.equals("wav"))
    {
      type = "audio"; 
    }
    else if(end.equals("3gp")||end.equals("mp4"))
    {
      type = "video";
    }
    else if(end.equals("jpg")||end.equals("gif")||end.equals("png")
             ||end.equals("jpeg")||end.equals("bmp"))
    {
      type = "image";
    }
    else
    {
      /* 如果无法直接打开，就跳出软件列表给用户选择 */
      type="*";
    }
    type += "/*"; 
    return type; 
  }
}
