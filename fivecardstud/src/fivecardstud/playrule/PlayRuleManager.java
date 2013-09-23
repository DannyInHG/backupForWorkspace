package fivecardstud.playrule;

import fivecardstud.player.Player;
import fivecardstud.pokercard.PokerCard;

/**
 * 功能：管理玩家与桌面的中间件
 * 刚开始时第一次出牌从三玩家中挑选第一个出牌的人
 * 出牌了之后管理每个人出的牌是不是能够出，并且根据现在的牌和接下来出的牌改变（给桌面发信息，让三个人都知道现在出到什么牌了，并且通知下一个人出牌）
 * @author Daniel.Lee
 *
 */
public interface PlayRuleManager {
	//public PokerCard recevPlayerShowCardInfo;
	
	public void infoPlayerShowCard(Player player);
	public XsPokerCard receivePlayerShowCard();  //收了玩家的牌之后马上检查玩家isCardEmpty是否为真，如为真则游戏结束
	public Player receivePlayerDropCard();
	public void sendCard2Player(PokerCard pokercard);
	public PokerCard selectCard2Send();   //在一副母牌中选牌来发
	public Player pickUpFirstShowCardMan();
	public Boolean receviePlayerChoiceForInfo(String reply); //reply = Yes or No
}
