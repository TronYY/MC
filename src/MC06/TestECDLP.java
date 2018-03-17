package MC06;

import java.math.BigInteger;
import java.util.Scanner;

import MC02.FundAl;

public class TestECDLP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input=new Scanner(System.in);
		System.out.println("E: Y^2=X^3+AX+B over GF(p)");
		System.out.println("请输入A B p");
		
		BigInteger A=input.nextBigInteger();
		BigInteger B=input.nextBigInteger();
		BigInteger p=input.nextBigInteger();
		ECDLP EC=new ECDLP(A,B,p);
		
		BigInteger[] P=new BigInteger[2];
		BigInteger[] Q=new BigInteger[2];
		System.out.println("请输入P的坐标");		
		P[0]=input.nextBigInteger();
		P[1]=input.nextBigInteger();
		System.out.println("请输入Q的坐标");		
		Q[0]=input.nextBigInteger();
		Q[1]=input.nextBigInteger();
		
		BigInteger[] ansR=new BigInteger[2];
		ansR=EC.calculateAnsPoint(P,Q);
		System.out.println("R'=P+Q=("+ansR[0]+","+ansR[1]+")  in E(GF("+p+")).");	
		
		System.out.println("请输入n=");
		BigInteger n=input.nextBigInteger();
		BigInteger[] ansnP=new BigInteger[2];
		ansnP=EC.doubleAndAdd(n,P);
		System.out.println(n+"P=("+ansnP[0]+","+ansnP[1]+")");	
		
			
	}

}
