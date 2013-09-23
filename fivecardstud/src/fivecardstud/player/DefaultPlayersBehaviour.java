package fivecardstud.player;
import fivecardstud.playerbehaviour.PlayerBehaviour;
import fivecardstud.pokercard.PokerCard;
/**
 * 实现玩家的三种行为
 * @author Daniel.Lee
 *
 */
public class DefaultPlayersBehaviour implements PlayerBehaviour {
	
	@Override
	public PokerCard drawCard() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 出牌，为用户调用，根据用户输入（showCard ）来给值
	 * @see fivecardstud.playerbehaviour.PlayerBehaviour#showCard(fivecardstud.pokercard.PokerCard)
	 */
	@Override
	public PokerCard showCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PokerCard dropCard() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String tellPlayManagerShowOrDrop() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
