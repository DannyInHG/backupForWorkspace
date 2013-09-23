package exectest;

import java.io.IOException;

public class ExecTest {
	public static void main(String[] args)
	{
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c start java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
