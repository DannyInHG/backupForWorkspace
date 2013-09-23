package testseri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSeri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileOutputStream fileStream = new FileOutputStream("F:/testseri.txt");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			
			os.writeObject("hello world!");
			os.writeObject("echo,I love you!");
			
			os.close();
			
			
			FileInputStream fileInStream = new FileInputStream("F:/testseri.txt");
			ObjectInputStream is = new ObjectInputStream(fileInStream);
			
			int num = is.available();
			Object ob = is.readObject();
			Object express = is.readObject();
			
			System.out.println(num);
			System.out.println(ob.toString());
			System.out.print(express.toString());
			
			is.close();
			
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		

	}

}
