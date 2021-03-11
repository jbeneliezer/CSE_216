let rec add_if condition element lst = match condition with
    | true -> element::lst
    | false -> lst;;
