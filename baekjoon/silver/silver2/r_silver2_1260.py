import sys
from collections import deque
n, m, v = map(int, sys.stdin.readline().split())

graph = [[0] * (n + 1) for _ in range(n + 1)]
visited = [False] * (n+1)

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a][b] = graph[b][a] = 1


def dfs(v):
    visited[v] = True
    print(v, end=" ")
    for i in range(1, n+1):
        if visited[i] == False and graph[v][i] == 1:
            dfs(i)


def bfs(v):
    visited[v] = False
    q = deque()
    q.append(v)
    while q:
        v = q.popleft()  # queue
        print(v, end=" ")
        for i in range(1, n + 1):
            if visited[i] == True and graph[v][i] == 1:
                q.append(i)
                visited[i] = False


dfs(v)
print()
bfs(v)
