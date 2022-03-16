import sys

def imP():
    return sys.stdin.readline()

N = int(imP())
rope = []
for i in range(N):
    rope.append(int(imP()))
rope.sort(reverse=True)

for i in range(N):
    rope[i] = rope[i] * (i+1)

print(max(rope))