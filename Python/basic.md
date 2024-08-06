# 자료형

## LIST
- Array 배열로 구현되어 있음
- 각 요소가 인덱스로 접근 가능하므로 인덱스를 알면 상수 시간 O(1)에 요소 접근할 수 있다
- 인덱스를 모르면 전체 리스트를 순회 해야 하므로 선형 시간 O(N)이 걸릴 수 있다
### - List comprehension
N * M 크기의 2차원 리스트 초기화
``` python
array =[[0] * m for _ in range(n)]
# [[0,0,0], [0,0,0], [0,0,0], [0,0,0]] 
```

## TUPLE
### - 튜플을 사용하면 좋은 경우
- 서로 다른 성질의 데이터를 묶어서 관리해야 할 때
- 데이터의 나열을 Hashing 의 key 값으로 사용해야 할 때 (한 번 선언되면 변경이 불가능하므로)
- List 보다  메모리를 효율적으로 사용해야 할 때

## Dictionary
 사전자료형은 해시 테이블(Hash Table) 을 이용하므로
 데이터의 조회 및 수정에 있어서 O(1)의 시간 안에 처리

 ## SET
 - (HashTable로 구현되어 있어) 집합 자료형은 중복 허용 x, 순서가 x
 - 데이터의 조회 및 수정에 있어서 O(1)의 시간 안에 처리
 ```python
 data = set([1, 1, 2, 3, 4, 4, 5]) # {1, 2, 3, 4, 5}
 data = {1, 1, 2, 3, 4, 4, 5} # {1, 2, 3, 4, 5}
 ```

 - List & Tuple - 순서가 있기 때문에 인덱싱으로 자료형 값 얻기 가능
 - Dictionary & Set - 순서가 없기 때문에 인덱싱으로 값을 얻을 수 x <br>
     사전의 key 또는  집합의 원소(Element) 를 이용해 O(1)의 시간 복잡도로 조회 가능

***

# Memo

## 그리디 & 구현

## DFS & BFS
- Depth-Frist Search (깊이 우선 탐색) : 
    - 깊은 부분부터 우선 탐색
    - 스택 자료구조(재귀함수) 이용
- Breadth-First Search (너비 우선 탐색) : 
    - 가까운 노드부터 우선 탐색
    - 큐 자료구조 이용

### stack
```py
stack =[]
stack.append(1)
stack.pop()
stack[::-1]
```

## queue
``` py
from collections import deque
queue = deque()
queue.append(5)
queue.popleft()
queue.reverse()
```

