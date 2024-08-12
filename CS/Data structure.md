## Array vs LinkedList

- Array 는 선언 시 size 지정되어 있어야하고
- LinkedList는 크기를 정해주지 않아도 됨. 순서가 중요
- linkedlist 는 처음부터 순차적으로 접근하며 요소를 찾아가기 때문에 시간복잡도가 O(N) 만큼이고, Array 는 random access가 가능하여 index를 통해 접근하기 때문에 시간 복잡도는 O(1)
- 삽입 및 삭제 시 인덱스로 접근 후(O(1)) 전체 배열 요소를 1씩 Shift(O(N)) 해줘야 함, LinkedList는 삽입 하려는 위치에 접근 후 삽입 및 삭제 (O(N)) [참고](https://yommi11.tistory.com/8)
  - 데이터 접근이 주 목적이면 Array
  - 데이터의 수정이 주 목적이면 LinkedList가 유리
- python 에서는 배열과 리스트를 구분하지 않는다. (파이썬의 리스트는 리스트와 배열의 특징 모두 가지도록 구현되어 있음)

|/| **Array** | **LinkedList** | 
| :---: | :--- | :---- |
| **Data Structure**| Contiguous (연속 메모리 할당)</br>Arrays are stored in contiguous location  | Non-contiguous(비연속 메모리 할당)</br>Linked lists are not stored in contiguous location</br> 메모리 공간을 자유롭게 사용할 수 있어서 메모리 낭비를 줄이는데 도움이 된다. |
| **Memory Allocation** | Fixed in size | Dynamic in size |
|                       | Memory is allocated at compile time | Memory is allocated at run time |
|                       | Use less memory than linked lists | Uese more memory because it sotred both data and the address of next node |
| **Access** | Random</br>Elements can be accessed easily | Sequential</br>Element accessing requires the traversal of whole linked list |
| **Insertion/Deletion**| Inefficient</br>Insertion and deletion operation takes time | Efficient</br>Insertion and deletion operation is faster<br>(no elements need to be shifted after insertion and deletion, Just the address needed to be updated) |

## Stack vs Queue

- Stack : Last in First out (LIFO) / First in Last Out (FILO)
- Queue : First in First out (FIFO)

### Operation on Stack
- Push : Adds an element to the top of the stack
- Pop : Removes and returns the element at the top of the stack
- Peek : Returns the element at the top of the stack without removing it
- Size : Returns the number of elements in the stack
- IsEmpty : Checks if the stack is empty
- IsFull : Checks if the stack is full (in case of fixed-size arrays)

### Application of Stack Data Structures

### Complexity Analysis of Stack Operations
- Time Complexity & Space Complexity of push(), pop()...etc : O(1)

### C# Stack with Examples
- A Stack accepts null as a valid value for reference types
- Clear : This method is used to remove all the objects from the stack
- Pop : This method removes the beginning element of the stack
- Peek : This method returns the object at the beginning of the stack without removing it
- Contains : This method returns true if the element present in the stack





### Operation on Queue
- Enqueue : Adds and element to the rear of the queue
- Dequeue : Removes and element from the front of the queue
- Peek : Retrives the front element without removing it
- IsEmpty : Checks if the queue is empty
- IsFull : Checks if the queue is full

## Tree

### Binary Tree(이진트리) BST(Binary Search Tree 이진탐색트리)

- 이진트리 - 노드 최대 차수가 2인 트리
- 이진탐색트리 - 이진트리에 아래 조건이 추가 됨
  - 루트노드의 왼쪽 자식 노드는 루트노드보다 크기가 작음
  - 루트노드 오른쪽 자식 노드는 루트노드보다 크기가 큼

## Heap - Binary heap

- 힙은 완전 이진트리인데
  - 완전이진트리: leaf node 를 제외하고 부모노드가 자식노드를 두개씩 가지고 있는 트리
- 자식 노드들이 특정한 성질을 가지고 정렬 되어 있는 구조로
  - 부모노드가 자식 노드보다 항상 작은 형태이면 최소힙
  - 부모노드가 자식 노드보다 항상 큰 형태이면 최대힙
- 최댓값 최소값을 찾아내는 연산을 빠르게 하기 위해 나온 것
  - 배열을 통해 최대/최소값을 찾으려면 O(N)이 걸리지만, 힙은 O(logN)이 걸린다
- 완전 이진트리의 일종으로 우선순위큐를 위하여 만들어진 자료구조
- 힙 트리에서는 중복된 값 허용, 이진 탐색 트리에서는 중복된 값을 허용하지 않음
- 힙을 저장하는 표준 자료구조는 배열

#### 우선순위 큐:
- 일반적인 큐는 FIFO 이지만 우선순위 큐는 들어온 순서와는 상관없이, 우선순위가 가장 크거나 작은 자료가 가장 먼저 나간다. 따라서 힙을 이용하여 가장 크거나 작은 자료를 O(1) 시간만에 찾아서 내보낼 수 있으므로 Heap을 쓰는 것이 적합하다.

- 우선순위큐는 Heap 자료구조로 구현할 수 있음
  |**자료구조**|**삭제되는 요소**|
  |:---:|:---|
  |스택| 가장 최근|
  |큐| 가장 먼저|
  |우선순위큐|가장 우선순위 높은 데이터|

  > 우선순위 큐의 이용사례

        운영체제에서의 작업 스케줄링 등
        네트워크 트래픽 제어

  > 우선순위 큐는 배열, 연결리스트, 힙으로 구현 가능한데 힙으로 구현하는 것이 가장 효율적

## Hash

- 내부적으로 배열을 사용하여 데이터를 저장하기 때문에 빠른 검색 속도를 갖는다.
- 특별한 알고리즘을 이용하여 저장할 데이터와 연관된 고유한 숫자를 만들어낸 뒤 이를 인덱스로 사용
- 적은 자원으로 많은 데이터를 효율적으로 관리하기 위해 임의의 길이 데이터를 고정된 길이의 데이터로 맵핑

### HashTable

- Key 와 Value 를 사용하여 자료를 저장
- 중복 Key 를 허용하지 않는다
- Key 와 Value 둘 다 Object 형식을 사용한다. (박싱 언박싱 발생)
- Value 가 Object 형식이기 때문에 타입에 상관없이 여러 형태 저장

```c#
  var hash = new HashTable();
  hash.Add("A", 1);
  hash.Remove("A");
  int myNum = (int)hash["A"];
```
