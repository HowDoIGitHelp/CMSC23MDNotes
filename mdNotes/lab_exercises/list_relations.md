# Lab Exercise 5 (List Relations)

Create a knowledge base that models the following predicates/logical relations involving lists:

- `emptylist(L)` - This predicate is true if and only if `L` is an empty list.
- `list_last(L, E)` - This relation is true if and only if and only if `E` is the last element in `L`.
- `list_member(L, E)` - This relation is true if and only if `E` is and element of `L`.
- `list_sum(L, N)` - This relation is true if and only if the sum of the elements in `L` is `N`. Assume that the elements of `L` are all integers.
- `list_upperbound(L, N)` - This relation is true if and only if the integer `N` is greater than or equal to the elements in `L`. Assume that the elements of `L` are all integers.
- `list_max(L, N)` - This relation is true if and only if `N` is the maximum value in `L`. Assume that the elements of `L` are all integers. An empty list is upperbounded by any integer.
- `list_subset(L, M)` - This relation is true if and only if all the elements in list `M` are also members of list `L`. An empty list is a subset of any list.

Feel free to change the variable names in the predicates/logical relations to accommodate your definitions.
