let rec pow x n =
    if n = 0 then 1
    else if n = 1 then x
    else x * (pow x (n - 1));;

let rec float_pow x n = match n with
    | 0 -> 1.0
    | _ -> x *. (float_pow x (n - 1));;

let rec compress lst = match lst with
    | [] -> []
    | h::[] -> [h]
    | h::s::t -> if h = s then s::compress t else h::compress t;;

let l = ["h"; "h"; "e"; "d"; "d"];;

compress l;;

let rec print_list_of_strings = function
        | [] -> Printf.printf ""
        | h::t -> begin
                Printf.printf "%s", h;
                print_list_of_strings t
        end;;
       
