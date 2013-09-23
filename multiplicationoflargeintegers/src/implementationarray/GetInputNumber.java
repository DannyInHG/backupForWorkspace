package implementationarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 获取数据
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0 Establish date：2013-9-20
 */
public class GetInputNumber {
	public int[] getInputNumber()
	{
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
			String bufferedFirstNumber = bufferedReader.readLine();
			char[] arrayFirstNumber = bufferedFirstNumber.toCharArray();

			System.out.println(arrayFirstNumber.length);
			int[] firstNumberSeri = new int[arrayFirstNumber.length];
			int count = 0 ;
			for(int i = arrayFirstNumber.length-1;i >= 0 ;i--)	
			{
				//char到int的转换
				firstNumberSeri[count] = arrayFirstNumber[i]-48;
				count++;
			}
			return firstNumberSeri;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
