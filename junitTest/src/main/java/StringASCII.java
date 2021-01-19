

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StringASCII {
	public static void main(String[] args) {
		String s="a4k3b2";
		StringASCII obj=new StringASCII();
		obj.m(s);
	}
	public void m(String s) {
		
		
		  StringBuffer sb=new StringBuffer(); char[] c=s.toCharArray(); for (int i = 0;
		  i < c.length; i++) { char temp = c[i]; int
		  num=Integer.parseInt(String.valueOf(c[i+1]));
		  
		  char letter=(char) (temp + num); i++; sb.append(temp).append(letter); }
		  System.out.println(sb);
		
	}

}
