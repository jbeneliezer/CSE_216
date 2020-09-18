let rec pow x n =
        if n = 0 then 1
        else if n = 1 then x
        else x * (pow x (n - 1));;

pow 10 2;;
