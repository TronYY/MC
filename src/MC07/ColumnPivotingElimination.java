package MC07;

import java.util.Scanner;
public class ColumnPivotingElimination {
public static void main(String args[]){
    Scanner input1=new Scanner(System.in);
    Matrix M=new Matrix();
    int A[][];  
    A=M.shuru();
    int B[]=new int[A.length];
    System.out.println("请输入常系数");
    for(int i=0;i<B.length;i++){
        B[i]=input1.nextInt();
    }
    input1.close();
    for(int i=0;i<A[0].length;i++) {
        for(int j=i;j<B.length;j++) {
            if (Math.abs(A[j][i])>=Math.abs(A[i][i])) {
                int temp=0,btemp=0;
                for(int k=0;k<A[0].length;k++) {
                    temp=A[j][i];
                    A[j][i]=A[i][i];
                    A[i][i]=temp;   
                    btemp=B[j];
                    B[j]=B[i];
                    B[i]=btemp;
                }
            }
        }
    }
    for(int l=0;l<A.length;l++){//化为上三角矩阵
        for(int k=l;k<A[0].length;k++) {
             A[l][k]=A[l][k]-(A[l][k]/A[l][l])*A[l][k];
             B[l]=B[l]-(A[l][k]/A[l][l])*B[l];
        }
    }
    int n=B.length;
    int sum=0;
        B[n]=B[n]/A[n][n];
        n--;
        for(;n>=0;n--){
            for(int k=n+1;k<B.length;k++){
                sum=sum+A[n+1][k]*B[k];
            }
            B[n]=B[n]-sum/A[n][n];
        }
    int x[]=new int[B.length];
    x=B;
    System.out.println("该方程的解为："+x);
    }
}