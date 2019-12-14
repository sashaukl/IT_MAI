shit_code <- function(my_list, finding_value, count) {
    list_size = length(my_list)
    res = c()
    for (i in 1:length(my_list)) {
      if (my_list[i] == finding_value){  
        if (i + count <= list_size){
          matches = TRUE
          for (j in i:(i+count-1)){  
            if (my_list[j] != finding_value){
              matches = FALSE
            }
          }
          if (matches){
            res <- c(res, i)
          }  
        }
      }
    }
    print(res)
}
shit_code(c(1, 9, 9,2,3,7,9,9,9,1), 9, 2)

