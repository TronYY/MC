package MidTest;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");
	
	public RSA() {
		
	}

	/*产生一个素数，是素数的概率超过1-2^(-10)*/
	public BigInteger createBigPrime(int len) {
		BigInteger p;
		do {
			p=new BigInteger(len, 10, new Random());//此构造函数用于构造一个随机生成正BigInteger的可能是以指定的len的素数。可能性超过1-2^(-10)
		} while (!p.isProbablePrime(10));//是素数则跳出构造
		return p;
		
	}
	
	
	public BigInteger createRandomInt(int len) {
		Random rand = new Random();
		//return(new BigInteger( rand.nextInt((int)(9*Math.pow(10,len-1)-1+Math.pow(10,len-1)))+"" ));//产生一个len位整数
		//return(new BigInteger(rand.nextInt(9*10^(len-1)-1)+10^(len-1)+""));//产生一个四位整数
		return new BigInteger(len, rand);
	}
	
	public void RSA_C_E_D() {
		BigInteger p,q,pq_1,e,N,m,c,d,t;
		/*Bob*/
		FundAl FA=new FundAl();
		p=createBigPrime(512);
		q=new BigInteger("1154639830424094586704374196379");
		pq_1=p.subtract(ONE).multiply(q.subtract(ONE));
		e=createBigPrime(16);
		N=p.multiply(q);
		System.out.println("Bob publishes his public key (N,e)=("+N+","+e+").");
		
		/*Alice*/
		m=new BigInteger("970268244100785623001747317880701213200869947407373312409972");
		System.out.println("Alice wants to send plaintext m="+m);
		long a=System.currentTimeMillis();
		c=FA.fastPowering(m, e, N);
		System.out.println("加密执行耗时 : "+(System.currentTimeMillis()-a)+"毫秒 ");
		System.out.println("Alice sends "+c+" to Bob.");
		
		/*Bob*/
		long b=System.currentTimeMillis();
		FA.extendedEuclidean(e, pq_1);
		
		d=FA.getU().mod(pq_1).abs();
		
		t=FA.fastPowering(c, d, N);
		System.out.println("解密执行耗时 : "+(System.currentTimeMillis()-b)+"毫秒 ");
		System.out.println("After computation,Bob gets message="+t);
		System.out.println("\n已使用内存："+ (Runtime.getRuntime().totalMemory() 
				- Runtime.getRuntime().freeMemory())/1024/1024 + "M");  
		
	}
	
}
