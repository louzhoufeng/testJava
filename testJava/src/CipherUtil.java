

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class CipherUtil {
	/**
	  * 加密字符串
	  * @author sunju
	  * @creationDate. 2010-8-16 下午06:02:11 
	  * @param strIn 需加密的字符串
	  * @return 加密后的字符串
	  * @throws Exception
	  */
	public static String encrypt(String strIn) {
		if (strIn == null || "".equals(strIn)) 
			return "";
		return Base64.encode(strIn.getBytes());
	}

}
