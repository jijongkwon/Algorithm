import sys


def imP():
    return sys.stdin.readline()


N = int(imP())  # 수열의 크기
num = list(map(int, imP().split()))  # 수열에 포함된 수
start = 0
end = N - 1
count = 0
if N == len(num):
    answer = int(imP())
    num.sort()
    while start < end:
        temp = num[start] + num[end]
        if temp == answer:
            count += 1

        if temp <= answer:
            start += 1
        else:
            end -= 1
    print(count)
else:
    print("errror")
