package table;

import cards.APokerCard;
import cards.Card;
import player.Player;
import rule.Rule;
/**
 * 
 *
 * @author lidatong Email��DannyInHG@gmail.com
 * @version 1.0, ����ʱ�䣺2013-9-10
 */
public class Table {
	
	Player playerA = new Player();
	Player playerB = new Player();
	Player playerC = new Player();

	/**
	 * ���ƣ���һ���˿�����ÿ������ץȡ5����
	 */
	private void deal(APokerCard aPokerCard)
	{
		for(int i = 0; i <= 4; i++)
		{
			playerA.drawCard(aPokerCard);
			playerB.drawCard(aPokerCard);
			playerC.drawCard(aPokerCard);
			
		}
	}
	
	/**
	 * �ӿ�ʼ��һ����ҳ��ƣ�������Ϸ����һֱ����һ����ҳ������������
	 */
	private void run()
	{
		/**
		 * tempFlag��������whileѭ��������־��˭�ȴ����ƣ���־���淽��ͳ�ơ�
		 * �����
		 * 1��tempFlag = 1�������A�ȴ��������ƣ�
		 * 2��tempFlag = 2�������B�ȴ����ƣ�
		 * 3��tempFlag = 3�������C�ȴ����ơ�
		 * 
		 */
		int tempFlag = 0;
		while(tempFlag == 0)
		{
			while(tempFlag == 0)
			{
				Card returnCardForA = playerA.chooceShowOrDropCard();
				if(returnCardForA.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				Card returnCardForB = playerB.chooceShowOrDropCard();
				if(returnCardForB.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				Card returnCardForC = playerC.chooceShowOrDropCard();
				if(returnCardForC.getFlag() == 1)
					Rule.doSomeThing();
				else
					System.out.println("Draw your new card and stay quietly to look others showing");
				
				/**
				 * ������Ҫ���һ����־λ����־��Щ��Ҳ��������ˣ�����Щ���Ҫһֱ����ȥ��ֻҪ����flag��Ϊ2����һֱ�档
				 * ����������ô��������ǳ�Ϊһ��ѭ������Ҫ���ǡ�
				 * 2013/9/12
				 */
				
			}
			
			
			if(playerA.checkPlayerCardIsEmpty())
				tempFlag = 1;
			if(playerB.checkPlayerCardIsEmpty())
				tempFlag = 2;
			if(playerC.checkPlayerCardIsEmpty())
				tempFlag = 3;
		}
		
	}
	
	/**
	 * �Ѿ������ٳ��ƣ�����ÿ���������Ƶĵ����ͣ��жϳ�˭��ʤ��
	 */
	private void over()
	{
		
	}
	
	public static void main(String[] args)
	{
		Table gameTable = new Table();
		APokerCard aPokerCard = new APokerCard();
		gameTable.deal(aPokerCard);
		gameTable.run();
		gameTable.over();		
	}
}
