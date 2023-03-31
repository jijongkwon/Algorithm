<h3 align="center"> 
    📢 프로그래머스(뒤에있는큰수찾기) : https://school.programmers.co.kr/learn/courses/30/lessons/154539
</h3>

<br>

## 🚀 문제
정수로 이루어진 배열 numbers가 있습니다. 배열 의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수라고 합니다.
정수 배열 numbers가 매개변수로 주어질 때, 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 return 하도록 solution 함수를 완성해주세요. 단, 뒷 큰수가 존재하지 않는 원소는 -1을 담습니다.

---
## 🚦제한사항
- 4 ≤ numbers의 길이 ≤ 1,000,000
    - 1 ≤ numbers[i] ≤ 1,000,000


---

## ⌨️ 입출력예
![스크린샷 2023-03-31 오후 9.29.04.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F33%2Fy_j6d8z90kx07xjm3k3q4lmw0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_SnXuBC%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-03-31%20%EC%98%A4%ED%9B%84%209.29.04.png)

---

### 📜 기능 목록
- [x] 앞 뒤 비교 후 스택에 넣을 수 있다.
    - [x] 만약 numbers[stack.peek()] 보다 현재 값이 크다면 현재값을 정답에 넣을 수 있다.
- [x] 스택에 남아있는 index에 -1을 넣을 수 있다.
