package MC07;

import java.math.BigDecimal;
public class Babai {
	protected BigDecimal[][] A;
	protected BigDecimal[] B;
	private final static BigDecimal ZERO = new BigDecimal("0");
	public Babai(int n,BigDecimal[][] a,BigDecimal[] b) {
		A=new BigDecimal[n][n];
		B=new BigDecimal[n];
		A=a.clone();//������ǳ�㸴�Ʒ����µ����ô�ŵ�ַ
		for(int i=0;i<a.length;i++)
			A[i]=a[i].clone();//a[i]ָ�����������Ϊ�������ͣ�������㸴�����������ö���
		
		
		B=b.clone();
		
	}
	
	public BigDecimal[][] solve(BigDecimal[][] A,BigDecimal[] B) {
		int i,j,k;
		int n=B.length;
		BigDecimal m;
		/*BigDecimal[][] a=new BigDecimal[n][n+1]; 
		a=A.clone();
		/*BigDecimal[] B=new BigDecimal[n];
		B=this.B.clone();*/
		BigDecimal[] X=new BigDecimal[n];//��ʼ��X
		
		for (i=0;i<n;i++) A[i][n]=B[i];
		
		for (i=0;i<n-1;i++) {
		   /*A(i,i)(i)=0,��˹��Ԫ���޷��������� ��������*/
		   if (A[i][i].compareTo(BigDecimal.ZERO)==0) {
			   System.out.println("Gaussian Elimination failed");
		       break;
		   }
		   
		   //��i����Ԫ
		   for (j=i+1;j<n;j++) {
		      m=A[j][i].divide(A[i][i],10, BigDecimal.ROUND_HALF_UP);
		   	  for (k=i;k<=n;k++)
		   		  A[j][k]=A[j][k].subtract(m.multiply(A[i][k]));
		      
		   }
		}
		
		
		//��Ԫ�����������������η��̵Ļش�������⣻
		BigDecimal[][] a=new BigDecimal[n][n];
		BigDecimal[] b=new BigDecimal[n];
		for (i=0;i<n;i++) {
			b[i]=A[i][n];
			for (j=0;j<n;j++)
				a[i][j]=A[i][j];
			
		}
		X=backsub(a,b);
		
		
		
	    BigDecimal[][] t=new BigDecimal[2][n];
	    for (j=0;j<n;j++) t[0][j]=X[j].setScale(0, BigDecimal.ROUND_HALF_EVEN);
	    
	    for (i=0;i<n;i++) {
	    	for (j=0;j<n;j++) System.out.print(this.A[i][j]+" ") ;
	    	System.out.println();
	    	
	    }
	    for (i=0;i<n;i++) {
	    	t[1][i]=ZERO;
	    	for (j=0;j<n;j++) 
	    		t[1][i]=t[1][i].add(t[0][j].multiply(this.A[i][j]));
	    	t[1][i]=t[1][i].setScale(0, BigDecimal.ROUND_HALF_EVEN);
	    		
	    }	
	    return t;
	    
	    	
		
	}
	
	
	public BigDecimal[] backsub(BigDecimal[][] A,BigDecimal[] B) {
		int n=B.length;
		
		int i,j;
		BigDecimal[] X=new BigDecimal[n];
		X[n-1]=B[n-1].divide(A[n-1][n-1],10, BigDecimal.ROUND_HALF_UP);
		for (i=n-2;i>=0;i--) {
			BigDecimal sum= new BigDecimal("0");
			for (j=i+1;j<n;j++) {
				
				sum=sum.add(A[i][j].multiply(X[j]));
			}
			X[i]=(B[i].subtract(sum)).divide(A[i][i],10, BigDecimal.ROUND_HALF_UP);	
		}
		return X;
	}
}


