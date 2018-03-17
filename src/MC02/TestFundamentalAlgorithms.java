package MC02;

import java.util.Scanner;

public class TestFundamentalAlgorithms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FundamentalAlgorithms FA=new FundamentalAlgorithms() ;
		Scanner input=new Scanner(System.in);
		
		
		
		System.out.print("���������������Լ����ŷ������㷨,������a��b:");
		int a=input.nextInt();
		int b=input.nextInt();
		FA=new FundamentalAlgorithms();
		System.out.println("gcd(a,b)="+FA.euclidean(a,b));		
		System.out.println();
		
		System.out.print("���������������Լ����������ϵ���չŷ������㷨,������a��b:");
		a=input.nextInt();
		b=input.nextInt();	
		int r=FA.extendedEuclidean(a, b);
		System.out.println("gcd(a,b)="+r);		
		System.out.println("au+bv="+a+"��"+FA.getU()+"+"+b+"��"+FA.getV()+"="+r);
		System.out.println();
		
		System.out.print("������ģ���㷨,���������Ԫa������p:");
		a=input.nextInt();
		int p=input.nextInt();
		FA.extendedEuclidean(a, p);
		System.out.println("a^(-1)="+Math.abs(FA.getU()) % p);	
		System.out.println();
		
		System.out.print("�����Ŀ���ģ���㷨g^A mod N,������g,A,N:");
		int g=input.nextInt();
		int A=input.nextInt();
		int N=input.nextInt();
		System.out.println(g+"^"+A+" mod "+N+"="+FA.fastPowering(g,A,N));
		
		

	}

}
