(*Question 1 *)
type 'a binary_tree =
	| Empty
	| Node of 'a * 'a binary_tree * 'a binary_tree
;;

let rec count t =
	match t with
	| Empty -> 0
	| Node(_, Empty, Empty) -> 1
	| Node(_, left, right) -> count left + count right;;
;;

(*Question 2*)

type 'a tree =
	| Leaf of 'a
	| Tree of ('a, 'b) node
and
('a, 'b) node = {
	operator: 'b
	left: ('a, 'b) tree;
	right ('a, 'b) tree
};;

(*Question 3*)

let rec nodes_and_leaves = function
	| Leaf x ([], [x])
	| Tree {operator = p, left = x, right = y} ->
		let (left_nodes, left_leaves) = nodes_and_leaves x and
			(right_nodes, right_leaves) = nodes_and_leaves y
			in
			(p::left_nodes @ right_nodes, left_leaves @ right_leaves)
;;


