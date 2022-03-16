import sys

zero = [1, 0, 1]
one = [0, 1, 1]
tc = int(sys.stdin.readline())

for _ in range(tc):
    n = int(sys.stdin.readline())
    length = len(zero)
    if length <= n:
        for i in range(length, n + 1):
            zero.append(zero[i-1] + zero[i-2])
            one.append(one[i-1] + one[i-2])
    print("{} {}".format(zero[n], one[n]))
