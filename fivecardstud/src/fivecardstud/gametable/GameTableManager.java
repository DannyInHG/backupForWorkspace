package fivecardstud.gametable;

import fivecardstud.player.DefaultPlayerStatusManager;
import fivecardstud.player.DefaultPlayersBehaviour;
import fivecardstud.player.Player;
import fivecardstud.playrule.PlayRuleManager;
import fivecardstud.playrule.XsPokerCard;
import fivecardstud.pokercard.GeneratePokerCards;
import fivecardstud.pokercard.PokerCard;

/**
 * ���ܾ�����Ϸ������������˹����ƣ�����������֪ͨȫ���磬����ÿλ���֪������˭������,֪ͨ��ҳ���Ҳ���������������Ϊ���������
 * @author Daniel.Lee
 *
 */
public class GameTableManager {
	
	public static void main(String[] args)
	{
		Player playerA = new Player();
		Player playerB = new Player();
		Player playerC = new Player();
		
		//����������һ���ƣ�һ��52��
		GeneratePokerCards APokerCards = new GeneratePokerCards();
		
		DefaultPlayerStatusManager defaultPlayStatusManager = new DefaultPlayerStatusManager() ;
		
		
		
		Player firstShowCardMan = defaultPlayStatusManager.pickUpFirstShowCardMan();
		//playerA = firstShowCardMan;
		DefaultPlayersBehaviour firstShowCardManBehaviour = new DefaultPlayersBehaviour();
		
		//��ץ5����
		
		//�ڿ�ʼ���ƣ���һ�������ҳ���һ�����Ƶ���
		defaultPlayStatusManager.infoPlayerShowCard(firstShowCardMan);
		
		//��һ�����Ƶ��˳����ǲ���
		String reply = new String();
		reply = firstShowCardManBehaviour.tellPlayManagerShowOrDrop();
		//������ҵ�ѡ����������ͬ�ķ���
		if(defaultPlayStatusManager.receviePlayerChoiceForInfo(reply))
		{
			//�������ҳ�ʲô�ƣ�Ȼ����Ʒ���
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
