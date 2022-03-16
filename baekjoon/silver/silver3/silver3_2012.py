import sys

def imP():
    return sys.stdin.readline()

N = int(imP())
score = [] #점수
complaint = [] #불만도

for _ in range(N):
    score.append(int(imP()))

score.sort()
result = 0 # 불만도 합
for i in range(1,N+1):
    result += abs(i - score[i-1])

print(result)