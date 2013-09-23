package test;
import java.util.Stack;


public class TestStack {
	public static void main(String[]  args)
	{
		@SuppressWarnings("rawtypes")
		Stack stack = new Stack();
		
		stack.push(1);
		stack.push(2);
		stack.push(4);
		
		System.out.println(stack.size());
		System.out.println(stack.pop());
		System.out.println(stack.size());
		while(!stack.empty())
			System.out.println(stack.pop());
		
	}
}
