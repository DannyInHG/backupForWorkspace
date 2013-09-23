package implementationarray;

/**
 * 主函数入口
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0 Establish date：2013-9-20
 */
public class ImplementationArray {
	public static void main(String[] args)
	{
/*		GetInputNumber getInputNumber = new GetInputNumber();
		System.out.println("Enter the first inputnumber:");
		int[]  firstInputNumber = getInputNumber.getInputNumber();
		
		System.out.println("Enter the second inputnumber:");
		int[]  secondInputNumber = getInputNumber.getInputNumber();*/
		
		
		long startTime = System.currentTimeMillis();
		
		int[]  firstInputNumber =new int[]{1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,
											7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,
											7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8};
		int[]  secondInputNumber =new int[]{1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,
  											7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,
  											7,6,5,4,3,2,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,2,3,4,5,6,7,8};
		
		System.out.println(firstInputNumber.length);
		Multiplication multiplication = new Multiplication();
		int[] multiplicationResult = multiplication.multiplication(firstInputNumber,secondInputNumber);
		
		System.out.println("The result of two large integers: ");
		for(int i = multiplicationResult.length-1; i>=0; i--)
			System.out.print(multiplicationResult[i]);
		long endTime = System.currentTimeMillis();
		System.out.println(" \n TotalRuntime:  "+(endTime-startTime)+"  milliseconds");
	}

}
