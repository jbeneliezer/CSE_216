
(*Question 2.2*)

type math_expr =
    | Const of int
    | Var of string
    | Plus of math_expr * math_expr
    | Mult of math_expr * math_expr
    | Minus of math_expr * math_expr
    | Div of math_expr * math_expr
;;

(*Question 3*)

let rec evaluate = function
    | Const a -> a
    | Plus (a, b) -> (evaluate a) + (evaluate b)
    | Mult (a, b) -> (evaluate a) * (evaluate b)
    | Minus (a, b) -> (evaluate a) - (evaluate b)
    | Div (a, b) -> (evaluate a) / (evaluate b)
;;



