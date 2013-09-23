package testplayerandpokercard;

import player.Player;
import cards.APokerCard;

/**
 * 
 * 测试玩家和扑克牌的相关方法能不能用。
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-12
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
