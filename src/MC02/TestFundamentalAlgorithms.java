package MC02;

import java.util.Scanner;

public class TestFundamentalAlgorithms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FundamentalAlgorithms FA=new FundamentalAlgorithms() ;
		Scanner input=new Scanner(System.in);
		
		
		
		System.out.print("求两个整数的最大公约数的欧几里得算法,请输入a与b:");
		int a=input.nextInt();
		int b=input.nextInt();
		FA=new FundamentalAlgorithms();
		System.out.println("gcd(a,b)="+FA.euclidean(a,b));		
		System.out.println();
		
		System.out.print("求两个整数的最大公约数和线性组合的扩展欧几里得算法,请输入a与b:");
		a=input.nextInt();
		b=input.nextInt();	
		int r=FA.extendedEuclidean(a, b);
		System.out.println("gcd(a,b)="+r);		
		System.out.println("au+bv="+a+"·"+FA.getU()+"+"+b+"·"+FA.getV()+"="+r);
		System.out.println();
		
		System.out.print("整数的模逆算法,请输入代求元a与素数p:");
		a=input.nextInt();
		int p=input.nextInt();
		FA.extendedEuclidean(a, p);
		System.out.println("a^(-1)="+Math.abs(FA.getU()) % p);	
		System.out.println();
		
		System.out.print("整数的快速模幂算法g^A mod N,请输入g,A,N:");
		int g=input.nextInt();
		int A=input.nextInt();
		int N=input.nextInt();
		System.out.println(g+"^"+A+" mod "+N+"="+FA.fastPowering(g,A,N));
		
		

	}

}
