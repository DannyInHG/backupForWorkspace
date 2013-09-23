package testapokercard;

import java.util.Iterator;

import cards.APokerCard;
import cards.Card;
/**
 * 
 * 测试一下生成一副扑克牌和对扑克牌的操作的类能不能用，经测试能用。
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-12
 */
public class TestAPokerCard {
	public static void main(String[] args)
	{
		APokerCard aPokerCard = new APokerCard();
		aPokerCard.generateAPokerCard();
		System.out.println("the number of the aPokerCard:" + aPokerCard.checkCardAmount());
/*		Card tempCard = aPokerCard.getRandomCard();
		System.out.println("getRandomCard \n number:" + tempCard.getNumber() +
				"\n the pattern is :" + tempCard.getPattern());
		System.out.println("the number of the aPokerCard:" + aPokerCard.checkCardAmount());*/
		
		Iterator<Card> iteratorAPokerCard = aPokerCard.getaPokerCard().iterator();
		while(iteratorAPokerCard.hasNext())
		{
			Card tempCard = iteratorAPokerCard.next();
			System.out.println("  " + tempCard.getNumber() + "    " + tempCard.getPattern());
			
		}
	}
}
