LangChain vs Lang Graph

1. Agent 없이 LangChain 사용하는 경우
질문
 ↓
문서 검색
 ↓
검색결과 삽입
 ↓
LLM
 ↓
답변

여기서 LLM은:

검색할지 말지 결정하지 않음
어떤 도구를 사용할지 결정하지 않음
몇 번 검색할지 결정하지 않음

2. Agent를 사용하는 경우
query : "서울 오늘 날씨 알려줘"
agent 는 내부적으로 다음의 과정을 거침
생각:
날씨 정보가 필요함

Action:
weather_tool 호출   *** 이 부분을 개발자가 직접 지정한 것이 아니라 LLM 이 결정한다

Observation:
26도

생각:
답변 가능

Final Answer:
서울은 26도입니다.
``

만약 Tool 이 여러개라면? 어떤 Tool을 써야 할까?를 LLM 이 판단한다.
Search Tool
Weather Tool
Stock Tool
DB Tool
Calculator Tool

질문
 ↓
LLM 판단
 ↓
Tool 선택
 ↓
LLM 판단
 ↓
Tool 선택


