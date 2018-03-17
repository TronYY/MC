package MC05;

import java.util.ArrayList;
import java.util.Scanner;

public class TestHamming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hamming HM=new Hamming();
		
		ArrayList<Integer> array1=new ArrayList<Integer>();
		ArrayList<Integer> array2=new ArrayList<Integer>();
		
		int in,k;
		
		int[][] H;//У�����
		int[][] G;//���ɾ���
		String input;
		boolean flag1=true,flag2=true,flag3=true,flag4=true;
		while (1==1) {
			k=0;
			array1.clear();
			array2.clear();
			HM.print("����? Y/N:");
			Scanner s=new Scanner(System.in);
			input=s.next();
			
			if (input.equals("N")) break;
			HM.print("�����������Ķ������ַ���,��2����");
			in=s.nextInt();
			if (!(in==1 || in ==0)) HM.print("�����������������\n");
			while (in!=2 &&(in==1||in==0)) {
				array1.add(in);
				in=s.nextInt();
				if ((in==1 || in ==0 || in==2)==false)
					flag1=false;
			}
			
			if (!flag1) HM.print("�����������������\n");	
			
			for (int i=0;;i++) {
				if (Math.pow(2,k)-k==array1.size()+1) {
					flag3=true;
					break;
				}
				if (Math.pow(2,k)-k>array1.size()+1) {
					flag3=true;
					break;
				} 
				k++;
			}
			
			if (flag3==false) HM.print("�ַ����������������������.\n");
			
			
			
			if (flag3) {
				H=HM.checkMatrix(k);
				G=HM.creatMatrix(H, k);
				
				HM.print("У�����Ϊ��\n");
				
				for (int i=0;i<H.length;i++) {
					for (int j=0;j<H[i].length;j++)
						System.out.print(" "+H[i][j]);
					System.out.println();
					
				}
				
				
				HM.print("���ɾ���Ϊ��\n");
				
				for (int i=0;i<G.length;i++) {
					for (int j=0;j<G[i].length;j++)
						System.out.print(" "+G[i][j]);
					System.out.println();
				}
				
				HM.print("Hamming����Ϊ\n");
				int[] code=HM.HammingCoding(G, array1);
				for (int i=0;i<code.length;i++)
					HM.print(code[i]+" ");
				
				int mm=(int)(Math.pow(2, k)-1);
				while (flag4) {
					HM.print("\n������"+mm+"λ���շ����յ��ַ���");
					for (int i=0;i<mm;i++) {
						in=s.nextInt();
						if ((in==1 ||  in==0)==false) {
							HM.print("�����������������");
							break;
						}
						
						else {
							if (i==mm-1) flag4=false;
							array2.add(in);
						}
					}
				}
				flag4=true;
				HM.checkCorrectError(H, array2);	
			}
		}
	}
		
}


