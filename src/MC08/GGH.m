
n=input('略方n=');
V=zeros(n,n);
U=zeros(n,n);
m=zeros(1,n);
r=zeros(1,n);
e=zeros(1,n);



%Alice！！！！！！！！！！！！！！！！！！
V=input('Alice chooses a good basis V:\n');
U=input('Alice chooses an integer matrix U satisfying |U|=＼1:\n');
fprintf('Alice publishes the public key \n');
W=U*V
fprintf('\n');

%Bob！！！！！！！！！！！！！！！！！！
m=input('Bob wants to send plaintext vector m\n');
r=input('Bob chooses random small vector r\n')
e=m*W+r
fprintf('e<！！！！！！！！Bob sends ciphtertext to Alice\n');

%Alice！！！！！！！！！！！！！！！！！！
fprintf('Alice uses Babai algorithm to compute CVP of e\n');
C=Babai(e,V)
fprintf('Alice recovers the plaintext');
C*inv(W)











