import sys
sys.setrecursionlimit(10**6)


def imP():
    return sys.stdin.readline()


N = int(imP())  # map size
zone = []
for _ in range(N):
    zone.append(list(map(int, imP().split())))

visited = [[False] * N for _ in range(N)]


def DFS(x, y):
    if x >= N or y >= N:
        return 0
    d = zone[x][y]
    if d == -1:
        print("HaruHaru")
        exit(0)

    if visited[x][y] == False:
        visited[x][y] = True
        DFS(x+d, y)
        DFS(x, y+d)


DFS(0, 0)
print("Hing")
