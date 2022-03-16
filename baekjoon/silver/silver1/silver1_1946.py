import sys

def imP():
    return sys.stdin.readline()

testCase = int(imP()) #테스트 케이스
score = []
if 1<= testCase <= 20 :
    for _ in range(testCase):
        count = 1
        peopleNum = int(imP())
        if 1 <= peopleNum <= 100000:
            for _ in range(peopleNum): #성적 면접 합을 리스트에 추가
                personSocre = list(map(int, imP().split()))
                score.append(personSocre)
        score.sort(key=lambda x: x[0])

        max = score[0][1] # 서류 1등에 면접성적

        for i in range(1, len(score)):
            if max > score[i][1] : #순위가 크면 카운트 
                count += 1
                max = score[i][1]
        print(count)
        score.clear()