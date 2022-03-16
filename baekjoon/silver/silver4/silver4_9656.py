import sys


def imP():
    return sys.stdin.readline()


N = int(imP())

if N % 2 == 0:
    print("SK")
else:
    print("CY")
