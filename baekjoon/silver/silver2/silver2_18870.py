import sys


def imP():
    return sys.stdin.readline()


N = int(imP())  # 받을 수
arr = list(map(int, imP().split()))  # x1..~ xn

arr2 = sorted(list(set(arr)))  # 중복 제거, 오름차순
dic = {arr2[i]: i for i in range(len(arr2))}
for i in arr:
    print(dic[i], end=" ")
