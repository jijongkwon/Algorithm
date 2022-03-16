import sys
from collections import deque


def imput(): return sys.stdin.readline()


n, m = map(int, input().split())

zone = [list(map(int, input().strip())) for _ in range(n)]
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

# bfs n, m 좌표를 만날 때 return


def bfs(x, y):
    cnt = 1
    q = deque()
    q.append((x, y, cnt))

    while q:
        x, y, cnt = q.popleft()
        if x == n-1 and y == m-1:
            return cnt
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if zone[nx][ny] == 1:
                q.append((nx, ny, cnt + 1))
                zone[nx][ny] = 0


print(bfs(0, 0))
