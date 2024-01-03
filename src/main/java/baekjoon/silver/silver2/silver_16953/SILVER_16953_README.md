<h3 align="center"> 
    📌 백준(A->B) : https://www.acmicpc.net/problem/16953
</h3>

<br>

## 🚀 문제
정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.

- 2를 곱한다.
- 1을 수의 가장 오른쪽에 추가한다.
A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.



---

## ⌨️ 입력
첫째 줄에 A, B (1 ≤ A < B ≤ 109)가 주어진다.


---

## 🖥️ 출력
A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력한다. 만들 수 없는 경우에는 -1을 출력한다.

---

### 📜 기능 목록
- [x] 알파벳의 자리수만큼 곱하고 동일한 알파벳이 있으면 자리수 곱셉한 것을 더한다.
- [x] 정렬한 후 큰 수부터 내림차순으로 곱한다.