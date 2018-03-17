function X=ColumnPivotingElimination(A,b)
%Input  -A n*n����
%       -b n*1����
%Output -X AX=b�Ľ�
 
%��b��ά��
n=length(b);
%��ʼ��X
X=zeros(n,1);
 
%������ʹ��
T=zeros(1,n+1);
 
%(A|b)���������
Aug=[A,b];
for i=1:n-1
    %�ҳ���ǰ��ʣ��Ԫ���о���ֵ����
    [y,k]=max(abs(Aug(i:n,i)));
    
    %�н���
    T=Aug(i,:);
    Aug(i,:)=Aug(i+k-1,:);
    Aug(i+k-1,:)=T;
    
    %��δ�ܱ�֤AΪ������ʱ���п��ܳ���ͨ��������Aug(i,i)��Ȼ��0���������ʱ�޷����У�����Ԫ��ȥ��ʧ��
    if Aug(i,i)==0
        fprintf('A(%d,%d)(%d)=0, Column Pivoting Elimination failed.\n',i,i,i);
       break;
   end
    
   %��i����Ԫ
   for j=i+1:n
       Aug(j,i)=Aug(j,i)/Aug(i,i);
       Aug(j,i:n+1)=Aug(j,i:n+1)-Aug(j,i)*Aug(i,i:n+1);
   end
    
end
%��Ԫ�����������������η��̵Ļش�������⣻
X=backsub(triu(Aug(1:n,1:n)),Aug(1:n,n+1));