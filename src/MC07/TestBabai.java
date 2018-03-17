package MC07;

import java.math.BigDecimal;
import java.util.Scanner;

public class TestBabai {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		System.out.println("请输入维数n=");
		int n=input.nextInt();
		int i,j;
		BigDecimal[][] A = new BigDecimal[n][n+1];//因为需要做增广矩阵
		BigDecimal[] B=new BigDecimal[n];
		BigDecimal[][] t=new BigDecimal[2][n];
		
		for (i=0;i<n;i++) {
			System.out.println("请输入第"+(i+1)+"个基的坐标");
			for (j=0;j<n;j++) A[j][i]=input.nextBigDecimal();		
		}
		System.out.println("请输入任意向量w的坐标");
		for (i=0;i<n;i++) B[i]=input.nextBigDecimal();	
		Babai babai=new Babai(n,A,B);
		t=babai.solve(A,B);
		for (i=0;i<n;i++) System.out.println("t["+(i+1)+"]="+t[0][i]); 
		System.out.print("w的最近向量为v=(");
		for (i=0;i<n;i++) System.out.print(t[1][i]+" "); 
		System.out.print(")");
		
		
	}

}
