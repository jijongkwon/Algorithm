import sys

makdeagi = list(sys.stdin.readline().strip())
lst = []
answer = 0

for i in range(len(makdeagi)):
    if makdeagi[i] == "(" and makdeagi[i + 1] != ")":
        lst.append(1)
    elif makdeagi[i] == ")":
        if makdeagi[i-1] == "(":
            answer += len(lst)
        else :
            lst.pop()
            answer += 1
print(answer)