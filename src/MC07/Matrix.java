package MC07;

import java.util.Scanner;

public class Matrix{
    Matrix(){
    }
    int[][] shuru (){
        Scanner input=new Scanner(System.in);
        int m,n;
        System.out.println("������ά��");
        n=input.nextInt();
        int M[][]=new int[n][n];
        System.out.println("��������ֵ");
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[i].length;j++)
                M[i][j]=input.nextInt();
        }
        return M;
    }
}