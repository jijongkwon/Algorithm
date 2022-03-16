import sys


def imP():
    return sys.stdin.readline()


testcase = int(imP())

for _ in range(testcase):
    N, M = map(int, imP().split())
    up = 1
    down = 1
    for i in range(N):
        up *= M - i
        down *= (i + 1)
    print(int(up/down))
