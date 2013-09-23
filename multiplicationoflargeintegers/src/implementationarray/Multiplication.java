package implementationarray;


/**
 * 算法实现部分代码
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0 Establish date：2013-9-20
 */
public class Multiplication {
	public int[] multiplication(int[] firstInputNumber,int[] secondInputNumber)
	{
		int[] sumOfTwoLargeNum = new int[firstInputNumber.length + secondInputNumber.length];
		SumOfTwoNum sumOfTwoNum = new SumOfTwoNum();
		SumOfTwoNum sumOfTwoNumTemp = new SumOfTwoNum();
		
		for(int i = 0 ; i <= firstInputNumber.length-1 ; i ++)
		{
			for(int j = 0 ; j <= secondInputNumber.length-1 ; j ++)
			{
				int firstNum = firstInputNumber[i];
				int secondNum = secondInputNumber[j];
				sumOfTwoNum = smallNumberMultiplicate(sumOfTwoNum, firstNum, secondNum);
				
				/**
				 * 这个部分应该写成递归函数，总是进行递归，一直到<=9才能够停止递归
				 */
				if((sumOfTwoLargeNum[i+j] += sumOfTwoNum.getLowerOrderNum()) >= 10)
				{
					int count = i+j;
					carrySet(sumOfTwoLargeNum,count,sumOfTwoNumTemp);
				}
				
				
				/**
				 * 这里应该写成和上面一样形式，应该可以共用一个方法
				 */
				if((sumOfTwoLargeNum[i+j+1] += sumOfTwoNum.getHighOrderNum()) >= 10)
				{
					int count = i+j+1;
					carrySet(sumOfTwoLargeNum,count,sumOfTwoNumTemp);
				}
			}
		}
	
		return sumOfTwoLargeNum;
	}
	
	
	public void carrySet(int[] sumOfTwoLargeNum,int count,SumOfTwoNum sumOfTwoNumTemp)
	{
	
			sumOfTwoNumTemp.setLowerOrderNum(sumOfTwoLargeNum[count]%10);
			sumOfTwoNumTemp.setHighOrderNum((sumOfTwoLargeNum[count]/10)%10);
			sumOfTwoLargeNum[count] = sumOfTwoNumTemp.getLowerOrderNum();
			if((sumOfTwoLargeNum[count+1]+= sumOfTwoNumTemp.getHighOrderNum()) >= 10)
			{
				count++;
				carrySet(sumOfTwoLargeNum, count, sumOfTwoNumTemp);
			}
			
	}
	
	private SumOfTwoNum smallNumberMultiplicate(SumOfTwoNum sumOfTwoNum,int firstNum,int secondNum)
	{
		int sumOfTwoNumTemp = firstNum*secondNum ;
		sumOfTwoNum.setHighOrderNum((sumOfTwoNumTemp/10)%10);
		sumOfTwoNum.setLowerOrderNum(sumOfTwoNumTemp%10);
		return sumOfTwoNum;
	}
}
