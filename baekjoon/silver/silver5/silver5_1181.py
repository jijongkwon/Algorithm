import sys

def imP():
    return sys.stdin.readline()

n = int(imP())
lst = []

for i in range(n):
    word = imP().strip()
    lst.append(word)

answer = sorted(list(set(lst)))
answer.sort(key=len)

for i in range(len(answer)):
    print(answer[i])