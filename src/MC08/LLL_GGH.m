function [Vq,V,m] = LLL_GGH(W,e)
    [Vq,recordK] = LLL(W);
    V=Babai(e,Vq);
    m=V*inv(W);
end

