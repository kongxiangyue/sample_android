package code.files;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

/**
 * �ļ��洢���ݷ�ʽ������
 * 
 * @author zuolongsnail
 */
public class FilesUtil {
	/**
	 * �����ļ�����
	 * 
	 * @param c
	 * @param fileName
	 *            �ļ�����
	 * @param content
	 *            ����
	 */
	private void writeFiles(Context c, String fileName, String content, int mode)
			throws Exception {
		// ���ļ���ȡ��������ļ����������Զ�����
		FileOutputStream fos = c.openFileOutput(fileName, mode);
		fos.write(content.getBytes());
		fos.close();
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param c
	 * @param fileName
	 *            �ļ�����
	 * @return �����ļ�����
	 */
	private String readFiles(Context c, String fileName) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis = c.openFileInput(fileName);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		String content = baos.toString();
		fis.close();
		baos.close();
		return content;
	}
}
