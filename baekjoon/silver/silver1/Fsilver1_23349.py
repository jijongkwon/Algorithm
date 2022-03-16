import sys


def imP():
    return sys.stdin.readline()


def getKey(val):
    for key, value in count.items():
        if val == value:
            return key


N = int(imP())
place_time = []  # 장소 시간 순서쌍
name = []  # 이름 목록
time1 = []  # 첫번째 시간

for _ in range(N):  # 이름 중복이 있으면 첫번째 값만 인정
    temp = list(imP().strip().split())
    if temp[0] not in name:
        place_time.append(temp[1:])
        name.append(temp[0])

place_time.sort()  # 사전순 정렬


count = {}  # 갯수 찾기
for i in place_time:
    try:
        count[i[0]] += 1
    except:
        count[i[0]] = 1

maxValue = max(count.values())  # 갯수가 많은 값

print(count)
