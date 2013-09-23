package rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cards.Card;

/**
 * 
 * 规则，作用：判断玩家出的牌能不能出：
 * 1）如果不能出，则返回让玩家重新选择是dropCard还是showCard；
 * 2）如果能出，则出，接下来让下一个玩家出牌；
 * 3）能出的话并且要检查玩家出了牌之后是不是手里已经没有牌了，如已经没有牌则代表游戏
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-12
 */
public class Rule {
	public static void doSomeThing()
	{}
	
	public static boolean examineCardCanShow(Card preCard,Card showCard)
	{
		if(preCard.getPattern().equals(showCard.getPattern()))
		{
			System.out.println("The Cardpattern is the same");
			return true;
		}
		else if(preCard.getNumber() == showCard.getNumber())
		{
			System.out.println("The Cardnumber is the same,you can change the pattern as you wish!");
			System.out.println("You can choose the sequence of list to change the pattern:");
			System.out.println("1 : Spade ");
			System.out.println("2 : heart ");
			System.out.println("3 : club ");
			System.out.println("4 : diamond ");
			System.out.println("Enter your choice:");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				String bufferedReadLine = bufferedReader.readLine();
				int chooseNumber = Integer.parseInt(bufferedReadLine);
				switch(chooseNumber)
				{
				case 1:
					System.out.println("Now change the pattern to Spade");
					
					break;
				case 2:
					System.out.println("Now change the pattern to heart");
					
					break;
				case 3:
					System.out.println("Now change the pattern to club");
					
					break;
				case 4:
					System.out.println("Now change the pattern to diamond");
					
					break;
				default:
					System.out.println("Enter the wrong number!");
					
					break;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else
			return false;
	
	}
}
