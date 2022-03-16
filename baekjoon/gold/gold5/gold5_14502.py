from collections import deque
import sys
import copy
from itertools import combinations, product

n, m = map(int, sys.stdin.readline().split())

zone = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

answer = 0

com1 = [i for i in range(n)]
com2 = [i for i in range(m)]

p = list(product(*[com1, com2]))


def wall(cnt):
    if cnt == 3:
        bfs()
        return
    for i, j in p:
        if zone[i][j] == 0:
            zone[i][j] = 1
            wall(cnt + 1)
            zone[i][j] = 0


def bfs():
    q = deque()
    tmp = copy.deepcopy(zone)

    for i in range(n):
        for j in range(m):
            if tmp[i][j] == 2:
                q.append((i, j))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if tmp[nx][ny] == 0:
                tmp[nx][ny] = 2
                q.append((nx, ny))
    global answer
    cnt0 = 0

    for i in range(n):
        cnt0 += tmp[i].count(0)

    answer = max(answer, cnt0)


wall(0)
print(answer)
