package fivecardstud.pokercard;

import java.util.List;

/**
 * ����ʣ����˿���
 * ���ԣ�������������ʲô��(���߿��ǻ���ʲô�Ƶ����û��ʹ�ù�����Ҫ��һ����¼�Ĺ��ܣ���¼ʲô���Ѿ�ʹ�ù��ˣ������ظ�)
 * ��������ʣ���������ѡ�Ƹ���ң����ÿһ������͸���ѡһ�Ÿ���ң�
 * @author Daniel.Lee
 *
 */


public class PokerCardManager {
	private int pokerCardNum;
	private List<PokerCard> pokerCardRemaider;
	
	public int getPokerCardNum() {
		return pokerCardNum;
	}
	public void setPokerCardNum(int pokerCardNum) {
		this.pokerCardNum = pokerCardNum;
	}
	public List<PokerCard> getPokerCardRemaider() {
		return pokerCardRemaider;
	}
	public void setPokerCardRemaider(List<PokerCard> pokerCardRemaider) {
		this.pokerCardRemaider = pokerCardRemaider;
	}
	
	/**
	 * ��ʣ����˿��ƣ��б��У��������ѡ��һ�ŷ��أ������ô��ѡ��δ��
	 * @return
	 */
	public PokerCard selectPokerCard()
	{
		PokerCard pokerCard = new PokerCard();
		
		return pokerCard;
	}
}
