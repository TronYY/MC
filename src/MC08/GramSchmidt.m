function [Z,u]= GramSchmidt(V)
%Input  -V 1*n����
%Output -Z 1*n����,���������
    [n,m]=size(V);
    VX=zeros(n,m);
    VX(1,:)=V(1,:);
    for i=2:n
        for j=1:i-1
           u(i,j)=V(i,:)*(VX(j,:)')/ (norm(VX(j,:))^2);
        end
        s=zeros(1,m);
        for j=1:i-1
            s=s+u(i,j)*VX(j,:);
        end
        VX(i,:)=V(i,:)-s;
    end
    Z=VX;
end

