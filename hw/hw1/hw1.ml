(*open Printf*)

(*test output*)

(*
let int_list = [1; 2; 2; 2; 2; 3; 4; 5; 4; 5; 8; 1; 2; 8; 10];;
List.iter (printf "%d ") int_list; printf "\n";;
*)

(*Question 1*)

let rec pow x n = match n with
    | 0 -> 1
    | _ -> x * (pow x (n - 1))
;;

(*test Question 1*)

(*printf "%d\n" (pow 2 10);;*)

(*Question 1 part 2*)

let rec float_pow x n = match n with
    | 0-> 1.0
    | _ -> x *. (float_pow x (n - 1))
;;

(*test Question 1 part 2*)

(*printf "%f\n" (float_pow 3.5 7);;*)

(*Question 2*)

let rec compress lst = match lst with 
    | h::(s::t) -> if h = s then compress(s::t) else h::compress(s::t)
    | _ -> lst
;;

(*test Question *)

(*List.iter (printf "%d ") (compress int_list) ; printf "\n";;*)

(*Question 3*)

let rec remove_if lst p = match lst with
    | [] -> lst
    | h::[] -> if (p h) then [] else lst
    | h::t -> if (p h) then remove_if t p else h::remove_if t p
;;

(*test Question 3*)

(*List.iter (printf "%d ") (remove_if int_list (fun x -> x mod 2 = 1)); printf "\n";;*)

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

(*test Question 4*)

(*List.iter (printf "%d ") (slice int_list 3 10) ; printf "\n";;*)

(*Question 5*)


(*Question 6*)
let rec is_prime x i = match (x mod i) with
    | 0 -> false
    | 1 -> true
    | _ -> is_prime x (i + 1)
;; 

let rec find_pair y i =
    if is_prime i 2 then (i, (y - i)) else find_pair y (i + 1)
;;

let goldbach n =
    find_pair n 2           
;;

(*test Question 6*)

(*let (x,y) = (goldbach 20) in
printf "%d %d\n" x y*)


(*Question 7*)

let rec equiv_on f g = function
    | [] -> true
    | h::t -> if f h = g h then equiv_on f g t else false
;;

(*test Question 7*)

(*
let f i = i * i;;
let g i = 3 * i;;

printf "%b\n" (equiv_on f g [3;4;5;6;7]);;
*)

(*Question 8*)

let rec pairwisefilter f = function
    | [] -> []
    | h::[] -> [h]
    | h::s::t -> (f h s)::pairwisefilter f t
;;

(*Question 9*)

let rec compute l x = match l with 
    | [] -> 0 
    | h::t -> let (a, b) = h in
        a * (pow x b) + compute t x
;; 

let polynomial lst = compute lst;;

(*test Question 9*)

(*let f = polynomial [1,2;2,0];;

printf "%d\n" (f 2);;*)
             
(*Question 10*)

let rec powerset lst = match lst with
    | [] -> [[]]
    | h::t -> let l = powerset t in
        l @ (List.map (fun x -> h::x) l)
;;

(*test Question 10*)

(*List.iter (List.iter (printf "%d ")) (powerset [1;2;3]); printf "\n" ;;*)







