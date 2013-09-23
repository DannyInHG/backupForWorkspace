package testreadline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 测试从键盘读取字符串能不能用。
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-12
 */
public class TestReadLine {
	public static void main(String[] args)
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
		try {
			String bufferedReadLine = bufferedReader.readLine();
			int temp = Integer.parseInt(bufferedReadLine);
			System.out.println(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
