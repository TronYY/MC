package MC01;//�������޸�**
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
		writename.createNewFile(); // �������ļ�  
        out = new BufferedWriter(new FileWriter(writename)); 
	}
	
	public void println(String str) throws IOException {
		out.write(str); // \r\n��Ϊ����  
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
	}

}
