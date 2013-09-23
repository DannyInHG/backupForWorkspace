package fivecardstud.playerbehaviour;

import fivecardstud.pokercard.PokerCard;
/**
 * 定义了玩家的三种行为接口,分别是抓牌，出牌，弃牌
 * @author Daniel.Lee
 *
 */
public interface PlayerBehaviour {
	public PokerCard drawCard();
	public PokerCard showCard();
	public PokerCard dropCard();
	public String tellPlayManagerShowOrDrop(); //等待用户回复，并且将结果告诉PlayRuleManager
}
