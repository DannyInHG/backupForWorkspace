package cards;

import java.util.ArrayList;


/**
 * 
 * ����һ���������˿��ƣ���main�����ɺ����⸱�ơ�
 * @author lidatong Email��DannyInHG@gmail.com
 * @version 1.0, ����ʱ�䣺2013-9-12
 */
public class APokerCard {
	private ArrayList<Card> aPokerCard = new ArrayList<Card>();

	public void generateAPokerCard()
	{
		
		for(int i = 1; i <= 4; i++)
			for(int j=1; j<=13; j++)
			{
				/**
				 * �˴��ر�ע�⣺Card tempCard = new Card();�������������ӵ�aPokerCard.add(tempCard);
				 * ÿ����Ӷ��ǲ�ͬ�Ķ��� �������ѭ��֮ǰ�Ļ�������ӵĶ���ͬһ��Ԫ�أ�
				 * ֻ�ǰ�Ԫ�����Ը����ˣ����Ի�������һ��ֵ��ı�ǰ��Ԫ�ص�ֵ
				 * ��ΪaPokerCard.add���ݵĶ�������ã����Ƕ���
				 */
				Card tempCard = new Card();
				switch(i)
				{
					case 1:
						tempCard.setPattern("Spade");
						tempCard.setNumber(j);
						break;
					case 2:
						tempCard.setPattern("heart");
						tempCard.setNumber(j);
						break;
					case 3:
						tempCard.setPattern("club");
						tempCard.setNumber(j);
						break;
					case 4:
						tempCard.setPattern("diamond");
						tempCard.setNumber(j);
						break;
				}
				aPokerCard.add(tempCard);
			}
	}
	
		
	public ArrayList<Card> getaPokerCard() {
		return aPokerCard;
	}



	public void setaPokerCard(ArrayList<Card> aPokerCard) {
		this.aPokerCard = aPokerCard;
	}



	public int checkCardAmount()
	{
		return aPokerCard.size();
	}

	/**
	 * ���������������ʱΪ�ա�������Ҫ���ÿ�������е�������ȡ�ƣ���������Ӯ����Щ��
	 * ���ȡһ�ŷ��أ�������������ɾȥ������
	 * @return
	 */
	public Card getRandomCard()
	{
		Card randomCard = null;
		if(aPokerCard.size() >= 1)
			randomCard = aPokerCard.get(0);
		else 
			System.out.println("Error!");
		aPokerCard.remove(0);
		return randomCard;
	}
	
	
}
