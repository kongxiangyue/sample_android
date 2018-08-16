package com.lanya;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;

import java.io.OutputStream;
 
import java.util.UUID;

import com.lanya.R;
 

import android.app.Activity;
 
import android.bluetooth.BluetoothAdapter;
 
import android.bluetooth.BluetoothDevice;
 
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
 
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
 
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
 
import android.widget.Button;
import android.widget.Toast;
public class lanya extends Activity {
	private static final String TAG = "THINBTCLIENT";
	 
    private static final boolean D = true;

    private BluetoothAdapter mBluetoothAdapter = null;

    private BluetoothSocket btSocket = null;

    private OutputStream outStream = null;
    Button mButtonF;
   
    Button mButtonB;
    Button mButtonL;
    Button mButtonR;
    Button mButtonS;
 

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    private static String address = "00:11:03:21:00:43"; // <==Ҫ���ӵ������豸MAC��ַ


    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);                
            setContentView(R.layout.main);
            
            //ǰ��
            mButtonF=(Button)findViewById(R.id.btnF);
            mButtonF.setOnTouchListener(new Button.OnTouchListener(){
           
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					String message;
					byte[] msgBuffer;
					int action = event.getAction();
					switch(action)
					{
					case MotionEvent.ACTION_DOWN:
					try {
                      	outStream = btSocket.getOutputStream();

                      } catch (IOException e) {
                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
                      }


                      message = "1";

                     msgBuffer = message.getBytes();

                      try {
                      	outStream.write(msgBuffer);

                      } catch (IOException e) {
                              Log.e(TAG, "ON RESUME: Exception during write.", e);
                      }
					break;
					
					case MotionEvent.ACTION_UP:
						try {
	                      	outStream = btSocket.getOutputStream();

	                      } catch (IOException e) {
	                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
	                      }


	                      message = "0";

	                      msgBuffer = message.getBytes();

	                      try {
	                      	outStream.write(msgBuffer);

	                      } catch (IOException e) {
	                              Log.e(TAG, "ON RESUME: Exception during write.", e);
	                      }
						break;
					}
					return false;
				}
		
         
            });
          //����
            mButtonB=(Button)findViewById(R.id.btnB);
            mButtonB.setOnTouchListener(new Button.OnTouchListener(){
            
			
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					String message;
					byte[] msgBuffer;
					int action = event.getAction();
					switch(action)
					{
					case MotionEvent.ACTION_DOWN:
					try {
                      	outStream = btSocket.getOutputStream();

                      } catch (IOException e) {
                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
                      }


                      message = "3";

                     msgBuffer = message.getBytes();

                      try {
                      	outStream.write(msgBuffer);

                      } catch (IOException e) {
                              Log.e(TAG, "ON RESUME: Exception during write.", e);
                      }
					break;
					
					case MotionEvent.ACTION_UP:
						try {
	                      	outStream = btSocket.getOutputStream();

	                      } catch (IOException e) {
	                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
	                      }


	                      message = "0";

	                      msgBuffer = message.getBytes();

	                      try {
	                      	outStream.write(msgBuffer);

	                      } catch (IOException e) {
	                              Log.e(TAG, "ON RESUME: Exception during write.", e);
	                      }
						break;
					}
					
					return false;
				}
            	
         
            });
          //��ת
            mButtonL=(Button)findViewById(R.id.btnL);
            mButtonL.setOnTouchListener(new Button.OnTouchListener(){
            
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					String message;
					byte[] msgBuffer;
					int action = event.getAction();
					switch(action)
					{
					case MotionEvent.ACTION_DOWN:
					try {
                      	outStream = btSocket.getOutputStream();

                      } catch (IOException e) {
                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
                      }


                      message = "2";

                     msgBuffer = message.getBytes();

                      try {
                      	outStream.write(msgBuffer);

                      } catch (IOException e) {
                              Log.e(TAG, "ON RESUME: Exception during write.", e);
                      }
					break;
					
					case MotionEvent.ACTION_UP:
						try {
	                      	outStream = btSocket.getOutputStream();

	                      } catch (IOException e) {
	                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
	                      }


	                      message = "0";

	                      msgBuffer = message.getBytes();

	                      try {
	                      	outStream.write(msgBuffer);

	                      } catch (IOException e) {
	                              Log.e(TAG, "ON RESUME: Exception during write.", e);
	                      }
						break;
					}
					
					return false;
					
	             }
            });
          //��ת
            mButtonR=(Button)findViewById(R.id.btnR);
            mButtonR.setOnTouchListener(new Button.OnTouchListener(){
            
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					String message;
					byte[] msgBuffer;
					int action = event.getAction();
					switch(action)
					{
					case MotionEvent.ACTION_DOWN:
					try {
                      	outStream = btSocket.getOutputStream();

                      } catch (IOException e) {
                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
                      }


                      message = "4";

                     msgBuffer = message.getBytes();

                      try {
                      	outStream.write(msgBuffer);

                      } catch (IOException e) {
                              Log.e(TAG, "ON RESUME: Exception during write.", e);
                      }
					break;
					
					case MotionEvent.ACTION_UP:
						try {
	                      	outStream = btSocket.getOutputStream();

	                      } catch (IOException e) {
	                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
	                      }


	                      message = "0";

	                      msgBuffer = message.getBytes();

	                      try {
	                      	outStream.write(msgBuffer);

	                      } catch (IOException e) {
	                              Log.e(TAG, "ON RESUME: Exception during write.", e);
	                      }
						break;
					}
					
					return false;
				
				}
            	
         
            });
            
          //ֹͣ
            mButtonS=(Button)findViewById(R.id.btnS);
            mButtonS.setOnTouchListener(new Button.OnTouchListener(){
            
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction()==MotionEvent.ACTION_DOWN)
					 try {
	                      	outStream = btSocket.getOutputStream();

	                      } catch (IOException e) {
	                          Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
	                      }


	                      String message = "0";

	                      byte[] msgBuffer = message.getBytes();

	                      try {
	                      	outStream.write(msgBuffer);

	                      } catch (IOException e) {
	                              Log.e(TAG, "ON RESUME: Exception during write.", e);
	                      }
					return false;
				}
            	
         
            });
            
            

            if (D)
            	Log.e(TAG, "+++ ON CREATE +++");
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if (mBluetoothAdapter == null) { 
            	Toast.makeText(this, "�����豸�����ã����������", Toast.LENGTH_LONG).show();
                    finish();
                    return;
            }


            if (!mBluetoothAdapter.isEnabled()) {
                    Toast.makeText(this,  "����������������г���", Toast.LENGTH_LONG).show();
                    finish();
                    return;

            }


            if (D)
            Log.e(TAG, "+++ DONE IN ON CREATE, GOT LOCAL BT ADAPTER +++");

    }


    @Override

    public void onStart() {

            super.onStart();

            if (D) Log.e(TAG, "++ ON START ++");
    }


    @Override

    public void onResume() {

            super.onResume();
            if (D) {
            	Log.e(TAG, "+ ON RESUME +");
             Log.e(TAG, "+ ABOUT TO ATTEMPT CLIENT CONNECT +");

            }
            DisplayToast("���ڳ�����������С�������Ժ󡤡�����");
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

            try {

               btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);

            } catch (IOException e) {

                 DisplayToast("�׽��ִ���ʧ�ܣ�");
            }
            DisplayToast("�ɹ���������С�������Կ�ʼ�ٿ���~~~");
            mBluetoothAdapter.cancelDiscovery();
            try {

                    btSocket.connect();
                    DisplayToast("���ӳɹ��������������Ӵ򿪣�");
                  

            } catch (IOException e) {

                    try {
                    	btSocket.close();

                    } catch (IOException e2) {

                            
                            DisplayToast("����û�н������޷��ر��׽��֣�");
                    }

            }


            // Create a data stream so we can talk to server.
            
            if (D)
            	Log.e(TAG, "+ ABOUT TO SAY SOMETHING TO SERVER +");
         

    }


    @Override

    public void onPause() {

            super.onPause();


            if (D)
            	Log.e(TAG, "- ON PAUSE -");
            if (outStream != null) {
                    try {
                            outStream.flush();
                    } catch (IOException e) {
                            Log.e(TAG, "ON PAUSE: Couldn't flush output stream.", e);
                    }

            }


            try {
                    btSocket.close();
            } catch (IOException e2) {
                    
                    DisplayToast("�׽��ֹر�ʧ�ܣ�");
            }

    }


    @Override

    public void onStop() {

            super.onStop();

            if (D)Log.e(TAG, "-- ON STOP --");

    }


    @Override

    public void onDestroy() {

            super.onDestroy();

            if (D) Log.e(TAG, "--- ON DESTROY ---");

    }
    public void DisplayToast(String str)
    {
    	Toast toast=Toast.makeText(this, str, Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.TOP, 0, 220);
    	toast.show();
    	
    }

}