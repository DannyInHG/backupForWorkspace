package fivecardstud.pokercard;
/**
 * 定义每张扑克牌的属性
 * @author Daniel.Lee
 *
 */
public class PokerCard {
	private enum Pattern{Spade,heart,club,diamond};
	private Pattern pattern;
	private int point;
	public Pattern getPattern() {
		return pattern;
	}
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
