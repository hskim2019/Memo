# Algorithm

## [Sorting] 

#### Stable Sort
* 중복된 키를 순서대로 정렬하는 정렬 알고리즘
* 정렬 전 후 순서가 동일함을 보장
* 정렬할 때 마다 순서가 다르면 혼란스럽고 항상 안정적으로 같은 리스트가 리턴 되도록 하기 위함

#### 정렬 알고리즘 종류

**Slection Sort**
  + 처리되지 않은 데이터 중에서 가장 작은 데이터를 선택 해 맨 앞에 있는 데이터와 바꾸는 것을 반복
  + O(N^2)

**Insertion Sort**
  + 처리되지 않은 데이터를 하나씩 골라서 적합한 위치에 삽입
  + 데이터가 거의 정렬 되어있을 때 가장 빠름
  + 선택 정렬에 비해 구현 난이도가 높지만 일반적으로 더 효율적으로 동작
  + O(N^2) 
  
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
 + 옆에 있는 값과 비교해서 작은 값을 앞으로 보내는 방법
 + 구현이 간단하지만
 + 하나의 요소가 왼쪽에서 오른쪽으로 이동하기 위해서 배열의 모든 다른 요소들과 교환되기 때문에 비효율
 + O(n^2)

**Heapsort**
  + 최대힙(내림차순 정렬) 또는 최소힙(오름차순 정렬) 트리를 구성해 정렬
  + 정렬 해야 할 요소를 최대 또는 최소힙(완전 이진트리- leaf node 를 제외하고 부모노드가 자식노드를 두개씩 가지고 있는 트리) 형태로 만듦
  + 요소의 제거 : 최소힙은 루트 노드가 가장 작은 값, 최대 힙은 루트 노드가 가장 큰 값이므로 루트노드를 제거
  + O(NlogN)

**Mergesort**
**Timsort**
**Tree Sort**
**Shell Sort**
**Bucket Sort**
**Radix Sort**
**Counting Sort**
**Cubesort**
    * 정렬 알고리즘마다 예상되는 속도가 다름
    * 속도보다 공간복잡도(Space Complexity)가 고려 대상이 될 수도 있다
    * Stable 한 지 아닌지에 따라 사용되어야 하는 알고리즘이 다를 수 있다


#### Array.Sort method
* This method uses the introspective sort (introsort) algorithm as follows:  
  + If the partition size is less than or equal to 16 elements, it uses an insertion sort algorithm.  
  + If the number of partitions exceeds 2 * LogN, where N is the range of the input array, it uses a Heapsort algorithm.
  +  Otherwise, it uses a Quicksort algorithm.  
* This implementation performs an unstable sort; that is, if two elements are equal, their order might not be preserved.  
In contrast, a stable sort preserves the order of elements that are equal.  
* This method is an O(n log n) operation, where n is length.
* Quick sort(퀵 정렬) + Heap Sort(힙 정렬) + Insert sort(삽입 정렬)