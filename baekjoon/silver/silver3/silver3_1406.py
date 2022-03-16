import sys

lst1 = list(sys.stdin.readline().strip())
lst2 = []
m = int(sys.stdin.readline())

for i in range(m):
    temp = sys.stdin.readline().split()
    if temp[0] == "L" and lst1 != []:
        lst2.append(lst1.pop())
        print(lst1)
        print(lst2)
    elif temp[0] == "D" and lst2 != []:
        lst1.append(lst2.pop())
        print(lst1)
        print(lst2)
    elif temp[0] == "B" and lst1 != []:
        lst1.pop()
        print(lst1)
        print(lst2)
    elif temp[0] == "P" :
        lst1.append(temp[1])
        print(lst1)
        print(lst2)

print("".join(lst1 + list(reversed(lst2))))