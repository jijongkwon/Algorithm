import sys
from bisect import bisect_left as binary
input = sys.stdin.readline


def main():
    n, weight = map(int, input().split())  # 갯수와 무게
    w_lst = []  # 무게 리스트
    for i in map(int, input().split()):
        if i >= weight:
            if i == weight:  # 1개 뽑을 때
                return 1
        else:
            w_lst.append(i)
    w_lst.sort()
    last_index = w_lst[-1]
    for index, value in enumerate(w_lst):  # 인덱스, 값
        g = binary(w_lst, weight-value-last_index, lo=index+1)  # lo 부분집합
        for h, j in zip(range(g, n), w_lst[g:]):
            if weight == value+j:
                return 1
            d = binary(w_lst, weight-value-j, lo=h)
            if h == d:  # 같은거 선택할 경우
                break
            k = w_lst[d]
            if k == weight-value-j:
                return 1
    return 0


print(main())
