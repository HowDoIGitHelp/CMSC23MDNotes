is_ancestor(Parent, Child) :-
    is_parent(Parent, Child).
is_ancestor(Ancestor, Descendant) :-
    is_parent(Parent, Descendant),
    is_ancestor(Ancestor, Parent).

is_parent(juan, francisco).
is_parent(cirila, francisco).
is_parent(teodora, jose).
is_parent(francisco, jose).
is_parent(brigida, teodora).
is_parent(lorenzo, teodora).
