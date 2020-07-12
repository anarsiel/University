split(nil, X, nil, nil) :- !.
split(tree((K, V, P), L, R), X, RL, RR) :- X < K, split(L, X, RL, NL), RR = tree((K, V, P), NL, R), !.
split(tree((K, V, P), L, R), X, RL, RR) :- X >=K, split(R, X, NR, RR), RL = tree((K, V, P), L, NR), !.

merge(nil, nil, nil) :- !.
merge(R, nil, R) :- !.
merge(L, L, nil) :- !.
merge(T, tree((LK, LV, LP), LL, LR), tree((RK, RV, RP), RL, RR)) :- LP > RP, merge(NR, LR, tree((RK, RV, RP), RL, RR)), T = tree((LK, LV, LP), LL, NR), !.
merge(T, tree((LK, LV, LP), LL, LR), tree((RK, RV, RP), RL, RR)) :- LP =<RP, merge(NL, tree((LK, LV, LP), LL, LR), RL), T = tree((RK, RV, RP), NL, RR), !.

insert(nil, (K, V, P), tree((K, V, P), nil, nil)).
insert(tree((K, V, P), L, R), (NK, NV, NP), tree((NK, NV, NP), NL, NR)) :- NP > P, split(tree((K, V, P), L, R), NK, NL, NR).
insert(tree((K, V, P), L, R), (NK, NV, NP), tree((K, V, P), NN, R)) :- NP =<P, NK < K, insert(L, (NK, NV, NP), NN).
insert(tree((K, V, P), L, R), (NK, NV, NP), tree((K, V, P), L, NN)) :- NP =<P, NK >=K, insert(R, (NK, NV, NP), NN).

map_remove(nil, _, nil).
map_remove(tree((K, _, _), L, R), K, RT) :- merge(RT, L, R).
map_remove(tree((K, V, P), L, R), NK, tree((K, V, P), NN, R)) :- NK < K, map_remove(L, NK, NN).
map_remove(tree((K, V, P), L, R), NK, tree((K, V, P), L, NN)) :- NK > K, map_remove(R, NK, NN).

search(nil, _, _, 0).
search(tree((K, V, _), _, _), K, V, 1).
search(tree((K, _, _), L, _), NK, NV, E) :- NK < K, search(L, NK, NV, E).
search(tree((K, _, _), _, R), NK, NV, E) :- NK > K, search(R, NK, NV, E).
map_get(T, K, V) :- search(T, K, V, E), E == 1.

replace(nil, K, V, nil).
replace(tree((K, _, P), L, R), K, NV, tree((K, NV, P), L, R)).
replace(tree((K, V, P), L, R), NK, NV, tree((K, V, P), NL, R)) :- NK < K, replace(L, NK, NV, NL).
replace(tree((K, V, P), L, R), NK, NV, tree((K, V, P), L, NR)) :- NK > K, replace(R, NK, NV, NR).
map_put(T, K, V, R) :- rand_int(2147483647, P), search(T, K, _, E), (E == 1 -> replace(T, K, V, R); insert(T, (K, V, P), R)).

get_biggest(tree((K, V, P), L, nil), K) :- !.
get_biggest(tree((K, V, P), L, R), S) :- get_biggest(R, S).
map_floorKey(tree((K, V, P), L, R), X, S) :- split(tree((K, V, P), L, R), X, RL, RR), get_biggest(RL, S).

tree_build([], nil).
tree_build([(K, V) | L], T) :- tree_build(L, NT), map_put(NT, K, V, T).