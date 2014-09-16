
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Func.Fenci;
import Util.LinkDatabase;

public class wordFrequency {

	public static void main(String[] args) throws IOException{
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		File file = new File("D:/new/frequencyNeeq.txt");
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)); 
		
	    LinkDatabase lnk = new LinkDatabase();
	    lnk.connect();
		String selectSql = "select name from crawldata";
		ResultSet rs = lnk.executeQuerySQL(selectSql);
		try {
			while(rs.next()){
				ArrayList<String> result = Fenci.split(rs.getString(1));
				for(int i=0;i<result.size();i++){
					if(map.get(result.get(i))==null){
						map.put(result.get(i), 1);
					}
					else{
						map.put(result.get(i), map.get(result.get(i))+1);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error when tring to get query resultset...");
			e.printStackTrace();
		}finally{
			lnk.close();
		}
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
			String key = entry.getKey();
			Integer val = entry.getValue();
			writer.write(key+"\t"+val+"\n");
			writer.flush();
		}		
		writer.close();

	}
	
}
