package MC07;

import java.math.BigDecimal;
import java.util.Scanner;

public class TestBabai {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		System.out.println("������ά��n=");
		int n=input.nextInt();
		int i,j;
		BigDecimal[][] A = new BigDecimal[n][n+1];//��Ϊ��Ҫ���������
		BigDecimal[] B=new BigDecimal[n];
		BigDecimal[][] t=new BigDecimal[2][n];
		
		for (i=0;i<n;i++) {
			System.out.println("�������"+(i+1)+"����������");
			for (j=0;j<n;j++) A[j][i]=input.nextBigDecimal();		
		}
		System.out.println("��������������w������");
		for (i=0;i<n;i++) B[i]=input.nextBigDecimal();	
		Babai babai=new Babai(n,A,B);
		t=babai.solve(A,B);
		for (i=0;i<n;i++) System.out.println("t["+(i+1)+"]="+t[0][i]); 
		System.out.print("w���������Ϊv=(");
		for (i=0;i<n;i++) System.out.print(t[1][i]+" "); 
		System.out.print(")");
		
		
	}

}
