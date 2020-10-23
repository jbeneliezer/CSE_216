
(* code from problem 2 of the homework*)

type bool_expr = 
    | Lit of string
    | Not of bool_expr
    | And of bool_expr * bool_expr
    | Or of bool_expr * bool_expr
;;

(*Question 1*)

let truth_table a b expr =
    let rec t a b = function
        | Or (x, y) -> (t a b x) || (t a b y)
        | And (x, y) -> (t a b x) && (t a b y)
        | Not x -> not (t a b x) 
        | Lit x -> if x = "a" then a else b 
    in [(false,false, (t false false expr));(false,true,(t false true expr));(true,false,(t true false expr));(true,true,(t true true expr))]
;;


