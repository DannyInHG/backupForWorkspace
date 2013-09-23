package fivecardstud.playrule;

import fivecardstud.player.Player;
import fivecardstud.pokercard.PokerCard;

/**
 * ���ܣ����������������м��
 * �տ�ʼʱ��һ�γ��ƴ����������ѡ��һ�����Ƶ���
 * ������֮�����ÿ���˳������ǲ����ܹ��������Ҹ������ڵ��ƺͽ����������Ƹı䣨�����淢��Ϣ���������˶�֪�����ڳ���ʲô���ˣ�����֪ͨ��һ���˳��ƣ�
 * @author Daniel.Lee
 *
 */
public interface PlayRuleManager {
	//public PokerCard recevPlayerShowCardInfo;
	
	public void infoPlayerShowCard(Player player);
	public XsPokerCard receivePlayerShowCard();  //������ҵ���֮�����ϼ�����isCardEmpty�Ƿ�Ϊ�棬��Ϊ������Ϸ����
	public Player receivePlayerDropCard();
	public void sendCard2Player(PokerCard pokercard);
	public PokerCard selectCard2Send();   //��һ��ĸ����ѡ������
	public Player pickUpFirstShowCardMan();
	public Boolean receviePlayerChoiceForInfo(String reply); //reply = Yes or No
}
