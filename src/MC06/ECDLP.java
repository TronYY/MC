package MC06;
import java.math.BigInteger;
import MC02.FundAl;

public class ECDLP {
	protected BigInteger A,B,p;
	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");
	private final static BigInteger THREE = new BigInteger("3");
	private final static BigInteger INF = new BigInteger("-1");
	
	public ECDLP() {
	}
	
	public ECDLP(BigInteger A,BigInteger B,BigInteger p) {
		this.A=A;
		this.B=B;
		this.p=p;
	}
	
	
	public BigInteger[] calculateAnsPoint(BigInteger[] P,BigInteger[] Q) {
		BigInteger lam;
		BigInteger[] ans=new BigInteger[2];
		FundAl FA=new FundAl() ;
		/*基本情况*/
		if (P[0].compareTo(INF)==0 && P[1].compareTo(INF)==0) return Q;
		if (Q[0].compareTo(INF)==0 && Q[1].compareTo(INF)==0) return P;
		if (P[0].compareTo(Q[0])==0 && (P[1].add(Q[1])).compareTo(p)==0 ) {
			ans[0]=INF;
			ans[1]=INF;
			return ans;	
		}
		
		
		if (P[0].compareTo(Q[0])==0 && P[1].compareTo(Q[1])==0) {
			FA.extendedEuclidean(TWO.multiply(P[1]), p);
			//"a^(-1)="+FA.getU().mod(p).abs();
			lam=(THREE.multiply(P[0].multiply(P[0])).add(A)).multiply(FA.getU().mod(p).abs()).mod(p);//(3 x1^2+A)/(2y1)
	
		}
		else {
			FA.extendedEuclidean(Q[0].subtract(P[0]), p);
			lam=(Q[1].subtract(P[1])).multiply(FA.getU().mod(p).abs()).mod(p);// (y2-y1)/(x2-x1)	
		}
		ans[0]=lam.multiply(lam).subtract(P[0]).subtract(Q[0]).mod(p);
		ans[1]=lam.multiply(P[0].subtract(ans[0])).subtract(P[1]).mod(p);
		return ans;
	}
	
	public BigInteger[] doubleAndAdd(BigInteger n,BigInteger[] P){
		BigInteger[] Q=new BigInteger[2];
		BigInteger[] R=new BigInteger[]{INF,INF};
		Q=P;
		
		while (n.compareTo(ZERO)==1) {
			if (n.mod(TWO).compareTo(ONE)==0 ) R=calculateAnsPoint(R,Q);
			Q=calculateAnsPoint(Q,Q);
			n=n.divide(TWO);
		}
		return R;
	}

}
