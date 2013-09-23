package implementationdc;

import implementationarray.Multiplication;
import implementationarray.SumOfTwoNum;

public class Dc {
	public int[] multiplicationByDc(int[] firstInputNumber,int[] secondInputNumber)
	{
		Multiplication multiplication = new Multiplication();
		SumOfTwoNum sumOfTwoNumTemp = new SumOfTwoNum();
		int[] firstNumberHighOrder =  new int[firstInputNumber.length/2+1];
		int[] firstNumberLowerOrder =  new int[firstInputNumber.length/2+1];
		int firstHighOrder = 0;
		int secondHighOrder = 0;
		if(firstInputNumber.length%2 == 0 )
		{
			firstHighOrder = firstInputNumber.length/2;
			for(int i = 0; i <= firstInputNumber.length/2-1; i++)
				firstNumberLowerOrder[i] = firstInputNumber[i];
			for(int i = firstInputNumber.length/2; i <= firstInputNumber.length-1; i++)
				firstNumberHighOrder[i] = firstInputNumber[i];
		}
		else
		{
			firstHighOrder = (firstInputNumber.length+1)/2-1;
			for(int i = 0; i <= (firstInputNumber.length+1)/2-1; i++)
				firstNumberLowerOrder[i] = firstInputNumber[i];
			for(int i = (firstInputNumber.length+1)/2; i <= firstInputNumber.length-1; i++)
				firstNumberHighOrder[i] = firstInputNumber[i];
		}
		
		int[] secondNumberHighOrder =  new int[secondInputNumber.length/2+1];
		int[] secondNumberLowerOrder =  new int[secondInputNumber.length/2+1];
		if(secondInputNumber.length%2 == 0 )
		{
			secondHighOrder = secondInputNumber.length/2;
			for(int i = 0; i <= secondInputNumber.length/2-1; i++)
				secondNumberLowerOrder[i] = secondInputNumber[i];
			for(int i = secondInputNumber.length/2; i <= secondInputNumber.length-1; i++)
				secondNumberHighOrder[i] = secondInputNumber[i];
		}
		else
		{
			secondHighOrder = (secondInputNumber.length+1)/2-1;
			for(int i = 0; i <= (secondInputNumber.length+1)/2-1; i++)
				secondNumberLowerOrder[i] = secondInputNumber[i];
			for(int i = (secondInputNumber.length+1)/2; i <= secondInputNumber.length-1; i++)
				secondNumberHighOrder[i] = secondInputNumber[i];
		}
		
		int[] multiplicationLowerResult = multiplication.multiplication(firstNumberLowerOrder, secondNumberLowerOrder);
		int[] multiplicationHighResult = multiplication.multiplication(firstNumberHighOrder, secondNumberHighOrder);
		
		
		
		int[] lowerOrderSum = new int[(firstNumberLowerOrder.length+secondNumberLowerOrder.length)/2+1];
		int[] highOrderSum = new int[(firstNumberHighOrder.length+secondNumberHighOrder.length)/2+1];
		
		if(firstNumberLowerOrder.length >= secondNumberLowerOrder.length)
		{
			for(int i = 0 ; i <= firstNumberLowerOrder.length-1; i++)
			{
				
				lowerOrderSum[i] = firstNumberLowerOrder[i] + secondNumberLowerOrder[i];
				multiplication.carrySet(lowerOrderSum, i, sumOfTwoNumTemp);
			}
		}else
		{}
		
		if(firstNumberHighOrder.length >= secondNumberHighOrder.length)
		{
			for(int i = 0 ; i <= firstNumberHighOrder.length-1; i++)
			{
				
				lowerOrderSum[i] = firstNumberHighOrder[i] + secondNumberHighOrder[i];
				multiplication.carrySet(lowerOrderSum, i, sumOfTwoNumTemp);
			}
		}else
		{}
		
		int[] multiplicationHybridResult = multiplication.multiplication(lowerOrderSum,highOrderSum);
		
		int[] sumPrevBehi = new int[(multiplicationLowerResult.length/2+multiplicationHighResult.length/2)+1];
		
		if(multiplicationLowerResult.length >= multiplicationHighResult.length)
		{
			for(int i = 0 ; i <= multiplicationLowerResult.length-1; i++)
			{
				
				sumPrevBehi[i] = multiplicationLowerResult[i] + multiplicationHighResult[i];
				multiplication.carrySet(sumPrevBehi, i, sumOfTwoNumTemp);
			}
		}else
		{}
		
		int[] subResult = new int[multiplicationHybridResult.length];
		
		for(int i = 0 ; i <= multiplicationHybridResult.length-1 ; i ++)
		{
			subResult[i] = multiplicationHybridResult[i] - sumPrevBehi[i];
			borrow(subResult , i , sumOfTwoNumTemp);
		}
		
		int[] highPart = new int[highOrderSum.length + firstInputNumber.length + secondInputNumber.length];
		for(int i = highOrderSum.length-1; i >=0 ; i--)
		{
			highPart[i+firstInputNumber.length + secondInputNumber.length] = highOrderSum[i];
		}
		
		int[] midPart = new int[highOrderSum.length + firstInputNumber.length + secondInputNumber.length];
		for(int i = subResult.length-1; i >=0 ; i--)
		{
			midPart[i+firstHighOrder + secondHighOrder] = subResult[i];
		}
		
		for(int i = 0; i <= highPart.length; i++)
		{
			highPart[i] = highPart[i]+midPart[i]+lowerOrderSum[i];
			multiplication.carrySet(highPart, i, sumOfTwoNumTemp);
		}
		
		return highPart;
		
	}

	private void borrow(int[] subResult, int i, SumOfTwoNum sumOfTwoNumTemp) {
		
	}
}
