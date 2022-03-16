import sys


def input(): return sys.stdin.readline()


n, m = map(int, input().split())

trees = list(map(int, input().split()))

start, end = 0, max(trees)

while start <= end:
    mid = (start + end) // 2
    s_tree = 0
    for i in trees:
        if i > mid:
            s_tree += i - mid

    if s_tree >= m:
        start = mid + 1
    else:
        end = mid - 1

print(end)
