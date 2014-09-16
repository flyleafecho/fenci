package Util;

import java.io.File;

/**
* @PackageName:util
* @ClassName: Const
* @Description: const parameters of the system
*/
public class Const{
	
	public static File dictFile = new File("PNP.txt");
	public static File negativeFile = new File("NegativeDict.txt");

	//public static int bufferWindowSize = 3;
	
	public static String DBDriver = "com.mysql.jdbc.Driver";
	public static String DBConnectStr = "jdbc:mysql://localhost/crawler?useUnicode=true&characterEncoding=GBK";
	public static String DBUser = "root";
	public static String DBPassword = "root";

}
