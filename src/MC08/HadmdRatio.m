function H=HadmdRatio(V)
    s=1;
    n=size(V,1);
    for i=1:n
        s=s*norm(V(i,:)); 
    end;
    H=(abs(det(V))/s)^(1/n);
end