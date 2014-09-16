package Func;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
public class Fenci {
	/**
	 * @param input - String to be process 
	 * @return ArrayList - splited words, contain duplicate
	 */
	public static  ArrayList<String> split(String input){
		ArrayList<String> result = new ArrayList<String>();
		
		//String text="基于java语言开发的轻量级的中文分词工具包";
		StringReader sr=new StringReader(input);
		IKSegmenter ik=new IKSegmenter(sr, true);
		Lexeme lex=null;
		try {
			while((lex=ik.next())!=null){
				result.add(lex.getLexemeText());
			}
		} catch (IOException e) {
			System.err.println("Error when trying to get splited words...");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static void main(String[] args){
		ArrayList<String> result = split("不可思议的人格高尚 感同身受驷马难追求 中小企业私募债券承销不积极");
		for(String tmp:result){
			System.out.println(tmp);
		}
	}

}
