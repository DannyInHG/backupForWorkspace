package fivecardstud.playerbehaviour;

import fivecardstud.pokercard.PokerCard;
/**
 * ��������ҵ�������Ϊ�ӿ�,�ֱ���ץ�ƣ����ƣ�����
 * @author Daniel.Lee
 *
 */
public interface PlayerBehaviour {
	public PokerCard drawCard();
	public PokerCard showCard();
	public PokerCard dropCard();
	public String tellPlayManagerShowOrDrop(); //�ȴ��û��ظ������ҽ��������PlayRuleManager
}
