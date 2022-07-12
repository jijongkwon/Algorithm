import sys


def imP():
    return sys.stdin.readline()


N = int(imP())
Solution = list(map(int, imP().split()))  # 용액
if N == len(Solution):
    Solution.sort()
    start = 0  # 시작 인덱스
    end = N-1  # 끝 인데스
    min = 10000000000000  # 0에 가까운 값

    answer = []  # 정답

    while start < end:
        s_L = Solution[start]
        s_R = Solution[end]

        sum = s_L + s_R

        if abs(sum) < min:  # 절댓값으로 비교
            min = abs(sum)
            answer = [s_L, s_R]

        if sum < 0:
            start += 1
        else:
            end -= 1

    print(answer[0], answer[1])
