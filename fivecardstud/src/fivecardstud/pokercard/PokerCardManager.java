package fivecardstud.pokercard;

import java.util.List;

/**
 * 管理剩余的扑克牌
 * 属性：牌数，具体有什么牌(或者考虑还有什么牌的组合没有使用过，需要有一个记录的功能：记录什么牌已经使用过了，不能重复)
 * 方法：从剩余的牌中挑选牌给玩家（玩家每一次请求就给挑选一张给玩家）
 * @author Daniel.Lee
 *
 */


public class PokerCardManager {
	private int pokerCardNum;
	private List<PokerCard> pokerCardRemaider;
	
	public int getPokerCardNum() {
		return pokerCardNum;
	}
	public void setPokerCardNum(int pokerCardNum) {
		this.pokerCardNum = pokerCardNum;
	}
	public List<PokerCard> getPokerCardRemaider() {
		return pokerCardRemaider;
	}
	public void setPokerCardRemaider(List<PokerCard> pokerCardRemaider) {
		this.pokerCardRemaider = pokerCardRemaider;
	}
	
	/**
	 * 从剩余的扑克牌（列表中）中随机挑选出一张返回，随机怎么挑选还未定
	 * @return
	 */
	public PokerCard selectPokerCard()
	{
		PokerCard pokerCard = new PokerCard();
		
		return pokerCard;
	}
}
