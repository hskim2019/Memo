# Algorithm

## [Sorting]

#### Stable Sort

- 중복된 키를 순서대로 정렬하는 정렬 알고리즘
- 정렬 전 후 순서가 동일함을 보장
- 정렬할 때 마다 순서가 다르면 혼란스럽고 항상 안정적으로 같은 리스트가 리턴 되도록 하기 위함
- Insertion Sort
- Merge Sort
- Bubble Sort
- Counting Sort

#### Unstable Sorting

- 추가적인 메모리 공간이 거의(아예가 아니다) 안 드는 정렬
- Selection Sort
- Heap Sort
- Shell Sort

#### In-place Sorting

- Insertion Sort
- Selection Sort
- Bubble Sort
- Shell Sort
- Help Sort
- Quick Sort

#### Not In Place Sorting

- In-place하지 않은 알고리즘은 n 길이의 리스트를 정렬할 때 n 만큼의 메모리보다 더 많은 메모리 공간을 할당한다. 즉, 이런 알고리즘들은 space complexity가 높다.
- Merge Sort
- Counting Sort
- Radix Sort
- Bucket Sort

#### 정렬 알고리즘 종류

**Slection Sort**

- 처리되지 않은 데이터 중에서 가장 작은 데이터를 선택 해 맨 앞에 있는 데이터와 바꾸는 것을 반복
- O(N^2)

**Insertion Sort**

- 처리되지 않은 데이터를 하나씩 골라서 적합한 위치에 삽입
- 데이터가 거의 정렬 되어있을 때 가장 빠름
- 선택 정렬에 비해 구현 난이도가 높지만 일반적으로 더 효율적으로 동작
- O(N^2)

**Quick sort**

- 기준 데이터를 설정하고 그 기준보다 큰 데이터와 작은 데이터의 위치를 바꾸는 방법
- 배열 가운데 하나의 원소를 고르고 기준 데이터(Pivot)로 설정
- 피벗을 기준으로 데이터 묶음을 나누는 분할 작업이 일어나면 다시 왼쪽과 오른쪽 각각 정렬
- 퀵정렬이 빠른 이유 – 이상적인 분할이 절반씩 일어나면 전체 연산 횟수로 O(NlogN) 을 기대할 수 있음
- 피벗값을 어떤 것으로 설정하느냐에 따라서 시간 복잡도 달라짐
- 첫번째 원소를 피벗으로 삼을 때, 이미 정렬된 배열에 대해 퀵 정렬을 수행하는 경우  
   분할 시 오른쪽만 남게 되고 분할을 위해 매번 선형탐색을 하게 되므로 O(N^2) 시간 복잡도를 가지게 됨  
   표준 라이브러리로 퀵정렬 이용 시 O(NlogN) 시간복잡도를 보장함

**Bubble sort**

- 옆에 있는 값과 비교해서 작은 값을 앞으로 보내는 방법
- 구현이 간단하지만
- 하나의 요소가 왼쪽에서 오른쪽으로 이동하기 위해서 배열의 모든 다른 요소들과 교환되기 때문에 비효율
- O(n^2)

**Heapsort**

- 최대힙(내림차순 정렬) 또는 최소힙(오름차순 정렬) 트리를 구성해 정렬
- 정렬 해야 할 요소를 최대 또는 최소힙(완전 이진트리- leaf node 를 제외하고 부모노드가 자식노드를 두개씩 가지고 있는 트리) 형태로 만듦
- 요소의 제거 : 최소힙은 루트 노드가 가장 작은 값, 최대 힙은 루트 노드가 가장 큰 값이므로 루트노드를 제거
- O(NlogN)

**Mergesort**
**Timsort**
**Tree Sort**
**Shell Sort**
**Bucket Sort**
**Radix Sort**
**Counting Sort**
**Cubesort**
_ 정렬 알고리즘마다 예상되는 속도가 다름
_ 속도보다 공간복잡도(Space Complexity)가 고려 대상이 될 수도 있다 \* Stable 한 지 아닌지에 따라 사용되어야 하는 알고리즘이 다를 수 있다

#### Array.Sort method

- This method uses the introspective sort (introsort) algorithm as follows:
  - If the partition size is less than or equal to 16 elements, it uses an insertion sort algorithm.
  - If the number of partitions exceeds 2 \* LogN, where N is the range of the input array, it uses a Heapsort algorithm.
  - Otherwise, it uses a Quicksort algorithm.
- This implementation performs an unstable sort; that is, if two elements are equal, their order might not be preserved.  
  In contrast, a stable sort preserves the order of elements that are equal.
- This method is an O(n log n) operation, where n is length.
- Quick sort(퀵 정렬) + Heap Sort(힙 정렬) + Insert sort(삽입 정렬)

#### Big O

##### 참고 링크

- [시간복잡도 표와 영문표기](https://dingrr.com/blog/post/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%EC%8B%9C%EA%B0%84%EB%B3%B5%EC%9E%A1%EB%8F%84-big-o)

```
시간복잡도 단계 (빠른순으로)
[fast] O(1) < O(log n) < O(n) < O(n*log n) < O(n^2) < O(2^n) < O(n!)

O(1) – 상수 시간 : 문제를 해결하는데 오직 한 단계만 처리함.
O(log n) – 로그 시간 : 문제를 해결하는데 필요한 단계들이 연산마다 특정 요인에 의해 줄어듬. 이진트리탐색. n 을 2로 몇 번 나누어야 1을 만들 수 있는지
O(n) – 직선적 시간 : 문제를 해결하기 위한 단계의 수와 입력값 n이 1:1 관계를 가짐.
O(n log n) - 선형로그형:  문제를 해결하기 위한 단계의 수가 N*(log2N) 번만큼의 수행시간을 가진다. 퀵정렬. 머지정렬은 리스트를 반으로 쪼개고 다시 쪼개서 작게 만들어 작은 수 부터 정렬. 쪼개면서 log n 시간을 소요하고 다시 붙일 때 n 시간 소요
O(n^2) – 2차 시간 : 문제를 해결하기 위한 단계의 수는 입력값 n의 제곱. 버블정렬, 삽입정렬
O(n!) - 완전탐색 무작위 대입 - 시간이 기하급수적으로 늘어나는 것을 이용하여 암호화 알고리즘에 사용할 수 있다. 무작위 대입 공격에 큰 시간과 비용이 들게 만듦
```
