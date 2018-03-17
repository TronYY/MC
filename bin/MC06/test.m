k=0;
hold on
for x=-5:0.01:8
    y2=x^3;
    if (y2>=0)
        plot(x,sqrt(y2),x,-sqrt(y2))
    end
end
