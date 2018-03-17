package MC01;//包可以修改**
import java.io.File;  
import java.io.InputStreamReader;  
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;  
import java.io.FileWriter;
import java.io.IOException;
public class ToTxt {
	File writename;
	BufferedWriter out;
	public ToTxt() throws IOException {
		writename = new File("E:\\Doc\\Math\\MC\\src\\MC01\\output.txt"); //**
		writename.createNewFile(); // 创建新文件  
        out = new BufferedWriter(new FileWriter(writename)); 
	}
	
	public void println(String str) throws IOException {
		out.write(str); // \r\n即为换行  
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后记得关闭文件  
	}

}
