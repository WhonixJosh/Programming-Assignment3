# Programming-Assignment3

## Exponentiation using Arithmetic Sequencing Worst Case Efficiency

Using backwards subsitition we can see that the algorithm proposed using th artihmetic sequence provides us with a worst case of O(4^n).

Let's designate the time complexity of computing A^n as a funciton T(n). From the algorithm, we can see that A^n is computed in terms of A^(n-1), and the recursive call is made four times with A^(n-1) as the input. This means that each recursive call results in the problem size being reduced by 1, and the total number of recursive calls is 4^n.

Therefore, we can write the time complexity of the algorithm recursively as:

T(n) = 4 * T(n-1)

where T(0) = 1 (since 4^0 = 1, as given in the problem statement).

Let's use backward substitution to find the efficiency class of the algorithm:

T(n) = 4 * T(n-1)
= 4 * (4 * T(n-2)) [substituting T(n-1) in terms of T(n-2)]
= 4^2 * T(n-2)
= 4^2 * (4 * T(n-3)) [substituting T(n-2) in terms of T(n-3)]
= 4^3 * T(n-3)
= ...
= 4^i * T(n-i)

We can continue this process until we reach T(0), at which point i = n, and we obtain:

T(n) = 4^n * T(0)

Since T(0) is a constant (1, as given in the problem statement), we can drop it in our analysis of the efficiency class. Therefore, the time complexity of the algorithm is O(4^n).
