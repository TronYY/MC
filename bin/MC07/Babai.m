function G=Babai(n,e,V)
    t=ColumnPivotingElimination(V',e');
    t=round(t');
    G=t*V;
end
