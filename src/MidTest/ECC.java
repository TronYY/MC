package MidTest;

import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;

public class ECC {
	
	public ECC() {
		
	}	
	
	
	public BigInteger createBigPrime(int len) {
		BigInteger p;
		do {
			p=new BigInteger(len, 10, new Random());//此构造函数用于构造一个随机生成正BigInteger的可能是以指定的len的素数。可能性超过1-2^(-10)
		} while (!p.isProbablePrime(10));//是素数则跳出构造
		return p;
		
	}
	
	public BigInteger createRandomInt() {
		Random rand = new Random();
		int len=10;
		//return(new BigInteger( rand.nextInt((int)(9*Math.pow(10,len-1)-1+Math.pow(10,len-1)))+"" ));//产生一个len位整数
		//return(new BigInteger(rand.nextInt(9*10^(len-1)-1)+10^(len-1)+""));//产生一个四位整数
		return new BigInteger(len, rand);
	}
	
	
	
	public void ElGamal() {
		BigInteger p,A,B;
		BigInteger nA;
		
		BigInteger[] P=new BigInteger[2];
		BigInteger[] QA=new BigInteger[2];
		BigInteger k;
		BigInteger[] M=new BigInteger[2];
		BigInteger[] C1=new BigInteger[2];
		BigInteger[] C2=new BigInteger[2];
		
		
		p=createBigPrime(10);
		BigInteger FOUR = new BigInteger("4");
		BigInteger TS = new BigInteger("27");
		BigInteger ZERO = new BigInteger("0");
		do {
			A=createRandomInt();
			B=createRandomInt();
		} while (FOUR.multiply(A).multiply(A).multiply(A).add(TS.multiply(B).multiply(B)).compareTo(ZERO)==0);
		
		P[0]=createRandomInt();
		P[1]=createRandomInt();
		
		System.out.println("A trusted party chooses and publishes a large prime p="+p);
		System.out.println("E=X^3+"+A+"X+"+B);
		System.out.println("P=("+P[0]+","+P[1]+")");
		System.out.println();
		
		ECDLP ecdlp=new ECDLP(A,B,p);
		/*Alice*/
		nA=createRandomInt();
		QA=ecdlp.doubleAndAdd(nA,P);
		System.out.println("Alice chooses private key,then publiches the public key QA=("+QA[0]+","+QA[1]+")");
		System.out.println();
		
		/*Bob*/
		M[0]=createRandomInt();
		M[1]=createRandomInt();
		System.out.println("Bob wants to send plaintext M=("+M[0]+","+M[1]+")");
		k=createRandomInt();
		
		long a=System.currentTimeMillis();
		C1=ecdlp.doubleAndAdd(k,P);
		C2=ecdlp.calculateAnsPoint(M, ecdlp.doubleAndAdd(k,QA));
		System.out.println("加密执行耗时 : "+(System.currentTimeMillis()-a)+"毫秒 ");
		
		System.out.println("Bob sends ciphertext (C1,C2)=(("+C1[0]+","+C1[1]+"),("+C2[0]+","+C2[1]+")) to Alice.");
		System.out.println();
		
		/*Alice*/
		
		BigInteger[] t=new BigInteger[2];
		
		long b=System.currentTimeMillis();
		t=ecdlp.doubleAndAdd(nA,C1);
		t[1]=ZERO.subtract(t[1]);
		t=ecdlp.calculateAnsPoint(C2, t);	
		System.out.println("解密执行耗时 : "+(System.currentTimeMillis()-a)+"毫秒 ");
		System.out.println("After computation, Alice gets message=("+t[0]+","+t[1]+")");
		System.out.println("已使用内存："+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "M");  
		
	}
	

}
