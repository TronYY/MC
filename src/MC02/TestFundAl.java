package MC02;

import java.math.BigInteger;
import java.util.Scanner;

public class TestFundAl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FundAl FA=new FundAl() ;
		Scanner input=new Scanner(System.in);
		
		
		
		System.out.print("求两个整数的最大公约数的欧几里得算法,请输入a与b:");
		BigInteger a=input.nextBigInteger();
		BigInteger b=input.nextBigInteger();
		FA=new FundAl();
		System.out.println("gcd(a,b)="+FA.euclidean(a,b));		
		System.out.println();
		
		System.out.print("求两个整数的最大公约数和线性组合的扩展欧几里得算法,请输入a与b:");
		a=input.nextBigInteger();
		b=input.nextBigInteger();	
		BigInteger r=FA.extendedEuclidean(a, b);
		System.out.println("gcd(a,b)="+r);		
		System.out.println("au+bv="+a+"·"+FA.getU()+"+"+b+"·"+FA.getV()+"="+r);
		System.out.println();
		
		System.out.print("整数的模逆算法,请输入代求元a与素数p:");
		a=input.nextBigInteger();
		BigInteger p=input.nextBigInteger();
		FA.extendedEuclidean(a, p);
		System.out.println("a^(-1)="+FA.getU().mod(p).abs());	
		System.out.println();
		
		System.out.print("整数的快速模幂算法g^A mod N,请输入g,A,N:");
		BigInteger g=input.nextBigInteger();
		BigInteger A=input.nextBigInteger();
		BigInteger N=input.nextBigInteger();
		System.out.println(g+"^"+A+" mod "+N+"="+FA.fastPowering(g,A,N));
		
		

	}

}
