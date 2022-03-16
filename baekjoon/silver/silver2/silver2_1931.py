import sys

def inP(): #input 받는 함수
    return sys.stdin.readline()

n = int(inP())
meetingTime = [] # 회의시간
for i in range(n):
    temp = inP().strip().split()
    meetingTime.append([int(temp[0]),int(temp[1])])

meetingTime.sort(key=lambda x: (x[1],x[0])) #끝나는 시간을 기준으로 오름차순 정렬한 후 시작시간 오름차순 정렬

count = 0 #갯수
last = 0 #끝나는 시간

for fst, lst in meetingTime : #끝나는 시간과 앞 시간 비교해서 같거나 크면 갯수 + 1
    if fst >= last :
        count += 1
        last = lst

print(count)
