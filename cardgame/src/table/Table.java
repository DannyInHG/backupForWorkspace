package table;

import cards.APokerCard;
import cards.Card;
import player.Player;
import rule.Rule;
/**
 * 
 *
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-10
 */
public class Table {
	
	Player playerA = new Player();
	Player playerB = new Player();
	Player playerC = new Player();

	/**
	 * 发牌，从一副扑克牌中每人轮流抓取5张牌
	 */
	private void deal(APokerCard aPokerCard)
	{
		for(int i = 0; i <= 4; i++)
		{
			playerA.drawCard(aPokerCard);
			playerB.drawCard(aPokerCard);
			playerC.drawCard(aPokerCard);
			
		}
	}
	
	/**
	 * 从开始挑一个玩家出牌，按照游戏规则一直到有一个玩家出完牌这个过程
	 */
	private void run()
	{
		/**
		 * tempFlag用来控制while循环，并标志出谁先打完牌，标志后面方便统计。
		 * 如果：
		 * 1）tempFlag = 1，则玩家A先打完手中牌；
		 * 2）tempFlag = 2，则玩家B先打完牌；
		 * 3）tempFlag = 3，则玩家C先打完牌。
		 * 
		 */
		int tempFlag = 0;
		while(tempFlag == 0)
		{
			while(tempFlag == 0)
			{
				Card returnCardForA = playerA.chooceShowOrDropCard();
				if(returnCardForA.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				Card returnCardForB = playerB.chooceShowOrDropCard();
				if(returnCardForB.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				Card returnCardForC = playerC.chooceShowOrDropCard();
				if(returnCardForC.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				/**
				 * 这里需要添加一个标志位来标志有些玩家不能再玩了，而有些玩家要一直玩下去，只要他的flag不为2即可一直玩。
				 * 但是这里怎么添加让它们成为一个循环还需要考虑。
				 * 2013/9/12
				 */
				
			}
			
			
			if(playerA.checkPlayerCardIsEmpty())
				tempFlag = 1;
			if(playerB.checkPlayerCardIsEmpty())
				tempFlag = 2;
			if(playerC.checkPlayerCardIsEmpty())
				tempFlag = 3;
		}
		
	}
	
	/**
	 * 已经不能再出牌，计算每个人手中牌的点数和，判断出谁是胜者
	 */
	private void over()
	{
		
	}
	
	public static void main(String[] args)
	{
		Table gameTable = new Table();
		APokerCard aPokerCard = new APokerCard();
		gameTable.deal(aPokerCard);
		gameTable.run();
		gameTable.over();		
	}
}
