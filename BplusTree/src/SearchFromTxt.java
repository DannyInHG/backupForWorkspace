import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;


public class SearchFromTxt {
	
	public static void main(String[] args) throws Exception {
		String root = "11.txt";
		String result =searchLeaf(7,root);
		System.out.println(result);
	}
	
	public static String searchLeaf(Comparable index,String adr) throws Exception{
		List<Entry<Comparable, Object>> entries = new ArrayList<Entry<Comparable, Object>>();
		entries = getEntries(adr);
		if(index.compareTo(entries.get(0).getKey())<0)
		{
			System.out.println("未查找到此索引");
			return null;
		}
		else
		{
			for(int i =0;i<entries.size();i++)
			{
				if(i==(entries.size()-1))
				{
					if(getChildrenIndex(adr)==null)
					{
						if(index.compareTo(entries.get(i).getKey())!=0)
						{
							System.out.println("未查找到此索引");
							return null;
						}
						Object txtadr = getEntries(adr).get(i);
						return txtadr.toString();
					}
					String txtadr = getChildrenIndex(adr).get(i);
					return searchLeaf(index,txtadr);
				}
				else if(index.compareTo(entries.get(i).getKey())>=0&&index.compareTo(entries.get(i+1).getKey())<0)
				{
					System.out.println("asdasdasd"+getChildrenIndex(adr));
					if(getChildrenIndex(adr)==null)
					{
						System.out.println("asdasdasdasdasdasd");
						if(index.compareTo(entries.get(i).getKey())!=0)
						{
							System.out.println("未查找到此索引");
							return null;
						}
						Object txtadr = getEntries(adr).get(i);
						return txtadr.toString();
					}
					String txtadr = getChildrenIndex(adr).get(i);
					return searchLeaf(index,txtadr);
				}
			}
			return null;
		}
	}
	
	public static String gImfo(String imfo,String adr) throws Exception
	{
		List<String> imfoCatch = ReadWriteTxt.readLines(adr);
		int keysize=imfo.length();
		for(int i=0;i<imfoCatch.size();i++)
		{
			if(imfoCatch.get(i).substring(0, keysize).equals(imfo))return imfoCatch.get(i).substring(keysize+1);
		}		
		return null;
	}
	
	public static List<Entry<Comparable, Object>> getEntries(String adr) throws Exception  {
		if(gImfo("entries",adr).equals("[]"))
		{
			List<Entry<Comparable, Object>>entries = new ArrayList<Entry<Comparable, Object>>();
			return entries;
		}
		else
		{
			List<Entry<Comparable, Object>>entries = new ArrayList<Entry<Comparable, Object>>();
			entries = new ArrayList<Entry<Comparable, Object>>();
			int end = gImfo("entries",adr).length();
			String geten = gImfo("entries",adr).substring(1, end-1);
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
	
	 public static List<String> getChildrenIndex(String adr) throws Exception {
		 if(gImfo("childsIndex",adr).equals("null"))
			{
			 List<String>childsIndex = new ArrayList<String>();
			 childsIndex = null;
			 return childsIndex;
			}
			else
			{
				List<String>childsIndex = new ArrayList<String>();
				childsIndex = new ArrayList<String>();
				int end = gImfo("childsIndex",adr).length();
				String geten = gImfo("childsIndex",adr).substring(1, end-1);
				String[] arr =geten.split(", ");
				for(String equal:arr)
				{
					childsIndex.add(equal);
				}
				return childsIndex;
			}
	 	}
}
