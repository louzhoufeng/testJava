import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		 SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

	       String times ="2012-10-15 11:03:00";

	       Date date = format.parse(times);
	       
		Date time = new Date();
		System.out.println(time.getTime());
		System.out.println(date.getTime());
		long num = (time.getTime() - date.getTime());
		System.out.println(num);
		System.out.println(num/(3600*1000));
		
		System.out.println(Math.toDegrees(Math.atan(1)));
		
		String info = "20120304".substring(6,8);
		System.out.println(info);
		
		double ft = 32.34 ;
		long ln = (long)ft ;
		System.out.println(ln);
		
		int BACK_DURATION = 20 ;   // 20ms
	    //水平方向前进速率
		float VE_HORIZONTAL = 0.7f ;  //0.1dip/ms
		System.out.println(BACK_DURATION*VE_HORIZONTAL);
		
		String infoStr="123";
		updateString(infoStr);
		System.out.println(infoStr);
	}

	private static void updateString(String info){
		info="dateupdate";
	}
}