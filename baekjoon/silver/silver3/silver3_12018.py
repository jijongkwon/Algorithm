import sys

n = list(map(int, sys.stdin.readline().split())) 
ml = [] #최소 들을 수 있는 마일리지
sum = 0 # 마일리지 합계
answer = 0 # 들을 수 있는 과목 수

for i in range(n[0]):
    sugang = list(map(int, sys.stdin.readline().split()))
    remain = sugang[1] - sugang[0]
    mileage = list(map(int, sys.stdin.readline().split()))
    if remain > 0 :
        ml.append(1)
    else: #정렬해서 remain 인덱스에 + 1한 값 넣기
        mileage.sort()
        ml.append(mileage[abs(remain)])

ml.sort()
for i in range(n[0]):
    if sum + ml[i]> n[1] :
        break
    sum += ml[i]
    answer += 1

print(answer)