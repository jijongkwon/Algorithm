import sys

def imP():
    return sys.stdin.readline()

def binary_search(start,end,note1,num): #이분탐색
    while start <= end:
        mid = (start + end) // 2
        if note1[mid] == num :
            return 1
        elif note1[mid] < num :
            start = mid + 1
        else:
            end = mid - 1
    return 0


testCase = int(imP())

for _ in range(testCase):
    N1 = int(imP())
    note1 = list(map(int, imP().split())) # 수첩1
    N2 = int(imP())
    note2 = list(map(int, imP().split())) # 수첩2
    note1.sort()
    for i in note2:
        print(binary_search(0,N1 -1, note1, i))