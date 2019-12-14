x = c(1, 9, 9, 2, 3, 7, 9, 9, 9, 1, -12, 23, 40, 4, 23 ,4 ,-100, 8, 10, 14, 500)
mean = mean(x)
for (i in 1:length(x)){
  if (x[i] > mean*3){
    x[i] = mean
  }
}
print(x)
