import sys

def imP():
    return sys.stdin.readline()

testcase = int(imP())
for _ in range(testcase):
    phone_list = [] # 폰 리스트
    phone = int(imP())
    cnt = 0
    for _ in range(phone):
        phoneNum = imP().strip()
        phone_list.append(phoneNum)
    phone_list.sort() # 정렬해서 앞 뒤만 비교하려고
    for i in range(0, len(phone_list) - 1):
        temp = phone_list[i + 1]
        
        if phone_list[i] == temp[:len(phone_list[i])]: # 숫자가 같으면 체크
            cnt += 1
    if cnt > 0 :
        print("NO")
    else:
        print("YES")
    

