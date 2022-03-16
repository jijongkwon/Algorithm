import sys

n = int(sys.stdin.readline())

rgb = []

for i in range(n):
    rgb.append(list(map(int, sys.stdin.readline().split())))

for i in range(1, n):
    rgb[i][0] += min(rgb[i-1][1], rgb[i-1][2])  # 빨강
    rgb[i][1] += min(rgb[i-1][0], rgb[i-1][2])  # 초록
    rgb[i][2] += min(rgb[i-1][0], rgb[i-1][1])  # 파랑

print(rgb)
print(min(rgb[-1]))
