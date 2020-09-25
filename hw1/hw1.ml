open Printf

let int_list = [1; 2; 2; 2; 2; 3; 4; 5; 4; 5; 8; 1; 2; 8; 10];;
List.iter (printf "%d ") int_list; printf "\n";;

(*Question 1*)

let rec pow x n = match n with
    | 0 -> 1
    | _ -> x * (pow x (n - 1))
;;

printf "%d\n" (pow 2 10);;

(*Question 1 part 2*)

let rec float_pow x n = match n with
    | 0-> 1.0
    | _ -> x *. (float_pow x (n - 1))
;;

printf "%f\n" (float_pow 3.5 7);;

(*Question 2*)

let rec compress = function 
    | h::(s::t) -> if h = s then compress(s::t) else h::compress(s::t)
    | _ -> lst
;;

List.iter (printf "%d ") (compress int_list) ; printf "\n";;

(*Question 3*)

let rec remove_if lst p = match lst with
    | [] -> lst
    | h::[] -> if (p h) then [] else lst
    | h::t -> if (p h) then remove_if t p else h::remove_if t p
;;

List.iter (printf "%d ") (remove_if int_list (fun x -> x mod 2 = 1)); printf "\n";;

(*Question 4*)

let rec slice lst i j = match lst with 
    | [] -> [] 
    | h::t -> if i = 0 then begin
            if j < 1 then []
            else if j = 1 then [h]
            else h::slice t 0 (j - 1)
    end
        else slice t (i - 1) (j - 1)
;;

List.iter (printf "%d ") (slice int_list 3 10) ; printf "\n";;

(*Question 5*)

let rec equivs f lst = match lst with

(*Question 6*)

let is_prime n i = match (n mod i) with
    | 0 -> false
    | 1 -> true
    | _ -> is_prime(n mod (i + 1))
;;

let goldbach n =
    let rec find_pair x a b = 
        

