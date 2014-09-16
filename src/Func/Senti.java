package Func;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Util.Const;

public class Senti {
	private HashMap<String, Integer> dict;
	private HashSet<String> negativeDict;
	
	public Senti(){
		dict = new HashMap<String, Integer>();
		negativeDict = new HashSet<String>();
	}
	
	public void loadDict() throws IOException{
		InputStreamReader read = new InputStreamReader(new FileInputStream(Const.dictFile), "gbk");
		BufferedReader reader = new BufferedReader(read);
		String line = null;
		while((line=reader.readLine())!=null){
			String[] pair = line.split("\t");
			dict.put(pair[0], Integer.parseInt(pair[1]));
		}
		reader.close();
		
		BufferedReader nd = new BufferedReader(new InputStreamReader(new FileInputStream(Const.negativeFile),"gbk"));
		String line1 = null;
		while((line1=nd.readLine())!=null){
			negativeDict.add(line1);
		}
		nd.close();
	}
	
	public int analysize(String obj){
		if(obj==null||obj==""){
			return -1;
		}
		ArrayList<String> result = Fenci.split(obj);
		double calcSenti=0.0;
		ArrayList<Integer> arrayNeg = new ArrayList<Integer>();
		for(int i=0;i<result.size();i++){
			String word = result.get(i);
			if(negativeDict.contains(word)){
				arrayNeg.add(i);
			}
			if(!dict.containsKey(word))continue;
			else {
				int val =dict.get(word);
				//System.out.println(calcSenti);
				if(arrayNeg.size()%2==1){
					val = -val;
					arrayNeg.clear();    //After this sentiWord, sum the negative words from a new start point
				}
				calcSenti +=val;
			}
			//else negative++;
		}
		//System.out.println("positive: "+positive+"\tnegative: "+negative+"\n");
		/*if(positive-negative==0){
			return -1;
		}*/
		if(calcSenti==0.0){
			return -1;
		}
		else if(calcSenti>0){
			return 1;
		}
		else {
			return 2;
		}
	}
}
