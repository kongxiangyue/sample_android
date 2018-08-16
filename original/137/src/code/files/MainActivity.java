package code.files;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText writeET;
	private Button writeBtn;
	private TextView contentView;
	public static final String FILENAME = "setting.set";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		writeET = (EditText) findViewById(R.id.write_et);
		writeBtn = (Button) findViewById(R.id.write_btn);
		contentView = (TextView) findViewById(R.id.contentview);
		writeBtn.setOnClickListener(new OperateOnClickListener());
	}

	class OperateOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			writeFiles(writeET.getText().toString());
			contentView.setText(readFiles());
			System.out.println(getFilesDir());
		}
	}

	// 保存文件内容
	private void writeFiles(String content) {
		try {
			// 打开文件获取输出流，文件不存在则自动创建
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读取文件内容
	private String readFiles() {
		String content = null;
		try {
			FileInputStream fis = openFileInput(FILENAME);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			content = baos.toString();
			fis.close();
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}