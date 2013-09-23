package cards;

import java.util.ArrayList;


/**
 * 
 * 生成一副完整的扑克牌，在main中生成后都用这副牌。
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-12
 */
public class APokerCard {
	private ArrayList<Card> aPokerCard = new ArrayList<Card>();

	public void generateAPokerCard()
	{
		
		for(int i = 1; i <= 4; i++)
			for(int j=1; j<=13; j++)
			{
				/**
				 * 此处特别注意：Card tempCard = new Card();放在这里表明添加的aPokerCard.add(tempCard);
				 * 每次添加都是不同的对象， 如果放在循环之前的话，则添加的都是同一个元素，
				 * 只是把元素属性给改了，所以会造成添加一个值会改变前面元素的值
				 * 因为aPokerCard.add传递的对象的引用，而非对象
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
	 * 这里是随机规则，暂时为空。后面需要结合每个人手中的牌来抽取牌，让所有人赢得慢些。
	 * 随机取一张返回，并在整幅牌中删去那张牌
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
