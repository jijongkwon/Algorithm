import sys

n = 14
lst = []
for i in range(n):
    temp = sys.stdin.readline().split()
    if temp[0] == "push":
        lst.append(temp[1])
    elif temp[0] == "pop":
        if len(lst) == 0 :
            print(-1)
        else:
            print(lst.pop())
    elif temp[0] == "size" :
        print(len(lst))
    elif temp[0] == "empty" :
        if len(lst) == 0 :
            print(1)
        else :
            print(0)
    elif temp[0] == "top" :
        if len(lst) == 0:
            print(-1)
        else :
            print(lst[len(lst) - 1])
