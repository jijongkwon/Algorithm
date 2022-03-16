import sys

def imP():
    return sys.stdin.readline()

people = int(imP()) #대기 사람 수
time = list(map(int, imP().split())) # 인출 하는 시간
spendTime = [] #기다리고 인출하는데 걸리는 시간


if len(time) == people: # 인원수 체크
    time.sort()
    spendTime.append(time[0]) 
    for i in range(1, len(time)): #이전 값 더하기 현재값
        spendTime.append(time[i] + spendTime[i-1])

print(sum(spendTime))

