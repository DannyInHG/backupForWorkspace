import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTxt {
	public static void toTxtReplace(String str,String file) throws IOException{
		File f=new File(file);
		if(!f.exists()) f.createNewFile();
//		System.out.println("�½��ļ��ɹ�"+file);
		FileWriter fw=new FileWriter(file);
		fw.write(str);
		fw.close();		
	}
	public static void toTxtAppend(String str,String file) throws IOException{
		File f=new File(file);
		if(!f.exists()) f.createNewFile();
		BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
		bw.append("\n"+str);
		bw.close();
	}
	
	public static List<String> readLines(String file) throws IOException{
		BufferedReader fr=new BufferedReader(new FileReader(file));
		String str=null;
		List<String> list=new ArrayList<String>();
		while((str=fr.readLine())!=null)
			list.add(str);
		return list;
	}
	
	public static boolean deleteFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            System.out.println("ɾ�������ļ�"+fileName+"�ɹ���");     
            return true;     
        }else{     
            System.out.println("ɾ�������ļ�"+fileName+"ʧ�ܣ�");     
            return false;     
        }     
    } 
//	public static void repalceLines(String str,int line,String file) throws IOException{
//		BufferedReader fr=new BufferedReader(new FileReader(file));
//		List<String> list=new ArrayList<String>();
//		while((str=fr.readLine())!=null)
//			list.add(str);
//		return list;
//	}
	
}
