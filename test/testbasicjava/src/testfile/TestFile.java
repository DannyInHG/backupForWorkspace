package testfile;

import java.io.File;

public class TestFile {
	public static void main(String[] args)
	{
		File testFile = new File("F:/testFile.txt");
		System.out.println(testFile.renameTo(new File("F:/testfile")));
		System.out.print(testFile.canWrite());
	}
	
}
