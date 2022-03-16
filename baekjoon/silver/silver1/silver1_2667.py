from itertools import count
import sys

n = int(sys.stdin.readline())
zone = []
for _ in range(n):
    zone.append(list(map(int, sys.stdin.readline().strip())))
count = 0
answer = []
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def dfs(x, y):
    global count
    if x < 0 or x >= n or y < 0 or y >= n:
        return False

    if zone[x][y] == 1:
        count += 1
        zone[x][y] = 0
        for i in range(4):
            dfs(x + dx[i], y + dy[i])
        return True


for i in range(n):
    for j in range(n):
        if dfs(i, j) == True:
            answer.append(count)
            count = 0

print(len(answer))
answer.sort()
for i in range(len(answer)):
    print(answer[i])
