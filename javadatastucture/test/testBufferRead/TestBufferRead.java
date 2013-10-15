package testBufferRead;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestBufferRead {
	public static void main(String[] args)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		try {
			
			count = br.read();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println((char)count);
	}
}
