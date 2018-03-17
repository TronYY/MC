function X=backsub(A,b)
%Input  -A n*n�����Ǿ���
%       -b n*1����
%Output -X AX=b�Ľ�
 
%��b��ά��
n=length(b);
%��ʼ��X
X=zeros(n,1);
 
X(n)=b(n)/A(n,n);
for i=n-1:-1:1
   X(i)=(b(i)-A(i,i+1:n)*X(i+1:n))/A(i,i); 
end