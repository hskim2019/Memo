# Azure OAuth를 이용한 서비스 사례


- resource owner : =end user, 일반 사용자
- client : web site ex)Naver 로그인 서비스를 사용하는 Gmarket
- Authorization Server/ Resource Server : ex) 인증서비스 제공 사이트, Naver, Facebook
- Access Token : 유효시간 짧음
- Refresh Token : 유효시간 있지만 Access Token보다 길고, Access Token을 다시 받아올 수 있는 Token