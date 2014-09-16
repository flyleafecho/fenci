import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Set;

import Func.Senti;
import Util.LinkDatabase;


public class testSenti {
	public static void main(String[] args) throws Exception{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		LinkDatabase lnk = new LinkDatabase();
		lnk.connect();
		Senti senti = new Senti();
		senti.loadDict();
		ResultSet rs = lnk.executeQuerySQL("select id, name from crawldata");
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int sentiVal = senti.analysize(name);
			System.out.println(id+"\t"+sentiVal+"\t"+name);
			map.put(id, sentiVal);
		}
		lnk.close();
		
		LinkDatabase lnk_inner = new LinkDatabase();
		lnk_inner.connect();
		Set<Integer> keySet = map.keySet();
		for(Integer id : keySet){
			int sentiVal = map.get(id);
			System.out.println(id+"\t"+sentiVal);
			lnk_inner.executeSQL("update crawldata set senti="+sentiVal+" where id="+id);
		}
		lnk_inner.close();
	}
}
