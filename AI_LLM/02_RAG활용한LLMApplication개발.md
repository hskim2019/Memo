## RAG
1. Retrieval
 언어모델(LLM)이 가지고 있지 않은 정보를 가져오는 것 (ex. 보안이 걸려있는 사내자료)
2. Augmented
 Retrieval된 데이터를 LLM에게 주면서, "마치 이 정보를 아는 것 처럼"
3. Generation
 내가 가져온 데이터를 제공할테니, 이 정보를 아는 것 처럼 "답변을 생성해라"

## Vector Database
사용자의 질문과 관련있는 데이터를 잘 가져오려면?
- 관련성 파악을 위해 vector 를 활용 : 단어 또는 문장의 유사도를 파악해서 관련성을 측정함
Vector를 생성하는 방법
- Embedding 모델을 활용해서 vector를 생성함
  (강의에서는 Open AI Embedding Model 활용)
- 문장에서 비슷한 단어가 자주 붙어있는 것을 학습
  (embedding projector)
Vector Database
- Embedding 모델을 활용해 생성된 vector를 저장
  -  vecotr만 저장하는 것이 아닌 metadata도 같이 저장해야 함 (문서의 이름, 페이지 번호 등등 LLM이 잘못된 답변을 생성할 때를 대비해서 어떤 문서에서 가져왔는지 출처를 같이 보여줘야 함)
- Vector를 대상으로 유사도 검색 실시
  - 사용자의 질문과 가장 비슷한 문서를 가져오는 것-Retrieval
  - 가져온 문서를 prompt를 통해 LLM에 제공 - Augmented
  - LLM은 prompt를 활용해서 답변 생성 - Generation