package fivecardstud.player;

import java.util.List;

import fivecardstud.pokercard.PokerCard;
/**
 * 玩家：
 * 属性：手上牌数，具体有什么牌
 * 行为：抓牌（就是向扑克牌请求一次，返回就是一张牌），出牌（出一张牌，向游戏规则管理器出一张牌，如果通过就减少一张，如果不通过就保持原样，并且提醒出错牌了），放弃出牌（需要弃牌，自己选择弃一张牌）
 * @author Daniel.Lee
 * @version 1.0
 * 
 */

public class Player {
	private int cardNum;
	private List<PokerCard> cardInHand;
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public List<PokerCard> getCardInHand() {
		return cardInHand;
	}
	public void setCardInHand(List<PokerCard> cardInHand) {
		this.cardInHand = cardInHand;
	}
	
	public Boolean isCardEmpty()
	{
		if(cardNum == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	

	
	
}
