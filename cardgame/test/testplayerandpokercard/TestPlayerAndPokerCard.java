package testplayerandpokercard;

import player.Player;
import cards.APokerCard;

/**
 * 
 * ������Һ��˿��Ƶ���ط����ܲ����á�
 * @author lidatong Email��DannyInHG@gmail.com
 * @version 1.0, ����ʱ�䣺2013-9-12
 */
public class TestPlayerAndPokerCard {
	public static void main(String[] args)
	{
		APokerCard aPokerCard = new APokerCard();
		aPokerCard.generateAPokerCard();
		System.out.println("the number of the aPokerCard:" + aPokerCard.checkCardAmount());

		
		Player testPlayer = new Player();
		testPlayer.setCardsInHand(aPokerCard.getaPokerCard());
		testPlayer.chooceShowOrDropCard();
		System.out.println(testPlayer.getDropCardNumSum());
	}
}
