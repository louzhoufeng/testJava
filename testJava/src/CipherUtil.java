

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class CipherUtil {
	/**
	  * �����ַ���
	  * @author sunju
	  * @creationDate. 2010-8-16 ����06:02:11 
	  * @param strIn ����ܵ��ַ���
	  * @return ���ܺ���ַ���
	  * @throws Exception
	  */
	public static String encrypt(String strIn) {
		if (strIn == null || "".equals(strIn)) 
			return "";
		return Base64.encode(strIn.getBytes());
	}

}
