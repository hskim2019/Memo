# Azure OAuth를 이용한 서비스 사례


- resource owner : =end user, 일반 사용자
- client : web site ex)Naver 로그인 서비스를 사용하는 Gmarket
- Authorization Server/ Resource Server : ex) 인증서비스 제공 사이트, Naver, Facebook
- Access Token : 유효시간 짧음
- Refresh Token : 유효시간 있지만 Access Token보다 길고, Access Token을 다시 받아올 수 있는 Token


* 인증 Authentication  
  - 로그인(id,password로 이용 권한 부여 받기)
* 인가 Authorization  
  - 인증 후 로그인 유지되는 상태에서 사용자가 서비스 이용 시 로그인 되어 있음을 알아보고 서비스 이용을 허가 해 주는 것
  - jwt
  
* 세션 - 
* 쿠키 - 브라우저에 저장되는 정보, server에서 browser로 cookie를 통해 필요한 정보를(sessionId) 심어놓기, server로 요청 보낼 때 마다 cookie는 같이 옴
* 캐시
* [Client] 1) ---------ID, Password-------------------->[Server]
           2) <--Cookie에 sessionId 담아서 돌려주기------
* 세션 정보를 메모리에 저장하면 이용자가 많은 경우 메모리가 무족하거나<br>
  메모리는 휘발성이고 서버에 문제가 생기거나 재부팅 되어야 하면 세션 정보도 전부 사라짐.<br>
  하드나 DB에 세션 정보를 저장하는 것은 속도 저하의 문제가 있고<br>
  서비스 규모가 커서 서버개인 경우 세션 관리가 어려움<br>
  -> Redis 사용 하여 세션 정보 저장
  -> 또는 jwt json web token 서버가 로그인 정보 저장 할 필요 없도록
* jwt 
    - jwt구조 : header.payload.verifysignature
    - header: 토큰 타입(type은 항상 jwt이므로 고정), alg(알고리즘, verify signature 값 만들 때 사용할 알고리즘) 
    - payload : 토큰 유효기간, 누가 누구에게 발급 했는지, 사용자 닉네임 등등<br>
                Claim - 토큰에 담긴 사용자 정보 등의 데이터
    - signature : 헤더, 베이로드, 서버에 감춰놓은 비밀값을 암호와 알고리즘에 넣어 만든 겂
    - 서버는 요청에 token 값이 실려오면 서버의 secret key 값과 함께 돌려 계산된 서명값이 일치하는지 확인<br>
      payload가 수정 되면 값이 맞지 않으므로 거부 됨
    - server에 사용자들의 상태를 저장해두지 않아도 되기 때문에 stateless 상태로(<->stateful) 유지 가능
        - stateless : server로 가는 요청이 이전 request와 독립적으로 다뤄짐 
    - jwt한계
        - session처럼 stateful 해서 사용자의 상태를 기억할 수 있으면 사용자 제어가 용이함(ex. 한 기기에서만 로그인 가능한 서비스 만들기)<br>
        - 토큰 무효화 할 수 없음(토큰이 탈취되면?)
        - 따라서 jwt 방식만으로 인가를 구현하는 것은 한계가 있음

    - jwt 보완 예 
        - 로그인 시 수명이 짧은 access token, 수명이 긴 refresh token으로 토큰을 두 개 발급
        - refresh token 상응값을 DB에 저장
        - access token 수명이 다하면 refresh token을 보내서 DB 값과 대조해보고 일치하면 새 access token 발급
        - refresh token 을 DB에서 삭제하면 강제로그아웃 하거나 access token이 갱신 되지 않도록 제어 가능 
        - 여전히 access token 이 탈취 되었을 때 무효화 하지 못하므로 같은 문제는 남아 있지만 토큰이 하나일 때 보다 보완 할 수 있는 방안


## OAuth 2.0 인증 방식 4가지
1. Authorization Code Grant
2. Implicit Grant
3. Resource Owner Password Credentials Grant
4. Client Credentials Grant

## 동작 순서
<img src="https://t1.daumcdn.net/cfile/tistory/99E7B03A5B6EE05324">
- Resource Owner : User
- Client : Application Server
- Authorization Server : 권한관리 서버, offer Access Token, Refresh Token
- Resource Server : Oauth 관리 서버(Google, Facebook, Naver, etc)의 자원을 관리하는 서버 (Oauth 2.0 API)

<img src ="https://t1.daumcdn.net/cfile/tistory/9945F13F5B6EECC02A">

1. Resource Owner(사용자)가 Client(우리 서버)에게 인증 요청

2. Client는 Authorization Request를 통해 Resource Owner에게 인증할 수단(ex Facebook, Google 로그인 url)을 보냄

3. Resource Owner는 해당 Request를 통해 인증을 진행하고 인증을 완료했다는 신호로 Authorization Grant(권한증서) 를 url에 실어 Client에게 보낸다

4. Client는 해당 권한증서(Authorization Grant)를 Authorization Server에 보낸다

5. Authorization Server는 권한증서를 확인 후, 유저가 맞다면 Client에게 Access Token, Refresh Token, 그리고 유저의 프로필 정보(id 포함) 등을 발급

6. Client는 해당 Access Token을 DB에 저장하거나 Resource Owner에게 넘긴다

7. Resource Owner(사용자)가 Resource Server에 자원이 필요하면, Client는 Access Token을 담아 Resource Server에 요청한다

8. Resource Server는 Access Token이 유효한지 확인 후, Client에게 자원을 보낸다

9. 만일 Access Token이 만료됐거나 위조되었다면, Client는 Authorization Server에 Refresh Token을 보내 Access Token을 재발급

10. 그 후 다시 Resource Server에 자원을 요청

11. 만일 Refresh token도 만료되었을 경우, Resource Owner는 새로운 Authorization Grant를 Client에게 넘겨야 함 (= 다시 로그인)


<img src="https://t1.daumcdn.net/cfile/tistory/99115C3F5B6EECBF37">




## Session / Cookie 
<img src="https://t1.daumcdn.net/cfile/tistory/994BEA345B53368401">
- session/cookie 방식은 세션 저장소가 필요(Redis를 많이 사용 함)
- session storage는 Session ID 를 생성하고, HTTP Header에 실어 사용자에게 return
- 사용자는 Session ID를 cookie로 보관 하고 인증이 필요하면 Cookie를 전달함
- 서버는 세션 저장소에서 Cookie를 확인

## HttpOnly
- 쿠키 생성 시 HttpOnly 옵션을 주면 쿠키는 자바스크립트로 접근 불가능


## References
- https://tansfil.tistory.com/58?category=255594