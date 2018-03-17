function [Z,recordK] = LLL(V)
    k=2;
    VX(1,:)=V(1,:);
    n=size(V,1);
    alpha=0.99;
    step=0;
    recordK=[];
    while k<=n
        step=step+1;
        recordK(step)=k;
        for j=k-1:-1:1
           [VX,u]=GramSchmidt(V);
           V(k,:)= V(k,:)-round(u(k,j))*V(j,:);%Size Reduction
        end
        if norm(VX(k,:))^2>=(alpha-u(k,k-1)^2)*norm(VX(k-1,:))^2   %Lovasz Condition
            k=k+1;
        else
            %swap
            t=V(k-1,:);
            V(k-1,:)=V(k,:);
            V(k,:)=t;
            %Go back
            k=max(k-1,2);
        end %if
    end %while
    Z=V;
    
end


