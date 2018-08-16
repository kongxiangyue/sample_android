package dialog064;

import com.dialog064.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dialog064 extends Activity {
	private static final int DIALOG1 = 1;
	private static final int DIALOG2 = 2;
	private static final int DIALOG4 = 4;
	private static final int DIALOG3 = 3;


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG1:
			return buildDialog1(Dialog064.this);

		case DIALOG2:
			return buildDialog2(Dialog064.this);

		case DIALOG3:
			return buildDialog3(Dialog064.this);
		case DIALOG4:
			return buildDialog4(Dialog064.this);

		}
		return null;
	}
	
	protected void onPrepareDialog(int id, Dialog dialog){
		if(id==DIALOG1){
			setTitle("����");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_dialog);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG1);
			}
		});

		Button buttons2 = (Button) findViewById(R.id.buttons2);
		buttons2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG2);
			}
		});

		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG3);
			}
		});
		
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG4);
			}
		});
	}

	private Dialog buildDialog1(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_two_buttons_title);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("���ղŵ���˶Ի����ϵ�ȷ����ť");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("���ղŵ���˶Ի����ϵ�ȡ����ť");
					}
				});
		return builder.create();

	}
	private Dialog buildDialog2(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_two_buttons_msg);
		builder.setMessage(R.string.alert_dialog_two_buttons2_msg);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("���ղŵ���˶Ի����ϵ�ȷ����ť");
					}
				});
		builder.setNeutralButton(R.string.alert_dialog_something,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("���ղŵ���˶Ի����ϵĽ�����ϸ��ť");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("���ղŵ���˶Ի����ϵ�ȡ����ť");
					}
				});
		return builder.create();
	}

	private Dialog buildDialog3(Context context) {
		LayoutInflater inflater = LayoutInflater.from(this);
		final View textEntryView = inflater.inflate(
				R.layout.text_entry, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_text_entry);
		builder.setView(textEntryView);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("���ղŵ���˶Ի����ϵ�ȷ����ť");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("���ղŵ���˶Ի����ϵ�ȡ����ť");
					}
				});
		return builder.create();
	}
	

	private Dialog buildDialog4(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle("���ڴ�����...");
		dialog.setMessage("��һ��ɡ���");
		return  dialog;
	}
}