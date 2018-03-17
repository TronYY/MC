package MidTest;
import java.math.BigInteger;

public class FundAl {
	protected BigInteger u;
	protected BigInteger v;
	protected BigInteger zero;
	
	
	public FundAl() {
		zero=new BigInteger("0");
	}
	
	public BigInteger getU() {
		return u;
	}
	public BigInteger getV() {
		return v;
	}
	
	public BigInteger euclidean(BigInteger a,BigInteger b) {
		if (b.compareTo(zero)==0) return a;
		return euclidean(b,a.mod(b));
		
	}
	
	public BigInteger extendedEuclidean(BigInteger a,BigInteger b) {
		if (b.compareTo(zero)==0) {
			u=new BigInteger("1");
			v=new BigInteger("0");
			return a;
		}		
		BigInteger r= extendedEuclidean(b,a.mod(b));
		BigInteger t=u;
		u=v;
		v=t.subtract(a.divide(b).multiply(v));
		return r;
	
	}
	
	
	public BigInteger fastPowering(BigInteger g,BigInteger A,BigInteger N) {
			
		BigInteger a=g;
		BigInteger b=new BigInteger("1");
		BigInteger one=new BigInteger("1");
		BigInteger two=new BigInteger("2");
		while (A.compareTo(zero)!=0) {
			if (A.mod(two).compareTo(one)==0) b=b.multiply(a).mod(N);
			a=a.multiply(a).mod(N);
			A=A.divide(two);				
		}
		return b;		
	}
}
