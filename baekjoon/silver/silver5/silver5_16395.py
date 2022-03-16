import sys

n, j = map(int, sys.stdin.readline().split())

pascal = [[1], [1, 1]]

for i in range(2, n):
    temp = [1] * len(pascal[i-1])
    temp.append(1)
    for k in range(1, len(temp) - 1):
        temp[k] = pascal[i-1][k-1] + pascal[i-1][k]
    pascal.append(temp)

print(pascal[n-1][j - 1])
