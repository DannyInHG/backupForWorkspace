import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Node {
	public static int ooxx;
	/** 文件索引 */
	protected String fileIndex;
	
	/** 是否为叶子节点 */
	protected boolean isLeaf;
	
	/** 是否为根节点*/
	protected boolean isRoot;

	/** 父节点 */
	protected Node parent;
	protected String parentIndex;
	
	/** 子节点 */
	protected List<Node> children;
	protected List<String> childsIndex;
	
	/** 叶节点的前节点*/
	protected Node previous;
	protected String preIndex;
	
	/** 叶节点的后节点*/
	protected Node nextnext;
	protected String nexIndex;
	
	/** 节点的关键字 */
	@SuppressWarnings("unchecked")
	protected List<Entry<Comparable, Object>> entries;
	

	
	@SuppressWarnings({ "unchecked", "static-access" })
	public Node(boolean isLeaf) {
		this.ooxx=ooxx+1;
//		System.out.println(ooxx);
		this.fileIndex = ooxx+".txt";
		this.isLeaf = isLeaf;
		this.parentIndex = null;
		this.preIndex = null;
		this.nexIndex =null;
		this.childsIndex = new  ArrayList<String>();
		entries = new ArrayList<Entry<Comparable, Object>>();		
		if (!isLeaf) {
			children = new ArrayList<Node>();
		}
		StringBuilder sb=new StringBuilder();
		sb.append("isRoot="+isRoot+"\n");
		sb.append("isLeaf="+isLeaf+"\n");
		sb.append("parentIndex=null"+"\n");
		sb.append("childsIndex=null"+"\n");
		sb.append("preIndex=null"+"\n");
		sb.append("nexIndex=null"+"\n");
		sb.append("entries="+entries+"\n");
		try {
			ReadWriteTxt.toTxtReplace(sb.toString(), fileIndex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Node(boolean isLeaf, boolean isRoot) throws Exception {
		this(isLeaf);
		this.isRoot = isRoot;
		String imfo = "isRoot";
		List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		int keysize=imfo.length();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))
			{
				sb.append("isRoot="+isRoot+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
		ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
	}
	
	public Node()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public Object get(Comparable key) throws Exception {
		//如果是叶子节点
		if (isLeaf) {
			for (Entry<Comparable, Object> entry : entries) {
				if (entry.getKey().compareTo(key) == 0) {
					//返回找到的对象
					return entry.getValue();
				}
			}
			//未找到所要查询的对象
			return null;
			
		//如果不是叶子节点
		}else {
			//如果key小于等于节点最左边的key，沿第一个子节点继续搜索
			if (key.compareTo(entries.get(0).getKey()) <= 0) {
				return children.get(0).get(key);
			//如果key大于节点最右边的key，沿最后一个子节点继续搜索
			}else if (key.compareTo(entries.get(entries.size()-1).getKey()) >= 0) {
				return children.get(children.size()-1).get(key);
			//否则沿比key大的前一个子节点继续搜索
			}else {
				for (int i = 0; i < entries.size(); i++) {
					if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i+1).getKey().compareTo(key) > 0) {
						return children.get(i).get(key);
					}
				}	
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void insertOrUpdate(Comparable key, Object obj, BplusTree tree) throws Exception{
		getEntries();
		parent=getNode(parentIndex);
//		children = getNodeList(childsIndex);
		//如果是叶子节点
		if (getLeaf()){
			//不需要分裂，直接插入或更新
			if (contains(key) || entries.size() < tree.getOrder()){
				insertOrUpdate(key, obj);
				if (parent != null) {
					//更新父节点
//					parent=getNode(parentIndex);
					parent.updateInsert(tree);
				}

			//需要分裂	
			}else {
				//分裂成左右两个节点
				Node left = new Node(true);
				Node right = new Node(true);
				//设置链接
				if (!getPreIndex().equals("null")){
					getNode(getPreIndex()).setNexIndex(left.fileIndex);
					left.setPreIndex(getPreIndex());
				}
				if (!getNexIndex().equals("null")) {
					getNode(getNexIndex()).setPreIndex(right.fileIndex);
					right.setNexIndex(getNexIndex());
				}
				if (getPreIndex().equals("null")){
					tree.setHead(left);
					tree.setHeadIndex(left.fileIndex);
				}
				left.setNexIndex(right.fileIndex);
				right.setPreIndex(left.fileIndex);
				previous = null;
				nextnext = null;
				
				//左右两个节点关键字长度
				int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2; 
				int rightSize = (tree.getOrder() + 1) / 2;
				//复制原节点关键字到分裂出来的新节点
				insertOrUpdate(key, obj);
				List<Entry<Comparable, Object>> temp = new ArrayList<Entry<Comparable, Object>>();
				for (int i = 0; i < leftSize; i++){
					temp.add(entries.get(i));
					left.setEntries(temp);
				}
				temp.clear();
				for (int i = 0; i < rightSize; i++){
					temp.add(entries.get(leftSize + i));
					right.setEntries(temp);
				}
				temp.clear();
				//如果不是根节点
				if (parentIndex != null) {
					//调整父子节点关系
					int index = parent.getChildrenIndex().indexOf(this.fileIndex);
					left.setParentIndex(parentIndex);
					right.setParentIndex(parentIndex);
					List<String> temp1 = parent.getChildrenIndex();
					temp1.remove(this.fileIndex);
//					System.out.println("indexindex:::"+index);
					temp1.add(index,left.fileIndex);
					temp1.add(index+1,right.fileIndex);
//					temp1.add(index+2,"3.txt");
//					System.out.println("ttttttttttt"+temp1);
					parent.setChildrenIndex(temp1);
					temp1.clear();
					setEntries(null);
					setChildrenIndex(null);
//					tree.setHead(right);
					//父节点插入或更新关键字
					parent.updateInsert(tree);
					setParentIndex(null);

				//如果是根节点	
				}else {
					isRoot = false;
					Node parent = new Node(false, true);
//					ReadWriteTxt.deleteFile(fileIndex);
					tree.setRoot(parent);
					tree.setRootIndex(parent.fileIndex);
					left.setParentIndex(parent.fileIndex);
					right.setParentIndex(parent.fileIndex);
					List<String> temp1 = new ArrayList<String>();
					temp1.add(left.fileIndex);
					temp1.add(right.fileIndex);
					parent.setChildrenIndex(temp1);
					temp1.clear();
//					System.out.println("右节点"+parent.childsIndex);
					setEntries(null);
					setChildrenIndex(null);
					
					//更新根节点
					parent.updateInsert(tree);

				}
			}
			
		//如果不是叶子节点
		}else {
			//如果key小于等于节点最左边的key，沿第一个子节点继续搜索
			if (key.compareTo(entries.get(0).getKey()) <= 0) {
				getNode(getChildrenIndex().get(0)).insertOrUpdate(key, obj, tree);
			//如果key大于节点最右边的key，沿最后一个子节点继续搜索
			}else if (key.compareTo(entries.get(entries.size()-1).getKey()) >= 0) {
				getNode(getChildrenIndex().get(getChildrenIndex().size()-1)).insertOrUpdate(key, obj, tree);
			//否则沿比key大的前一个子节点继续搜索
			}else {
				for (int i = 0; i < entries.size(); i++) {
					if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i+1).getKey().compareTo(key) > 0) {
						getNode(getChildrenIndex().get(i)).insertOrUpdate(key, obj, tree);
						break;
					}
				}	
			}
		}
	}
	
	/** 插入节点后中间节点的更新 
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	protected void updateInsert(BplusTree tree) throws Exception{
		parent=getNode(parentIndex);
		validate(this, tree);
		
		//如果子节点数超出阶数，则需要分裂该节点	
		if (getChildrenIndex().size() > tree.getOrder()) {
			//分裂成左右两个节点
			Node left = new Node(false);
			Node right = new Node(false);
			//左右两个节点关键字长度
			int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
			int rightSize = (tree.getOrder() + 1) / 2;
			//复制子节点到分裂出来的新节点，并更新关键字
			List<Entry<Comparable, Object>> temp = new ArrayList<Entry<Comparable, Object>>();
			List<String> temp1 = new ArrayList<String>();
			for (int i = 0; i < leftSize; i++){
				temp1.add(getChildrenIndex().get(i));
				left.setChildrenIndex(temp1);
				temp.add(new SimpleEntry(getNode(getChildrenIndex().get(i)).getEntries().get(0).getKey(), null));
				left.setEntries(temp);					
//				left.getEntries().add(new SimpleEntry(children.get(i).getEntries().get(0).getKey(), null));
				getNode(getChildrenIndex().get(i)).setParentIndex(left.fileIndex);
			}
			temp1.clear();
			temp.clear();
			for (int i = 0; i < rightSize; i++){
				temp1.add(getChildrenIndex().get(leftSize + i));
				right.setChildrenIndex(temp1);
				temp.add(new SimpleEntry(getNode(getChildrenIndex().get(leftSize + i)).getEntries().get(0).getKey(), null));
				right.setEntries(temp);
//				right.getEntries().add(new SimpleEntry(children.get(leftSize + i).getEntries().get(0).getKey(), null));
				getNode(getChildrenIndex().get(leftSize + i)).setParentIndex(right.fileIndex);
//				children.get(leftSize + i).setParent(right);
			}
			temp.clear();
			temp1.clear();
			//如果不是根节点
			if (parentIndex != null) {
				//调整父子节点关系
				int index = parent.getChildrenIndex().indexOf(this.fileIndex);
				left.setParentIndex(parent.fileIndex);
				right.setParentIndex(parent.fileIndex);
				List<String> temp2 = parent.getChildrenIndex();
				temp2.remove(this.fileIndex);
//				System.out.println("indexindex:::"+index);
				temp2.add(index,left.fileIndex);
				temp2.add(index+1,right.fileIndex);
				parent.setChildrenIndex(temp2);
				temp2.clear();
				setEntries(null);
				setChildrenIndex(null);				
				//父节点更新关键字
				parent.updateInsert(tree);
				setParentIndex(null);
//				ReadWriteTxt.deleteFile(fileIndex);
			//如果是根节点	
			}else {
				isRoot = false;
				Node parent = new Node(false, true);
				tree.setRoot(parent);
				tree.setRootIndex(parent.fileIndex);
				left.setParentIndex(parent.fileIndex);
				right.setParentIndex(parent.fileIndex);
				List<String> temp2 = new ArrayList<String>();
				temp2.add(left.fileIndex);
				temp2.add(right.fileIndex);
				parent.setChildrenIndex(temp2);
				temp2.clear();
//				parent.getChildrenIndex().add(left.fileIndex);
//				parent.getChildrenIndex().add(right.fileIndex);
				setEntries(null);
				setChildrenIndex(null);
				
				//更新根节点
				parent.updateInsert(tree);
			}
		}
	}
	
	/** 调整节点关键字
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	protected void validate(Node node, BplusTree tree) throws Exception {
		
		// 如果关键字个数与子节点个数相同
		List<Entry<Comparable, Object>> temp = new ArrayList<Entry<Comparable, Object>>();
		temp = node.getEntries();
		if (temp.size() == node.getChildrenIndex().size()) {
			for (int i = 0; i < temp.size(); i++) {
				Comparable key = getNode(node.getChildrenIndex().get(i)).getEntries().get(0).getKey();
				if (temp.get(i).getKey().compareTo(key) != 0) {
					temp.remove(i);
					temp.add(i, new SimpleEntry(key, null));
//					if(!node.isRoot()){
//						validate(node.getParent(), tree);
//					}
				}
			}
			// 如果子节点数不等于关键字个数但仍大于M / 2并且小于M，并且大于2
		} else if (node.getRoot() && node.getChildrenIndex().size() >= 2 
				||node.getChildrenIndex().size() >= tree.getOrder() / 2 
				&& node.getChildrenIndex().size() <= tree.getOrder()
				&& node.getChildrenIndex().size() >= 2) {
			temp.clear();
			for (int i = 0; i < node.getChildrenIndex().size(); i++) {
				Comparable key = getNode(node.getChildrenIndex().get(i)).getEntries().get(0).getKey();
				temp.add(new SimpleEntry(key, null));
				
			}
		}
		node.setEntries(temp);
		if (!node.getRoot()) {
			validate(node.getNode(node.parentIndex), tree);
		}
	}
	
	/** 删除节点后中间节点的更新
	 * @throws Exception */
	protected void updateRemove(BplusTree tree) throws Exception {
		parent=getNode(parentIndex);
		validate(this, tree);

		// 如果子节点数小于M / 2或者小于2，则需要合并节点
		if (getChildrenIndex().size() < tree.getOrder() / 2 || getChildrenIndex().size() < 2) {
			if (getRoot()) {
				// 如果是根节点并且子节点数大于等于2，OK
				if (getChildrenIndex().size() >= 2) {
					return;
				// 否则与子节点合并
				} else {
					Node root = getNode(getChildrenIndex().get(0));
					tree.setRootIndex(root.fileIndex);
					root.setParentIndex("null");
					root.setRoot(true);
					setEntries(null);
					setChildrenIndex(null);
				}
			} else {
				//计算前后节点 
				int currIdx = getNode(getParentIndex()).getChildrenIndex().indexOf(this.fileIndex);
				int prevIdx = currIdx - 1;
				int nextIdx = currIdx + 1;
				String previous = null, next = null;
				if (prevIdx >= 0) {
					previous = getNode(getParentIndex()).getChildrenIndex().get(prevIdx);
				}
				if (nextIdx < getNode(getParentIndex()).getChildrenIndex().size()) {
					next = getNode(getParentIndex()).getChildrenIndex().get(nextIdx);
				}
				
				// 如果前节点子节点数大于M / 2并且大于2，则从其处借补
				if (!(getNode(previous).fileIndex).equals("null")
						&& getNode(previous).getChildrenIndex().size() > tree.getOrder() / 2
						&& getNode(previous).getChildrenIndex().size() > 2) {
					//前叶子节点末尾节点添加到首位
					int idx = getNode(previous).getChildrenIndex().size() - 1;
					String borrow = getNode(previous).getChildrenIndex().get(idx);
					List<String> temp = new ArrayList<String>();
					temp = getNode(previous).getChildrenIndex();
					temp.remove(idx);
					getNode(previous).setChildrenIndex(temp);
					getNode(borrow).setParentIndex(this.fileIndex);
					temp.clear();
					temp = getChildrenIndex();
					temp.add(0,borrow);
					setChildrenIndex(temp);
					temp.clear();
					validate(getNode(previous), tree);
					validate(this, tree);
					parent.updateRemove(tree);
					
				// 如果后节点子节点数大于M / 2并且大于2，则从其处借补
				} else if (!(getNode(next).fileIndex.equals("null"))	
						&& getNode(next).getChildrenIndex().size() > tree.getOrder() / 2
						&& getNode(next).getChildrenIndex().size() > 2) {
					//后叶子节点首位添加到末尾
					String borrow = getNode(next).getChildrenIndex().get(0);
					List<String> temp = new ArrayList<String>();
					temp = getNode(next).getChildrenIndex();
					temp.remove(0);
					getNode(next).setChildrenIndex(temp);
					getNode(borrow).setParentIndex(this.fileIndex);
					temp.clear();
					temp = getChildrenIndex();
					temp.add(borrow);
					setChildrenIndex(temp);
					validate(getNode(next), tree);
					validate(this, tree);
					parent.updateRemove(tree);
					
				// 否则需要合并节点
				} else {
					// 同前面节点合并
					if (!getNode(previous).fileIndex.equals("null")
							&& (getNode(previous).getChildrenIndex().size() <= tree.getOrder() / 2 || getNode(previous).getChildrenIndex().size() <= 2)) {
						
						for (int i = getNode(previous).getChildrenIndex().size() - 1; i >= 0; i--) {
							String child = getNode(previous).getChildrenIndex().get(i);
							List<String> temp = new ArrayList<String>();
							temp = getNode(previous).getChildrenIndex();
							temp.add(0,child);
							setChildrenIndex(temp);
							temp.clear();
							getNode(child).setParentIndex(this.fileIndex);
						}
						getNode(previous).setChildrenIndex(null);
						getNode(previous).setEntries(null);
						getNode(previous).setParentIndex("null");
						List<String> temp = new ArrayList<String>();
						temp = getNode(parentIndex).getChildrenIndex();
						temp.remove(previous);
						getNode(parentIndex).setChildrenIndex(temp);
						temp.clear();
						validate(this, tree);
						parent.updateRemove(tree);
						
					// 同后面节点合并
					} else if (!getNode(next).fileIndex.equals("null")	
							&& (getNode(next).getChildrenIndex().size() <= tree.getOrder() / 2 || getNode(next).getChildrenIndex().size() <= 2)) {

						for (int i = 0; i < getNode(next).getChildrenIndex().size(); i++) {
							String child = getNode(next).getChildrenIndex().get(i);
							List<String> temp = new ArrayList<String>();
							temp = getNode(previous).getChildrenIndex();
							temp.add(child);
							setChildrenIndex(temp);
							temp.clear();
							getNode(child).setParentIndex(this.fileIndex);
						}
						getNode(next).setChildrenIndex(null);
						getNode(next).setEntries(null);
						getNode(next).setParentIndex("null");
						List<String> temp = new ArrayList<String>();
						temp = getNode(parentIndex).getChildrenIndex();
						temp.remove(next);
						getNode(parentIndex).setChildrenIndex(temp);
						temp.clear();
						validate(this, tree);
						parent.updateRemove(tree);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void remove(Comparable key, BplusTree tree) throws Exception{
		getEntries();
		parent=getNode(parentIndex);
		//如果是叶子节点
		if (getLeaf()){			
			//如果不包含该关键字，则直接返回
			if (!contains(key)){
				return;
			}
			
			//如果既是叶子节点又是跟节点，直接删除
			if (getRoot()) {
				remove(key);
			}else {
				//如果关键字数大于M / 2，直接删除
				if (getEntries().size() > tree.getOrder() / 2 && getEntries().size() > 2) {
					remove(key);
				}else {
					//如果自身关键字数小于M / 2，并且前节点关键字数大于M / 2，则从其处借补
					if ((!getPreIndex().equals("null"))
							&& getNode(getPreIndex()).getEntries().size() > tree.getOrder() / 2
							&& getNode(getPreIndex()).getEntries().size() > 2
							&& !(getNode(getPreIndex()).getParentIndex().equals(parentIndex))) {
						int size = getNode(getPreIndex()).getEntries().size();
						Entry<Comparable, Object> entry = getNode(getPreIndex()).getEntries().get(size - 1);
						List<Entry<Comparable, Object>> temp = getNode(getPreIndex()).getEntries();
						temp.remove(entry);
						getNode(getPreIndex()).setEntries(temp);
						temp.clear();
						//添加到首位
						entries = getEntries();
						entries.add(0, entry);
						setEntries(entries);
						remove(key);
					//如果自身关键字数小于M / 2，并且后节点关键字数大于M / 2，则从其处借补	
					}else if (!(getNexIndex().equals("null")) 
							&& getNode(getNexIndex()).getEntries().size() > tree.getOrder() / 2
							&& getNode(getNexIndex()).getEntries().size() > 2
							&& !(getNode(getNexIndex()).getParentIndex().equals(parentIndex))) {
						Entry<Comparable, Object> entry = getNode(getNexIndex()).getEntries().get(0);
						List<Entry<Comparable, Object>> temp = getNode(getNexIndex()).getEntries();
						temp.remove(entry);
						getNode(getNexIndex()).setEntries(temp);
						temp.clear();						
						//添加到末尾
						entries = getEntries();
						entries.add(entry);
						setEntries(entries);
						remove(key);
					//否则需要合并叶子节点	
					}else {
						//同前面节点合并
						if (!(getPreIndex().equals("null")) 
								&& (getNode(getPreIndex()).getEntries().size() <= tree.getOrder() / 2 || getNode(getPreIndex()).getEntries().size() <= 2)
								&& getNode(getPreIndex()).getParentIndex().equals(parentIndex)) {
							for (int i = getNode(getPreIndex()).getEntries().size() - 1; i >=0; i--) {
								//从末尾开始添加到首位
								entries = getEntries();
								entries.add(0, getNode(getPreIndex()).getEntries().get(i));
								setEntries(entries);
							}							
							remove(key);
							getNode(getPreIndex()).setParentIndex("null");
							getNode(getPreIndex()).setEntries(null);
							List<String> temp = getNode(getParentIndex()).getChildrenIndex();
							temp.remove(getPreIndex());
							getNode(getParentIndex()).setChildrenIndex(temp);
							temp.clear();
//							parent.getChildren().remove(previous);
							//更新链表
							if (!getNode(getPreIndex()).getPreIndex().equals("null")) {
								String temp2 = getPreIndex();
								getNode(getNode(temp2).getPreIndex()).setNexIndex(this.fileIndex);
								setPreIndex(getNode(temp2).getParentIndex());
								getNode(temp2).setPreIndex(null);
								getNode(temp2).setNexIndex(null);							
							}else {
								tree.setHeadIndex(this.fileIndex);
								getNode(getPreIndex()).setNexIndex("null");
								setPreIndex("null");
							}
						//同后面节点合并	
						}else if(!getNexIndex().equals("null") 
								&& (getNode(getNexIndex()).getEntries().size() <= tree.getOrder() / 2 || getNode(getNexIndex()).getEntries().size() <= 2)
								&& getNode(getNexIndex()).getParentIndex().equals(parentIndex)){
							for (int i = 0; i < getNode(getNexIndex()).getEntries().size(); i++) {
								//从首位开始添加到末尾
								entries = getEntries();
								entries.add(getNode(getNexIndex()).getEntries().get(i));
								setEntries(entries);
							}
							remove(key);
							getNode(getNexIndex()).setParentIndex("null");
							getNode(getNexIndex()).setEntries(null);
							List<String> temp = getNode(getParentIndex()).getChildrenIndex();
							temp.remove(getNexIndex());
							getNode(getParentIndex()).setChildrenIndex(temp);
							temp.clear();
							//更新链表
							if (!getNode(getNexIndex()).getNexIndex().equals("null") ) {
								String temp2 = getNexIndex();
								getNode(getNode(temp2).getNexIndex()).setPreIndex(this.fileIndex);
								setNexIndex(getNode(temp2).getNexIndex());
								getNode(temp2).setPreIndex(null);
								getNode(temp2).setNexIndex(null);
							}else {
								getNode(getNexIndex()).setPreIndex("null");
								setNexIndex("null");
							}
						}
					}
				}
				parent.updateRemove(tree);
			}
		//如果不是叶子节点	
		}else {
			//如果key小于等于节点最左边的key，沿第一个子节点继续搜索
			if (key.compareTo(entries.get(0).getKey()) <= 0) {
				getNode(getChildrenIndex().get(0)).remove(key, tree);
			//如果key大于节点最右边的key，沿最后一个子节点继续搜索
			}else if (key.compareTo(entries.get(entries.size()-1).getKey()) >= 0) {
				getNode(getChildrenIndex().get(getChildrenIndex().size()-1)).remove(key, tree);
			//否则沿比key大的前一个子节点继续搜索
			}else {
				for (int i = 0; i < entries.size(); i++) {
					if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i+1).getKey().compareTo(key) > 0) {
						getNode(getChildrenIndex().get(i)).remove(key, tree);
						break;
					}
				}	
			}
		}
	}
	
	/** 判断当前节点是否包含该关键字*/
	@SuppressWarnings("unchecked")
	protected boolean contains(Comparable key) {
		for (Entry<Comparable, Object> entry : entries) {
			if (entry.getKey().compareTo(key) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 插入到当前节点的关键字中
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	protected void insertOrUpdate(Comparable key, Object obj) throws Exception{
		entries = getEntries();
		Entry<Comparable, Object> entry = new SimpleEntry<Comparable, Object>(key, obj);
		//如果关键字列表长度为0，则直接插入
		if (entries.size() == 0) {
			entries.add(entry);
			setEntries(entries);
			return;
		}
		//否则遍历列表
		for (int i = 0; i < entries.size(); i++) {
			//如果该关键字键值已存在，则更新
			if (entries.get(i).getKey().compareTo(key) == 0) {
				entries.get(i).setValue(obj);
				setEntries(entries);
				return;
			//否则插入	
			}else if (entries.get(i).getKey().compareTo(key) > 0){
				//插入到链首
				if (i == 0) {
					entries.add(0,entry);
					setEntries(entries);
					return;
				//插入到中间
				}else {
					entries.add(i,entry);
					setEntries(entries);
					return;
				}
			}
		}
		//插入到末尾
		entries.add(entries.size(),entry);
		setEntries(entries);
	}
	
	/** 删除节点
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	protected void remove(Comparable key) throws Exception{
		int index = -1;
		for (int i = 0; i < getEntries().size(); i++) {
			if (getEntries().get(i).getKey().compareTo(key) == 0) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			entries = getEntries();
			entries.remove(index);
			setEntries(entries);
		}
	}
	
	
//1111111111111111111111111111111111
	
	public boolean getLeaf() throws Exception {
		if(gImfo("isLeaf").equals("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
		 
	}

	public boolean getRoot() throws Exception {
		if(gImfo("isRoot").equals("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
		 
	}
	
	public void setRoot(boolean isRoot) throws Exception {
		this.isRoot = isRoot;
		String imfo = "isRoot";
		List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		int keysize=imfo.length();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))
			{
				sb.append("isRoot="+isRoot+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
		ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
	}
//	public void setLeaf(boolean isLeaf) throws Exception {
//		String imfo = "isLeaf";
//		List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
//		int keysize=imfo.length();
//		StringBuilder sb=new StringBuilder();
//		for(int i=0;i<imfoCatch.size();i++)
//		{
//			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))
//			{
//				sb.append("isLeaf="+isLeaf+";"+"\n");
//			}
//			else
//			{
//				sb.append(imfoCatch.get(i)+"\n");
//			}
//		}
//		ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
//		this.isLeaf = isLeaf; 
//	}
	
	@SuppressWarnings("unchecked")
	public List<Entry<Comparable, Object>> getEntries() throws Exception  {
	if(gImfo("entries").equals("[]"))
	{
		entries = new ArrayList<Entry<Comparable, Object>>();
		return entries;
	}
	else
	{
		entries = new ArrayList<Entry<Comparable, Object>>();
		int end = gImfo("entries").length();
		String geten = gImfo("entries").substring(1, end-1);
//		System.out.println("670670"+gImfo("entries"));
		String[] arr =geten.split(", ");
		for(String equal:arr)
		{
			String[]x=equal.split("=");
			int a =Integer.parseInt(x[0]);
			Object b=x[1];
			Entry<Comparable, Object> entry = new SimpleEntry<Comparable, Object>(a, b);
			entries.add(entry);
		}
		return entries;
	}
}

 
	@SuppressWarnings("unchecked")
	public void setEntries(List<Entry<Comparable, Object>> entries) throws Exception {
		String imfo = "entries";
		List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		int keysize=imfo.length();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))
			{
				sb.append("entries="+entries+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
		ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
		this.entries = entries;
	}

	 public String getParentIndex() throws Exception {
		 parentIndex = gImfo("parentIndex");
		 return parentIndex;
	 	}

	 public void setParentIndex(String parentIndex) throws Exception {
		 List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		 StringBuilder sb=new StringBuilder();
		 for(int i=0;i<imfoCatch.size();i++)
		 {
//			 System.out.println("!!!!"+i);
			if(i==2)
			{
				sb.append("parentIndex="+parentIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
			}
			ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
		 	this.parentIndex = parentIndex;
	 	}
	 public List<String> getChildrenIndex() throws Exception {
		 if(gImfo("childsIndex").equals("null"))
			{
			 childsIndex = new ArrayList<String>();
			return childsIndex;
			}
			else
			{
				childsIndex = new ArrayList<String>();
				int end = gImfo("childsIndex").length();
				String geten = gImfo("childsIndex").substring(1, end-1);
				String[] arr =geten.split(", ");
				for(String equal:arr)
				{
					childsIndex.add(equal);
				}
				return childsIndex;
			}
	 	}

	 public void setChildrenIndex(List<String>  childsIndex) throws Exception {
		 List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		 StringBuilder sb=new StringBuilder();
		 for(int i=0;i<imfoCatch.size();i++)
		 {
			if(i==3)
			{
				sb.append("childsIndex="+childsIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
		}
			ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
			this.childsIndex = childsIndex;
	 	}
	 
	 public String getPreIndex() throws Exception {
		 preIndex = gImfo("preIndex");
		 return preIndex;
		}

	 public void setPreIndex(String preIndex) throws Exception {
		 List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		 StringBuilder sb=new StringBuilder();
		 for(int i=0;i<imfoCatch.size();i++)
		 {
			if(i==4)
			{
				sb.append("preIndex="+preIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
			}
			ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
		 	this.preIndex = preIndex;
		}

	 public String getNexIndex() throws Exception {
		 nexIndex = gImfo("nexIndex");
		 return nexIndex;
		}

	 public void setNexIndex(String nexIndex) throws Exception {
		 List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		 StringBuilder sb=new StringBuilder();
		 for(int i=0;i<imfoCatch.size();i++)
		 {
			if(i==5)
			{
				sb.append("nexIndex="+nexIndex+"\n");
			}
			else
			{
				sb.append(imfoCatch.get(i)+"\n");
			}
			}
			ReadWriteTxt.toTxtReplace(sb.toString(), this.fileIndex);
		 	this.nexIndex = nexIndex;
		}
	 
//2222222222222222222222222222222222222	
//	 public boolean isLeaf() {  
//	        return isLeaf;  
//	    }  
//	  
//	 public void setLeaf(boolean isLeaf) {  
//	        this.isLeaf = isLeaf;  
//	    } 
//	 public List<Entry<Comparable, Object>> getEntries() {
//		 	return entries;
//	 	}
//
//	 public void setEntries(List<Entry<Comparable, Object>> entries) {
//		 	this.entries = entries;
//	 	}

	 public Node getParent() {
		 	
		 	return parent;
	 	}

	 public void setParent(Node parent) {
		 	this.parent = parent;
	 	}
	 public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public Node getNext() {
			return nextnext;
		}

		public void setNext(Node next) {
			this.nextnext = next;
		}

	
	

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
//	public boolean isRoot() {
//		return isRoot;
//	}

	
	
	@SuppressWarnings("unchecked")
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("isRoot: ");
		sb.append(isRoot);
		sb.append(", ");
		sb.append("isLeaf: ");
		sb.append(isLeaf);
		sb.append(", ");
		sb.append("keys: ");
		for (Entry entry : entries){
			sb.append(entry.getKey());
			sb.append(", ");
		}
		sb.append(", ");
		return sb.toString();
		
	}
	public String gImfo(String imfo) throws Exception
	{
		List<String> imfoCatch = ReadWriteTxt.readLines(this.fileIndex);
		int keysize=imfo.length();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))return imfoCatch.get(i).substring(keysize+1);
		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public Node getNode(String adr) throws Exception
	{
		if(adr==null)
		{
			return null;
		}
		else
		{
//		System.out.println("~~~~~"+adr);
		List<String> imfoCatch = ReadWriteTxt.readLines(adr);
		Node result = new Node() ;
//		System.out.println("@@@@@@@@@@@@"+adr);
		result.fileIndex = adr;
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(i==0)
			{
				if(imfoCatch.get(i).substring(6+1).equals("true"))result.isRoot = true;
				else result.isRoot = false;
			}
			if(i==1)
			{
				if(imfoCatch.get(i).substring(6+1).equals("true"))result.isLeaf = true;
				else result.isLeaf = false;
			}
			if(i==2)
			{
				if(imfoCatch.get(i).substring(11+1).equals("null"))result.parentIndex = null;
				else result.parentIndex = imfoCatch.get(i).substring(11+1);
			}
			if(i==3)
			{
				if(imfoCatch.get(i).substring(13+1).equals("null"))result.childsIndex = null;
				else 
					{
					result.childsIndex = new ArrayList<String>();
					int end = gImfo("childsIndex").length();
					String geten = gImfo("childsIndex").substring(1, end-1);
					String[] arr =geten.split(", ");
					for(String equal:arr)
					{
						result.childsIndex.add(equal);
					}
					}
			}
			if(i==7)
			{
				if(imfoCatch.get(i).substring(7+1).equals("[]"))
				{
					result.entries = new ArrayList<Entry<Comparable, Object>>();
				}
				else
				{
					result.entries = new ArrayList<Entry<Comparable, Object>>();
					int end = gImfo("entries").length();
					String geten = gImfo("entries").substring(1, end-1);
					String[] arr =geten.split(", ");
					for(String equal:arr)
					{
						String[]x=equal.split("=");
						int a =Integer.parseInt(x[0]);
						Object b=x[1];
						Entry<Comparable, Object> entry = new SimpleEntry<Comparable, Object>(a, b);
						result.entries.add(entry);
					}
				}
			}
		}		
		return result;
	}
	}
	public List<Node> getNodeList(List<String> adr) throws Exception
	{
		if(adr==null)
		{
			return null;
		}
		else
		{
			List<Node> result = new  ArrayList<Node>();;
			for(int i=0;i<adr.size();i++)
			{
				result.add(getNode(adr.get(i)));
			}
			return result;
		}		
	}

}
