package com.zp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zp.util.ContactActivity;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	private TextView tv = null;
	private TextView stateTips = null;
	private TextView phoneView = null;
	private Button btncontact = null;
	private Button btnset = null;
	private EditText etPhone = null;
	private Button btnCancel = null;
	private OnClickListener btncontactListener = null;
	private OnClickListener btnsetListener = null;
	private OnClickListener btncancelListener = null;
	
	private mPhoneCallListener phoneListener;
    private TelephonyManager telMgr;
	private static final int CONTACT_LIST = 0;
//	private static final int RING_LIST = 1;
//	private static final int CALL_IN = 2;
//	private static final int CALL_SETTING = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		prepareListener();
		setListener();

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		return buildDialog(MainActivity.this);
		
	}

	/**
	 * ��ʼ����ͼ���
	 */
	private void initView() {
		tv = (TextView) findViewById(R.id.apptip);
		stateTips = (TextView) findViewById(R.id.mTextView01);
		phoneView = (TextView) findViewById(R.id.TextView02);
		btncontact = (Button) findViewById(R.id.btnConact);
		btnset = (Button) findViewById(R.id.btnSet);
		etPhone = (EditText) findViewById(R.id.phonetext);
		btnCancel = (Button)findViewById(R.id.btnCancel);	
	}

	/**
	 * ��ʼ������¼�������
	 */
	private void prepareListener() {
		btncontactListener = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						ContactActivity.class);
				startActivityForResult(intent, CONTACT_LIST);
			}

		};
		btnsetListener = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				phoneListener = new mPhoneCallListener();
				/**
				 * ����TelephonyManagerȥץȡtelephone service
				 */
				telMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
				/* Listen Call */
				telMgr.listen(phoneListener, mPhoneCallListener.LISTEN_CALL_STATE);
				showDialog(0);
			}

		};

		btncancelListener = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//TelephonyManager telMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

				telMgr.listen(phoneListener, mPhoneCallListener.LISTEN_NONE);
				showDialog(0);
			}
			
		};
	}

	/**
	 * �������������
	 */
	private void setListener() {
		btncontact.setOnClickListener(btncontactListener);
		btnset.setOnClickListener(btnsetListener);
		btnCancel.setOnClickListener(btncancelListener);
	}

	/**
	 * ��ȡ����ֵ�Ļص�����
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CONTACT_LIST: {
			Bundle extras = data.getExtras();
			etPhone.setText(extras.getString("Number"));
			// editPhonerName.setText(extras.getString("Name"));
			phoneView.setText(extras.getString("Number"));
		}
			break;
		}
	}
    /**
     * �̳�phonestatelistenerʵ���Լ����߼�����
     * @author Administrator
     *
     */
	public class mPhoneCallListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			switch (state) {
			/* ��ȡ���ֻ��Ǵ���״̬ */
			case TelephonyManager.CALL_STATE_IDLE:
				stateTips.setText(R.string.str_CALL_STATE_IDLE);

				try {
					AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
					if (audioManager != null) {
						/* �����ֻ�Ϊ����ʱ����Ϊ����ģʽ */
						audioManager
								.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
						audioManager.getStreamVolume(AudioManager.STREAM_RING);
					}
				} catch (Exception e) {
					stateTips.setText(e.toString());
					e.printStackTrace();
				}
				break;

			/* ��ȡ���ֻ�״̬Ϊͨ���� */
			case TelephonyManager.CALL_STATE_OFFHOOK:
				stateTips.setText(R.string.str_CALL_STATE_OFFHOOK);
				break;

			/* ��ȡ���ֻ�״̬Ϊ���� */
			case TelephonyManager.CALL_STATE_RINGING:
				/* ��ʾ������Ϣ�� */
				stateTips.setText(getResources().getText(
						R.string.str_CALL_STATE_RINGING)
						+ incomingNumber);
                Log.i("�绰",phoneView.getText().toString());
				/* ���ж�������绰�Ƿ�Ϊһ����һ����תΪ����ģʽ�� */
				if (incomingNumber.equals(phoneView.getText().toString())) {
					try {
						AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						if (audioManager != null) {
							/* ��������ģʽΪ������ */
							audioManager
									.setRingerMode(AudioManager.RINGER_MODE_SILENT);
							audioManager
									.getStreamVolume(AudioManager.STREAM_RING);
							Toast.makeText(MainActivity.this,
									getString(R.string.str_msg),
									Toast.LENGTH_LONG).show();
						}
					} catch (Exception e) {
						stateTips.setText(e.toString());
						e.printStackTrace();
						break;
					}
				}
			}

			super.onCallStateChanged(state, incomingNumber);

			etPhone.setOnKeyListener(new EditText.OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					/* ͬ����ʾ�CTextView */
					phoneView.setText(etPhone.getText());
					return false;
				}
			});
		}
	}

    /**
     * ����Ի���
     * @param context
     * @return
     */
    private Dialog buildDialog(Context context) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	builder.setTitle(R.string.alertTips);
		builder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
					}
				});
//		builder.setNegativeButton("ȡ��",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						
//					}
//				});    	
    	return builder.create();
    }
}