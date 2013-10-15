
import java.io.IOException;
import java.util.List;

public class BplusTree implements B {
	
	/** 根节点 */
	protected Node root;
	protected String rootIndex;
	
	/** 阶数，M值 */
	protected int order;
	
	/** 叶子节点的链表头*/
	protected Node head;
	protected String headIndex;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

//	public int getOrder() {
//		return order;
//	}
//
//	public void setOrder(int order) {
//		this.order = order;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(Comparable key) throws Exception {
		return root.get(key);
	}
//   1111111111111111111111111111111111111111111111111111111111111
	public String getRootIndex() throws Exception {
		rootIndex = getFileImfo("rootIndex");
		return rootIndex;
	}

	public void setRootIndex(String rootIndex) throws Exception {
		List<String> imfoCatch = ReadWriteTxt.readLines("tree.txt");
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(i==0)
			{
				sb.append("rootIndex="+rootIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
			ReadWriteTxt.toTxtReplace(sb.toString(),"tree.txt");
		 	this.rootIndex = rootIndex;
	}
	
	
	public String getHeadIndex() throws Exception {
		headIndex = getFileImfo("headIndex");
		return headIndex;
	}

	public void setHeadIndex(String headIndex) throws Exception {
		List<String> imfoCatch = ReadWriteTxt.readLines("tree.txt");
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(i==1)
			{
				sb.append("headIndex="+headIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
			ReadWriteTxt.toTxtReplace(sb.toString(),"tree.txt");
		 	this.headIndex = headIndex;
	}
	
	public int getOrder() throws Exception {
		List<String> imfoCatch = ReadWriteTxt.readLines("tree.txt");
		String tmp = null;
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(i==2)tmp = imfoCatch.get(i).substring(6);
		}
		order =Integer.parseInt(tmp);
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
//   2222222222222222222222222222222222222222222222222222222222222
	@SuppressWarnings("unchecked")
	@Override
	public void remove(Comparable key) throws Exception {
		root.remove(key, this);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertOrUpdate(Comparable key, Object obj) throws Exception {
		root.insertOrUpdate(key, obj, this);

	}
	
	public BplusTree(int order) throws Exception{
		if (order < 3) {
			System.out.print("order must be greater than 2");
			System.exit(0);
		}		
		this.order = order;
		root = new Node(true, true);
		rootIndex = root.fileIndex;
		headIndex = rootIndex;
		head = root;
		StringBuilder sb=new StringBuilder();
		sb.append("rootIndex="+rootIndex+"\n");
		sb.append("headIndex="+headIndex+"\n");
		sb.append("order="+order+"\n");
		try {
			ReadWriteTxt.toTxtReplace(sb.toString(), "./tree"+".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试
	public static void main(String[] args) throws Exception {
		BplusTree tree = new BplusTree(3);
		long current = System.currentTimeMillis();
		for (int i = 1; i <10; i=i+1) {
			System.out.println("插入节点"+i);
			tree.insertOrUpdate(i, "page.jpg");
		}
//			for (int i = 8; i >1; i=i-2) {
//				System.out.println("插入节点"+i);
//				tree.insertOrUpdate(i, i);
//			}
		String x=getFileImfo("rootIndex");
		String y=getFileImfo("headIndex");
		System.out.println("根节点："+x);
		System.out.println("头节点： "+y);
		tree.remove(7);
		tree.insertOrUpdate(7, "d");
//		tree.remove(7);
		long duration = System.currentTimeMillis() - current;
		System.out.println("time elpsed for duration: " + duration);
//		ShowPic sp=new ShowPic();
//		sp.shwpic("page.jpg");

	}

	public static String getFileImfo(String imfo) throws Exception
	{
		List<String> imfoCatch = ReadWriteTxt.readLines("tree.txt");
		int keysize=imfo.length();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))return imfoCatch.get(i).substring(keysize+1);
		}		
		return null;
	}
	
	
}

	
