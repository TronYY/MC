package MC03;

import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;

public class DLP {
	
	public DLP() {
		
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
		return(new BigInteger(rand.nextInt(8999)+1000+""));//产生一个四位整数
	
	}
	
	
	public void Diffie_Hellman() {
		BigInteger p,g,a,A,b,B,keyA,keyB;
		
		p=createBigPrime(500);
		g=createRandomInt();
		System.out.println("A trusted party chooses and publishes a large prime p="+p+" and an integer g="+g);
		System.out.println();
		
		FundAl FA=new FundAl();
		/*Alice*/
		a=createRandomInt();
		A=FA.fastPowering(g,a,p);
		
		/*Bob*/
		b=createRandomInt();
		B=FA.fastPowering(g,b,p);
		
		System.out.println("Alice sends "+A+" to Bob————————>A;");
		System.out.println("B<————————Bob sends "+B+" to Alice;");
		System.out.println();
		
		keyA=FA.fastPowering(B,a,p);
		keyB=FA.fastPowering(A,b,p);
		
		System.out.println("Alice cpmoutes the secret value keyA="+keyA);
		System.out.println("Bob cpmoutes the secret value keyB="+keyB);
	}
	
	public void ElGamal() {
		BigInteger p,g,a,A,m,k,c1,c2;
		
		p=createBigPrime(500);
		g=createRandomInt();
		System.out.println("A trusted party chooses and publishes a large prime p="+p+" and an integer g="+g);
		System.out.println();
		
		FundAl FA=new FundAl();
		/*Alice*/
		a=createRandomInt();
		A=FA.fastPowering(g,a,p);
		System.out.println("Alice chooses private key,then publiches the public key A="+A);
		System.out.println();
		
		/*Bob*/
		m=createRandomInt();
		System.out.println("Bob wants to send plaintext m="+m);
		k=createRandomInt();
		c1=FA.fastPowering(g,k,p);
		c2=m.multiply(FA.fastPowering(A,k,p));
		System.out.println("Bob sends ciphertext (c1,c2)=("+c1+","+c2+") to Alice.");
		System.out.println();
		
		/*Alice*/
		BigInteger t=FA.fastPowering(c1,a,p);
		FA.extendedEuclidean(t, p);
		t=FA.getU().mod(p).abs();	
		t=t.multiply(c2).mod(p);
		System.out.println("After computation, Alice gets message="+t);
		
		
	}
	

}
