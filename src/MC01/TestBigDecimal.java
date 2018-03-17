package MC01;
import java.math.BigDecimal;
import java.util.Scanner;
public class TestBigDecimal {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		BigDecimal num1,num2;
		System.out.print("num1=");num1=input.nextBigDecimal();
		System.out.print("num2=");num2=input.nextBigDecimal();
		
		System.out.println(num1+"+"+num2+"="+num1.add(num2));
		System.out.println(num1+"-"+num2+"="+num1.subtract(num2));
		System.out.println(num1+"*"+num2+"="+num1.multiply(num2));
		System.out.println(num1+"/"+num2+"="+num1.divide(num2,2,BigDecimal.ROUND_HALF_EVEN));//保留2位小数，四舍五入
		
		
	}

}
