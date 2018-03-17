function X=ColumnPivotingElimination(A,b)
%Input  -A n*n矩阵
%       -b n*1矩阵
%Output -X AX=b的解
 
%求b的维数
n=length(b);
%初始化X
X=zeros(n,1);
 
%做交换使用
T=zeros(1,n+1);
 
%(A|b)的增光矩阵
Aug=[A,b];
for i=1:n-1
    %找出当前列剩余元素中绝对值最大的
    [y,k]=max(abs(Aug(i:n,i)));
    
    %行交换
    T=Aug(i,:);
    Aug(i,:)=Aug(i+k-1,:);
    Aug(i+k-1,:)=T;
    
    %当未能保证A为非奇异时，有可能出现通过交换后Aug(i,i)依然是0的情况，此时无法进行，列主元消去法失败
    if Aug(i,i)==0
        fprintf('A(%d,%d)(%d)=0, Column Pivoting Elimination failed.\n',i,i,i);
       break;
   end
    
   %第i列消元
   for j=i+1:n
       Aug(j,i)=Aug(j,i)/Aug(i,i);
       Aug(j,i:n+1)=Aug(j,i:n+1)-Aug(j,i)*Aug(i,i:n+1);
   end
    
end
%消元结束后，利用上三角形方程的回代过程求解；
X=backsub(triu(Aug(1:n,1:n)),Aug(1:n,n+1));