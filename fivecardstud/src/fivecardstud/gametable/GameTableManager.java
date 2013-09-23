package fivecardstud.gametable;

import fivecardstud.player.DefaultPlayerStatusManager;
import fivecardstud.player.DefaultPlayersBehaviour;
import fivecardstud.player.Player;
import fivecardstud.playrule.PlayRuleManager;
import fivecardstud.playrule.XsPokerCard;
import fivecardstud.pokercard.GeneratePokerCards;
import fivecardstud.pokercard.PokerCard;

/**
 * 接受经过游戏规则管理器过滤过的牌，并将这张牌通知全世界，并让每位玩家知道这是谁出的牌,通知玩家出牌也在这里，所以这里作为主函数入口
 * @author Daniel.Lee
 *
 */
public class GameTableManager {
	
	public static void main(String[] args)
	{
		Player playerA = new Player();
		Player playerB = new Player();
		Player playerC = new Player();
		
		//生成完整的一副牌，一共52张
		GeneratePokerCards APokerCards = new GeneratePokerCards();
		
		DefaultPlayerStatusManager defaultPlayStatusManager = new DefaultPlayerStatusManager() ;
		
		
		
		Player firstShowCardMan = defaultPlayStatusManager.pickUpFirstShowCardMan();
		//playerA = firstShowCardMan;
		DefaultPlayersBehaviour firstShowCardManBehaviour = new DefaultPlayersBehaviour();
		
		//先抓5张牌
		
		//在开始打牌，第一步，先找出第一个出牌的人
		defaultPlayStatusManager.infoPlayerShowCard(firstShowCardMan);
		
		//第一个出牌的人出还是不出
		String reply = new String();
		reply = firstShowCardManBehaviour.tellPlayManagerShowOrDrop();
		//根据玩家的选择来启动不同的方法
		if(defaultPlayStatusManager.receviePlayerChoiceForInfo(reply))
		{
			//问清楚玩家出什么牌，然后把牌返回
			PokerCard showCard = firstShowCardManBehaviour.showCard();
			defaultPlayStatusManager.receivePlayerShowCard().setPlayer(firstShowCardMan);
			defaultPlayStatusManager.receivePlayerShowCard().setPokerCard(showCard);
		}
		else 
		{
			PokerCard dropCard = firstShowCardManBehaviour.dropCard();
			
			defaultPlayStatusManager.receivePlayerDropCard();
		}
		
		
	}
}
