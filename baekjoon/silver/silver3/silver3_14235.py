import sys

n = int(sys.stdin.readline())
lst = [] # 선물 주머니
for i in range(n):
    a = list(sys.stdin.readline().strip().split()) # 갯수 와 가치
    if a[0] == "0":
        if len(lst) != 0 :
            print(lst.pop())
        else :
            print(-1)
    else :
        for k in range(1,int(a[0]) + 1):
            lst.append
