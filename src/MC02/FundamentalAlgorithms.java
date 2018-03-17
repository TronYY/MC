package MC02;
public class FundamentalAlgorithms {
	protected int u,v;//拓展欧几里得算法中的系数
	
	public FundamentalAlgorithms() {
		
	}
	
	public int getU() {
		return u;
	}
	public int getV() {
		return v;
	}
	
	public int euclidean(int a,int b) {
		int[] r,q;
		r=new int[(int) (2*Math.log(b)/Math.log(2)+3)];
		q=new int[(int) (2*Math.log(b)/Math.log(2)+3)];
		
		r[0]=a;
		r[1]=b;
		
		int i=0;
		do {
			i++;
			q[i]=r[i-1]/r[i];
			r[i+1]=r[i-1]%r[i];
		}while (r[i+1]!=0);		
		return r[i];
	}
	
	public int extendedEuclidean(int a,int b) {
		if (b==0) {
			u=1;
			v=0;
			return a;
		}		
		int r= extendedEuclidean(b,a%b);
		int t=u;
		u=v;
		v=t-a/b*v;
		return r;
	
	}
	
	
	public long fastPowering(int g,int A,int N) {
		int r=(int) (Math.log(A)/Math.log(2));
		long[] a=new long[r+1];
		int[] b=new int[r+1];
		long[] tp=new long[r+1];
		int i;
		
		/* 2^i */
		tp[0]=1;
		for (i=1;i<=r;i++) tp[i]=2*tp[i-1];
		
		i=r;
		int G=A;
		while (G>0) {
			
			//System.out.println("Jin"+i+" "+G+" "+tp[i]);
			if (G>=tp[i]) {
				b[i]=1;
				G-=tp[i];
			}
			else b[i]=0;
			i--;
		}
		
		a[0]=g % N;
		for (i=1;i<=r;i++) a[i]=a[i-1]*a[i-1] % N;
		
		long ans=1;
		for (i=0;i<=r;i++)
			if (b[i]==1) ans=ans * a[i] % N;
		return ans;	
		
	}	
}
