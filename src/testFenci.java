import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import Func.Senti;
public class testFenci {
	
	public static void main(String[] args) throws IOException {
		String text="阿里启动路演美最大IPO 亏损锐减";   ///市场反应 **|**如何应对
		StringReader sr=new StringReader(text);
		IKSegmenter ik=new IKSegmenter(sr, true);
		Lexeme lex=null;
		while((lex=ik.next())!=null){
			System.out.print(lex.getLexemeText()+"|");
		}
		
		/*
类org.wltea.analyzer.dic.Dictionary 
说明： IK分词器的词典对象。它负责中文词汇的加载，内存管理和匹配检索。 
	public static void loadExtendWords(List<String> extWords) 
说明：加载用户扩展的词汇列表到IK的主词典中，增加分词器的可识别词语。 
参数1：List<String> extWords ， 扩展的词汇列表 
返回值：无 
		 */
		Senti senti = new Senti();
		senti.loadDict();
		System.out.println(senti.analysize(text));
	}

}
