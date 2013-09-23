package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cards.APokerCard;
import cards.Card;

/**
 * 
 * 定义玩家的属性和方法
 * @author lidatong Email：DannyInHG@gmail.com
 * @version 1.0, 创建时间：2013-9-10
 */
public class Player {
	private List<Card> cardsInHand = new ArrayList<Card>();

	private int dropCardNumSum = 0;
	
	public int getDropCardNumSum() {
		return dropCardNumSum;
	}

	public void setDropCardNumSum(int dropCardNumSum) {
		this.dropCardNumSum = dropCardNumSum;
	}

	public void setCardsInHand(List<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	
	/**
	 * 向table要求一张牌，这张牌存储在cardsInHand中。这个地方还要写。
	 */
	public void drawCard(APokerCard aPokerCard)
	{
		cardsInHand.add(aPokerCard.getRandomCard());
	}
	
	public void checkAllCardsInHand()
	{
		Iterator<Card> iteratorCardsInHand = cardsInHand.iterator();
		System.out.println("——————遍历全部手中的全部牌——————");
		int i = 1;
		while(iteratorCardsInHand.hasNext())
		{
			Card tempCard = iteratorCardsInHand.next();
			System.out.println(" 序号            样式                   点数                       ");
			System.out.println(i + "    " + tempCard.getPattern() + "    " + tempCard.getNumber());
			i++;
		}
	}
	
	public Card showCard()
	{
		System.out.println("pick out a card to show from the cards in your hand:(Enter the sequence)");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String bufferedReadLine = bufferedReader.readLine();
			int pickSequenceNum = Integer.parseInt(bufferedReadLine);
			if(pickSequenceNum>=1 && pickSequenceNum<=cardsInHand.size())
			{
				System.out.println("Show the card:");
				System.out.println("the showcard pattern is:" + cardsInHand.get(pickSequenceNum-1).getPattern());
				System.out.println("the showcard number is:" + cardsInHand.get(pickSequenceNum-1).getNumber());
				
				Card removeElement = cardsInHand.remove(pickSequenceNum-1);
				System.out.println("you has remove the card:");			
				System.out.println("the removecard pattern is:" + removeElement.getPattern());
				System.out.println("the removecard number is:" + removeElement.getNumber());
				
				return removeElement;
			}
			else
				System.out.println("Wrong input!I'm sorry!");
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Card dropCard()
	{
		System.out.println("pick out a card to drop from the cards in your hand:(Enter the sequence)");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String bufferedReadLine = bufferedReader.readLine();
			int pickSequenceNum = Integer.parseInt(bufferedReadLine);
			if(pickSequenceNum>=1 && pickSequenceNum<=cardsInHand.size())
			{
				System.out.println("Drop the card:");
				System.out.println("the dropcard pattern is:" + cardsInHand.get(pickSequenceNum-1).getPattern());
				System.out.println("the dropcard number is:" + cardsInHand.get(pickSequenceNum-1).getNumber());
				
				Card removeElement = cardsInHand.remove(pickSequenceNum-1);
				System.out.println("you has remove the card:");			
				System.out.println("the removecard pattern is:" + removeElement.getPattern());
				System.out.println("the removecard number is:" + removeElement.getNumber());

				return removeElement;
			}
			else
				System.out.println("Wrong input!I'm sorry!");
			return null;	
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Card chooceShowOrDropCard()
	{
		System.out.println("choose to showcard or dropcard:");
		System.out.println("Enter 1 : showcard");
		System.out.println("Enter 2 : dropcard");
		
		Card returnCard = new Card();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String bufferedReadLine = bufferedReader.readLine();
			int chooseNumber = Integer.parseInt(bufferedReadLine);
			if(chooseNumber >=1 && chooseNumber <=2)
			{
				/**
				 * 对card增加个标志位，这个标志位可以方便识别是showcard还是dropcard，方便后面显示到table上。
				 * 在这里：
				 * 1)如果flag=1，则为showcard；
				 * 2）如果flag=2，则为dropcard。
				 */
				returnCard.setFlag(chooseNumber);
				switch (chooseNumber) {
				case 1:
					checkAllCardsInHand();
					returnCard = showCard();
					checkAllCardsInHand();
					break;
							
				case 2:
					checkAllCardsInHand();
					dropCardNumSum += dropCard().getNumber();
					returnCard = dropCard();
					checkAllCardsInHand();
					break;
				}
				return returnCard;
			}
			else
				System.out.println("Wrong Enter number,I'm sorry for system crush.Damn it!");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 检查玩家卡牌是否已经为空
	 * @return 如果玩家已经没有牌了则返回ture，否则返回false
	 */
	public boolean checkPlayerCardIsEmpty()
	{
		return cardsInHand.isEmpty();
	}
	
}
