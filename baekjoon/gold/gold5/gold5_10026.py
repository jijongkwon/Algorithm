import sys


def input(): return sys.stdin.readline()


n = int(input())

graph = []
for _ in range(n):
    graph.append(input().strip())
