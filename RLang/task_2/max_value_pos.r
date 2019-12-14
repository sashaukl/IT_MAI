N_ROW = 3
x <- c(24, 52, 23, 
  52, -23, 623, 
  623, 123, 124,
  1, 1, 1)
x <- matrix(x, nrow=3,byrow=TRUE)
max = min(x)
col = 0
row = 0
for (i in 1:dim(x)[1]){
  for (j in 1:dim(x)[2]){
    if (x[i, j] >= max){
      col = i
      row = j
      max = x[i, j]
    }
  }
}
print(max) #max value
print(col) #col value
print(row) #row value 
