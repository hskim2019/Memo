Ollama는 LLM 모델 자체가 아니라
LLM 모델을 실행시키기 위한 온프레미스 런타임이다. (Ollama = 모델을 메모리에 올려 실행하는 프로그램)
Gemma는 Google이 공개한 LLM 모델이며,
Ollama를 통해 모델 파일을 다운로드 받아
내 PC에서 직접 실행된다.
이후에는 내 PC의 메모리와 CPU/GPU에서 직접 실행된다.
따라서 외부 서버 연결 없이도 질문에 대한 답변 생성이 가능하다.
LangChain은 Python 코드에서 이 LLM에게
질문을 던지고 답변을 받도록 도와주는 프레임워크이다.

“그런데 Gemma가 모르는 최신 정보는 어떻게 보완하지?”

→ 그게 바로 RAG 입니다.


### 1. Ollama (런타임) 설치
1. Ollama (런타임) 설치
- LLM을 실행할 수 있는 엔진
- LLM 모델을 로컬 PC에서 실행하기 위한 런타임(Runtime)
- Ollama.com 에서 직접 다운로드 및 설치

### 2. Gemma LLM 모델 다운로드
- Ollama.com 에서 제공하는 여러 LLM 모델 중, 로컬 PC에서 실행 가능하며 회사 사용 pc 에서도 설치 가능한 모델인 Gemma 계열 모델 선택
- 그 중에서 Gemma 계열 소형 모델 gemma:2b 설치
- Ollama 명령을 통해 모델 실행 또는 최초 사용 시 Gemma 모델 파일이 자동으로 다운로드 됨
- Gemma LLM 모델은 Ollama에 의해 별도의 디렉터리에 다운로드된다.
- 경로 : Windows: C:\Users\사용자이름\.ollama\
- 이 디렉터리에는 수 GB 크기의 모델 가중치 파일이 저장되며, 질문 시 해당 파일이 메모리(RAM/GPU)에 로드되어 로컬에서 직접 추론(inference)이 수행된다.
```cmd
ollama run gemma4:e2b
```
### 3. 파이썬 가상환경 설치
- 학습을 위해 생성한 폴더에서만 사용할 가상환경 설정
```cmd
python -m venv venv
venv\Scripts\activate
```
### 4. Python <-> Ollama 연결용 라이브러리 설치
- ipynb 파일에서 langchain-ollama 설치
- 이 과정에서 ipykernel 설치
- Jupyter Notebook (ipynb) 셀에서 실행
``` python
%pip install -q langchain-ollama
```


[당신]
"프랑스의 수도는 어디인가?"

  ↓ (Python 코드)

[LangChain]
- 프롬프트를 LLM 호출 형태로 정리, 질문을 LLM에게 보낼 형태로 정리
 
 ↓

[ollama Python 라이브러리]
  ↓ (API 호출)

[Ollama 런타임]  ← 실행 환경
- 로컬에서 실행 중인 LLM 프로세스에게 전달

  ↓

[Gemma 모델 파일] ← 로컬 디스크

  ↓ 

[RAM / GPU] ← 실제 연산 (메모리에서 연산)

  ↓

[Gemma]
- 다음에 올 단어들을 확률적으로 계산
- 문장 생성

  ↓

"프랑스의 수도는 파리입니다"



### 로컬 LLM 과 클라우드 LLM 차이
Gemma는 로컬 실행형 LLM이므로 최초 실행 시 모델 파일을 다운로드해야 하며
이를 위해 Ollama의 run 과정이 필요하다.
반면 GPT‑4o는 OpenAI 서버에서 이미 실행 중인 클라우드 기반 LLM이므로
별도의 run 없이 모델 이름만 지정하여 API로 호출할 수 있다.
langchain-openai는 LangChain에서 OpenAI API를 사용하기 위한 연결용 라이브러리이다.

[Gemma + Ollama]
Python → LangChain → Ollama(API) → 로컬 Gemma → 내 PC 연산

[GPT‑4o + OpenAI]
Python → LangChain → OpenAI API → OpenAI 서버(GPT‑4o)



# Ollama 개념 정리

## Ollama란?

Ollama는 LLM(대규모 언어 모델)을  
**내 PC 또는 내부 서버에서 실행하기 위한 온프레미스형 런타임 프로그램**이다.

- Ollama 자체는 LLM 모델이 아니다
- LLM 모델을 실행시켜주는 실행 환경(Runtime)이다

비유하면:
- Ollama = Python 인터프리터
- Gemma, LLaMA = Python 코드

---

## Ollama로 무엇을 할 수 있나?

- ChatGPT와 비슷한 대화
- 인터넷 연결 없이 실행 가능
- 외부 서버로 데이터 전송 없음

---

## "서버 없이 ChatGPT처럼 된다"의 의미

여기서 말하는 서버란:
- OpenAI 서버
- Google 서버
- 외부 클라우드 서버

Ollama는 **내 PC가 서버 역할을 수행**한다.

즉:
- 내 PC = 서버
- Ollama = 서버에서 실행 중인 LLM 런타임
- Gemma = 그 서버에서 돌아가는 모델

# LLM 모델 정리

## LLM이란?

LLM은 텍스트를 이해하고 생성하는 AI 모델이다.

예:
- GPT
- LLaMA
- Mistral
- Gemma

---

## Gemma란?

- Google이 공개한(open) LLM 모델
- 서버 API가 아님
- 모델 파일을 다운로드해서 로컬에서 실행 가능

중요:
Gemma를 사용한다고 해서 질문이 Google 서버로 가는 것은 아니다.

Ollama가 Gemma 모델 파일을 다운로드하고
내 PC에서 직접 실행한다.

# LangChain 개념 정리

## LangChain이란?

LangChain은 LLM 모델을
프로그래밍에서 쉽게 사용하도록 도와주는 프레임워크이다.

- IDE가 아니다
- 코드 편집기가 아니다
- 실행 환경이 아니다

역할:
- LLM 호출 표준화
- 프롬프트 구조 관리
- 대화 흐름 관리

---

## LangChain의 위치

[Python 코드]
   ↓
[LangChain]
   ↓
[Ollama]
   ↓
[LLM 모델 (Gemma)]


# pip 명령어와 라이브러리 개념

## pip install 이란?

pip은 Python 패키지(라이브러리)를 설치하는 도구이다.

예:
pip install ollama

이 명령어는:
- LLM 모델을 설치하는 것이 아니다
- Python에서 Ollama와 통신하기 위한 라이브러리를 설치한다