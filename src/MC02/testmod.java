package MC02;

import java.math.BigInteger;
import java.util.Scanner;

public class testmod {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		BigInteger num1,num2,num3;
		System.out.print("num1=");num1=input.nextBigInteger();
		System.out.print("num2=");num2=input.nextBigInteger();
		num3=num1.mod(num2);
		System.out.println(num3.equals(0));

	}

}
