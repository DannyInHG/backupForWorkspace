package fivecardstud.player;

import java.util.List;

import fivecardstud.pokercard.PokerCard;
/**
 * ��ң�
 * ���ԣ�����������������ʲô��
 * ��Ϊ��ץ�ƣ��������˿�������һ�Σ����ؾ���һ���ƣ������ƣ���һ���ƣ�����Ϸ�����������һ���ƣ����ͨ���ͼ���һ�ţ������ͨ���ͱ���ԭ�����������ѳ������ˣ����������ƣ���Ҫ���ƣ��Լ�ѡ����һ���ƣ�
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
