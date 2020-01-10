# Azure OAuth를 이용한 서비스 사례


- resource owner : =end user, 일반 사용자
- client : web site ex)Naver 로그인 서비스를 사용하는 Gmarket
- Authorization Server/ Resource Server : ex) 인증서비스 제공 사이트, Naver, Facebook
- Access Token : 유효시간 짧음
- Refresh Token : 유효시간 있지만 Access Token보다 길고, Access Token을 다시 받아올 수 있는 Token






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