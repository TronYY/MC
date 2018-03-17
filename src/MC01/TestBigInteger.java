package MC01;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
public class TestBigInteger {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		BigInteger num1,num2;
		System.out.print("num1=");num1=input.nextBigInteger();
		System.out.print("num2=");num2=input.nextBigInteger();
		
		System.out.println(num1+"+"+num2+"="+num1.add(num2));
		System.out.println(num1+"-"+num2+"="+num1.subtract(num2));
		System.out.println(num1+"*"+num2+"="+num1.multiply(num2));
		System.out.println(num1+"/"+num2+"="+num1.divide(num2));
		System.out.println(num1+"%"+num2+"="+num1.mod(num2));
		System.out.println("gcd("+num1+","+num2+")="+num1.gcd(num2));
		ToTxt txt=new ToTxt();
		txt.println("Â½å°ìÏ");
	}

}
