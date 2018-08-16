package menu061;

import gen.menu061.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Menu061 extends Activity {
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	
	Button button1;
	Button button2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.INVISIBLE);
		

    }
	@Override
	/*
	 * menu.findItem(EXIT_ID);�ҵ��ض���MenuItem
	 * MenuItem.setIcon.��������menu��ť�ı���
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "��ʱ��ʾbutton1");
		menu.add(0, ITEM1, 0, "��ʱ��ʾbutton2");
		menu.findItem(ITEM1);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ITEM0: 
			actionClickMenuItem1();
		break;
		case ITEM1: 
			actionClickMenuItem2(); break;

		}
		return super.onOptionsItemSelected(item);}
	/*
	 * �����һ��menu�ĵ�һ����ťִ�еĶ���
	 */
	private void actionClickMenuItem1(){
		setTitle("button1 �ɼ�");
		button1.setVisibility(View.VISIBLE);
		button2.setVisibility(View.INVISIBLE);
	}
	/*
	 * ����ڶ�����menu�ĵ�һ����ťִ�еĶ���
	 */
	private void actionClickMenuItem2(){
		setTitle("���Կ���button2 ");
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.VISIBLE);
	}
}