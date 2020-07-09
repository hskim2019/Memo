

1. Azure AD Graph API: Tenant Name, Tenant ID 기준 서비스
2. Microsoft Graph: 로그인 사용자 기준 서비스

Azure AD Graph API는 일부 백그라운드로 자동화 처리가 가능하지만,

Microsoft Graph는 웹 브라우저로 Office 365에 명시적으로 로그인을 한 다음에만 동작한다.

Azure AD Graph API가 사용하고 있는 서비스 URL은 https://graph.window.net이고,

실제 사용하는 서비스는 https://graph.window.net/{Tenant ID GUID}/ 하위 URL이 사용되는데

인증 시 사용되는 URL은 https://login.microsoftonline.com/{Tenant Name}/ 이 사용된다.



반면,



Microsoft Graph가 사용하고 있는 서비스 URL은 https://graph.microsoft.com이고,

실제 사용하는 서비스는 https://graph.microsoft.com/v1.0/ 하위 URL이 사용되는데

인증 시 사용되는 URL은 https://login.microsoftonline.com/common 이다.



로그인한 사용자 본인의 정보는 https://graph.microsoft.com/v1.0/me/ 하위 URL이 사용되고

다른 사용자 정보에 접근할 때는 https://graph.microsoft.com/v1.0/users/{Email Address}/ 가 사용된다.


Graph API 사용을 위해서는
1. Azure 포털에 앱 등록
2. 등록된 앱의 Client ID, Client Secret 알고 있어야 함
3. 앱의 최대 사용기간 내에서만 사용 가능
4. 권한 지정
5. 관리자 접속 계정 정보 필요

출처: https://thermidor.tistory.com/1402 [떼르미의 『IT, 그리고 세상』]
