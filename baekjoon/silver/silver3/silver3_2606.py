import sys
from collections import deque
n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

visited = [False] * (n + 1)
graph = [[0] * (n + 1) for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a][b] = graph[b][a] = 1


def bfs():
    count = -1
    visited[1] = True
    q = deque()
    q.append(1)
    while q:
        count += 1
        v = q.popleft()
        for i in range(1, n+1):
            if visited[i] == False and graph[v][i] == 1:
                q.append(i)
                visited[i] = True
    return count


print(bfs())
