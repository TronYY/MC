function X=backsub(A,b)
%Input  -A n*n上三角矩阵
%       -b n*1矩阵
%Output -X AX=b的解
 
%求b的维数
n=length(b);
%初始化X
X=zeros(n,1);
 
X(n)=b(n)/A(n,n);
for i=n-1:-1:1
   X(i)=(b(i)-A(i,i+1:n)*X(i+1:n))/A(i,i); 
end