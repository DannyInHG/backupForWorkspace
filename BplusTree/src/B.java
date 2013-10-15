



public interface B {
	   public Object get(Comparable key) throws Exception;   //查询
	   public void remove(Comparable key) throws Exception;    //移除
	   public void insertOrUpdate(Comparable key, Object obj) throws Exception; //插入或者更新，如果已经存在，就更新，否则插入
}
