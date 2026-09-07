# Enterprise RAG for .NET Developers

## 1. 주요 개념 정리

### LLM (Large Language Model)

LLM은 질문을 이해하고 추론하며 답변을 생성하는 AI 모델이다.

예시:

- GPT-4o
- Claude
- Gemini
- Gemma
- Llama
- Qwen
- Mistral

LLM은 일반적인 지식을 알고 있지만 회사 내부 데이터는 알지 못한다.

예를 들어:

- 회사 전자결재 문서
- 회의록
- 업무 매뉴얼
- 고객정보

는 직접 학습되지 않았으므로 알 수 없다.

---

### Vector DB

Vector DB는 문서를 의미 기반으로 검색하기 위해 사용하는 데이터베이스이다.

예시:

- Pinecone
- Milvus
- Qdrant
- Weaviate
- Azure AI Search

관계형 DB와 차이점:

#### Oracle / MSSQL

원본 데이터 저장

```text
전자결재
게시판
메일
조직도
```

#### Vector DB

검색용 인덱스 저장

```text
임베딩 벡터
문서 Chunk
메타데이터
```

---

### RAG (Retrieval-Augmented Generation)

RAG는 검색(Retrieve)과 생성(Generate)을 결합한 아키텍처이다.

```text
질문

↓

문서 검색

↓

검색결과 제공

↓

LLM 답변 생성

↓

사용자
```

즉,

```text
RAG = Retrieval + Generation
```

이다.

---

## 2. RAG 아키텍처

### LLM만 사용하는 경우

```text
사용자

↓

GPT

↓

답변
```

문제점:

- 회사 문서를 모름
- 사내 업무 지식을 모름

---

### RAG 사용하는 경우

```text
사용자

↓

질문

↓

Vector DB 검색

↓

관련 문서

↓

LLM

↓

답변
```

장점:

- 회사 내부 문서 활용 가능
- 답변 정확도 향상
- 환각(Hallucination) 감소

---

## 3. 기업용 RAG 구조

```text
Oracle
MSSQL
SAP
전자결재
SharePoint

      ↓

문서 추출

      ↓

Chunking

      ↓

Embedding

      ↓

Vector DB

      ↓

Retriever

      ↓

LLM

      ↓

답변
```

---

## 4. 문서 적재(Indexing) 과정

### 문서 저장

사용자가 전자결재 작성

```text
전자결재

↓

Oracle 저장
```

---

### 인덱서(Indexer)

별도 프로그램이 수행

```text
Oracle

↓

문서 조회

↓

Chunking

↓

Embedding

↓

Vector DB 저장
```

---

### 신규 문서 처리

일반적으로:

```sql
SELECT *
FROM approval_document
WHERE embedding_yn = 'N'
```

조회

↓

임베딩

↓

Vector DB 저장

↓

상태 업데이트

---

## 5. 구성요소별 역할

### Oracle / MSSQL

원본 저장소

```text
전자결재
게시판
메일
조직도
```

---

### Indexer

문서를 읽고 임베딩하여 Vector DB에 저장

주요 작업:

```text
문서 조회

↓

Chunking

↓

Embedding

↓

Vector DB 저장
```

---

### Retriever

질문과 관련된 문서 검색

```text
질문

↓

Vector Search

↓

관련 문서 반환
```

---

### LLM

답변 생성

```text
문서

↓

추론

↓

답변 생성
```

---

## 6. Python AI 스택

### FastAPI

Python Web API Framework

.NET 비교:

```text
ASP.NET Core ↔ FastAPI
```

역할:

```text
REST API 제공

/chat

/search

/rag
```

---

### LangChain

LLM 활용을 쉽게 만들어주는 라이브러리

기능:

- Prompt
- Retriever
- Output Parser
- Document Loader
- RAG

---

### LangGraph

Agent Workflow 구성 라이브러리

기능:

```text
검색

↓

평가

↓

재검색

↓

답변 생성
```

Node와 Graph 기반으로 워크플로우를 구성한다.

---

## 7. Ollama란?

Ollama는 LLM 모델이 아니다.

Ollama는 모델 실행기(Runtime)이다.

예:

```text
Ollama

↓

Gemma

Llama

Qwen
```

비유:

```text
.NET Runtime

↓

C# 프로그램
```

---

## 8. LLM 모델 선택

### 클라우드 사용 가능

| 모델 | 특징 |
|--------|--------|
| GPT-4o | 성능 우수 |
| Claude Sonnet | 문서 요약 강점 |
| Gemini | 긴 컨텍스트 지원 |

---

### On-Premise

| 모델 | 특징 |
|--------|--------|
| Gemma 3 | Google 공개 모델 |
| Llama 3 | Meta 공개 모델 |
| Qwen 3 | Alibaba 공개 모델 |
| Mistral | 유럽계 공개 모델 |
| DeepSeek | 추론 성능 우수 |

---

## 9. LLM 실행 엔진

| 엔진 | 용도 |
|---------|---------|
| Ollama | 개발 및 소규모 운영 |
| vLLM | 엔터프라이즈 운영 |
| HuggingFace Transformers | 연구개발 |
| llama.cpp | 저사양 장비 |
| LM Studio | 개인 테스트 |

추천:

### 학습

```text
Ollama
```

### 운영

```text
vLLM
```

---

## 10. Vector DB 선택

### 클라우드

| 제품 | 특징 |
|--------|--------|
| Pinecone | 대표 SaaS |
| Azure AI Search | Azure 친화적 |
| Weaviate Cloud | 관리 편리 |

---

### On-Premise

| 제품 | 특징 |
|--------|--------|
| Milvus | 엔터프라이즈 |
| Qdrant | 설치 간편 |
| Weaviate | 기능 풍부 |
| Elasticsearch Vector | 기존 ES 활용 |

추천:

```text
Milvus
Qdrant
```

---

## 11. On-Premise AI 아키텍처

```text
사용자

↓

그룹웨어(.NET)

↓

FastAPI

↓

LangGraph

↓

Retriever

↓

Milvus

↓

Ollama

↓

Gemma / Qwen / Llama

↓

답변
```

---

## 12. 클라우드 활용 AI 아키텍처

```text
사용자

↓

그룹웨어(.NET)

↓

FastAPI

↓

LangGraph

↓

Retriever

↓

Azure AI Search

↓

GPT-4o

↓

답변
```

---

## 13. .NET 관점 비교표

| .NET | AI/Python |
|--------|--------|
| ASP.NET Core | FastAPI |
| Controller | API Endpoint |
| Workflow Foundation | LangGraph |
| EF / Dapper | LangChain |
| Oracle/MSSQL | Vector DB |
| .NET Runtime | Ollama |
| C# Application | Gemma/Llama/Qwen |
| HttpClient | httpx / requests |

---

## 14. 학습 순서 추천

1. Python 기초
2. FastAPI
3. Embedding
4. Vector DB (Milvus 또는 Qdrant)
5. RAG
6. LangChain
7. Ollama
8. LangGraph
9. vLLM
10. On-Premise LLM 운영

---

## 핵심 한 줄 요약

```text
RAG = 검색 + 답변생성

LLM = 두뇌

Vector DB = 문서 검색 창고

LangChain = LLM 활용 라이브러리

LangGraph = Agent 워크플로우 엔진

FastAPI = Python API 서버

Ollama = LLM 실행기

Gemma/Llama/Qwen = 실제 LLM 모델
```
