package implementationdc;

import implementationarray.GetInputNumber;

public class ImplementDC {
	public static void main(String[]  args)
	{
		GetInputNumber getInputNumber = new GetInputNumber();
		System.out.println("Enter the first inputnumber:");
		int[]  firstInputNumber = getInputNumber.getInputNumber();
		
		System.out.println("Enter the second inputnumber:");
		int[]  secondInputNumber = getInputNumber.getInputNumber();
		long startTime = System.currentTimeMillis();
		Dc dc = new Dc();
		int[] multiplicationResult = dc.multiplicationByDc(firstInputNumber, secondInputNumber);
		System.out.println("The result of two large integers: ");
		for(int i = multiplicationResult.length-1; i>=0; i--)
			System.out.print(multiplicationResult[i]);
		long endTime = System.currentTimeMillis();
		System.out.println(" \n TotalRuntime:  "+(endTime-startTime)+"  milliseconds");
	}
	
}
