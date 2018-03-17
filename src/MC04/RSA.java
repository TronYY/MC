package MC04;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");
	
	public RSA() {
		
	}

	/*����һ���������������ĸ��ʳ���1-2^(-10)*/
	public BigInteger createBigPrime(int len) {
		BigInteger p;
		do {
			p=new BigInteger(len, 10, new Random());//�˹��캯�����ڹ���һ�����������BigInteger�Ŀ�������ָ����len�������������Գ���1-2^(-10)
		} while (!p.isProbablePrime(10));//����������������
		return p;
		
	}
	
	
	public BigInteger createRandomInt() {
		Random rand = new Random();
		return(new BigInteger(rand.nextInt(8999)+1000+""));//����һ����λ����
	
	}
	
	public void RSA_C_E_D() {
		BigInteger p,q,pq_1,e,N,m,c,d,t;
		
		/*Bob*/
		FundAl FA=new FundAl();
		p=createBigPrime(10);
		q=createBigPrime(10);
		pq_1=p.subtract(ONE).multiply(q.subtract(ONE));
		e=createBigPrime(10);
		N=p.multiply(q);
		System.out.println("Bob publishes his public key (N,e)=("+N+","+e+").");
		
		/*Alice*/
		m=createRandomInt();
		System.out.println("Alice wants to send plaintext m="+m);
		c=FA.fastPowering(m, e, N);
		System.out.println("Alice sends "+c+" to Bob.");
		
		/*Bob*/
		//d=FA.fastPowering(e, pq_1.subtract(TWO), pq_1);
		FA.extendedEuclidean(e, pq_1);
		d=FA.getU().mod(pq_1).abs();	
		t=FA.fastPowering(c, d, N);
		System.out.println("After computation,Bob gets message="+t);
	}
	
}
