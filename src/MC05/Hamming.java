package MC05;

import java.util.ArrayList;

public class Hamming {
	
	
	
	/*校验矩阵*/
	
	public int[][] checkMatrix(int n) {
		ArrayList<Integer> array=new ArrayList<Integer>();
		int m=(int )(Math.pow(2,n)-1);
		int [][] H=new int [n][m];
		
		for (int i=0;i<n;i++) 
			for (int j=0;j<n;j++) 
				H[i][j]=0;
		
		for (int i=0;i<n;i++) 
			array.add((int )(Math.pow(2,i)));
		
		int p=0,q=0,mm=1;
		String b=new String();
		
		for (;q<m-n;) {
			b=Integer.toBinaryString(mm);
			if (!array.contains(mm)) {
				for (int j=n-1;j>=0;j--) {
					if (p==b.length()) break;
					else {
						H[j][q]=Integer.parseInt(b.substring(b.length()-(p+1),b.length()-p));
						p++;	
					}
				}
				q++;
				p=0;
					
			}
			mm++;
		}
		
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++)
				if (i==j)
					H[i][j+(int)(Math.pow(2,n)-1-n)]=1;
		}
		
		return H;
	}
	
	
	/*生成矩阵*/
	public int[][] creatMatrix(int H[][],int n){
		int i,j,m=(int)(Math.pow(2, n)-1);
		int mm=(int) (Math.pow(2, n)-1-n);
		int[][] G=new int[mm][m];
		
		for (i=0;i<mm;i++)
			for (j=0;j<mm;j++)
				if (i==j) G[i][j]=1;
		
		for (i=0;i<n;i++)
			for (j=0;j<mm;j++)
				G[j][i+mm]=H[i][j];
		return G;
				
	}
	
	
	/*Hamming码*/
	public int[] HammingCoding(int[][] g,ArrayList<Integer> array) {
		int i,j,num;
		int[] code=new int[g[1].length];
		for (i=0;i<g[1].length;i++) {
			num=0;
			for (j=0;j<g.length;j++)
				num+=array.get(j)*g[j][i];
			if ((num & 1)==1) code[i]=1;
			else code[i]=0;
		}
		return code;
	}
	
	
	/*检错纠错*/
	public void checkCorrectError(int h[][],ArrayList<Integer> array) {
		int i,j,num;
		int [] HX=new int[h.length];
		for (i=0;i<h.length;i++) {
			num=0;
			for (j=0;j<array.size();j++)
				num+=h[i][j]*array.get(j);
			if ((num & 1)==1) HX[i]=1;
			else HX[i]=0;
		}
		
		print("H*X=[");
		for (i=0;i<HX.length;i++) {
			print (HX[i]);
			if (i!=HX.length-1) print(",");
		}
		print("]\n");
		
		
		boolean flag=true;
		for (i=1;i<HX.length;i++) {
			if (HX[i]!=0) {
				flag=false;
				break;
			}
		}
		
		if (! flag) {
			int m=0;
			m=researchIndex(h,HX);
			if (m!=0) {
				print("接收的信息出错，出错位置为第"+m+"位.\n");
				print("纠错前的信息为:");
				for (i=0;i<array.size();i++)
					print(array.get(i));
				
				/*纠错*/
				
				m=m-1;
				array.set(m, array.get(m)^1);
				print("\n纠错后的信息为：");
				for (i=0;i<array.size();i++) print(array.get(i));
				print("\n");	
			}
			
		}
		else {
			print("接收的信息正确，信息为：");
			for (i=0;i<array.size();i++) print(array.get(i));
			print("\n");
		}
		
	}
	
	
	public int researchIndex(int[][] h, int[] hX) {
		int i,j,num;
		for (i=0;i<h[1].length;i++) {
			num =0;
			for (j=0;j<h.length;j++) {
				if (hX[j]==h[j][i]) num++;
			}
			
			if (num==h.length) return i+1;
		}
		return 0;
	}
	
	
	
	public void print(int str) {
		System.out.print(str);
	}
	public void print(String str) {
		System.out.print(str);
	}
	

}
