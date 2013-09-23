package fivecardstud.playrule;

import fivecardstud.player.Player;
import fivecardstud.pokercard.PokerCard;
/**
 * X's PokerCard，表示X的扑克牌
 * @author Daniel.Lee
 *
 */
public class XsPokerCard {
	private PokerCard pokerCard;
	private Player player;
	public PokerCard getPokerCard() {
		return pokerCard;
	}
	public void setPokerCard(PokerCard pokerCard) {
		this.pokerCard = pokerCard;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
