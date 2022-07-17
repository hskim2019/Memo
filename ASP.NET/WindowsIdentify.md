# Using LogonUser function of Advapi32.dll
- WindowsIdentify 를 이용한 인증
- Windows Impersonation

## 개요
* win32.dll 을 이용
* Windows Token 사용
* Windows Impersonation
 - 시스템에 Logon 한 이후 일정시간 유지 되는 Windows 의 memory 안에 Itentity 를 저장
 - phToken 값을 이용하여 WindowsIdentify 를 얻어 내면 타 시스템에도 접근 가능
 - Windows OS 에 로그인 한 사용자는 자신의 고유한 Itentity 를 가지는데 만약 일시적으로 다른 사람의 로그인 계정 하에서 어떤 작업을 수행 할 필요가 있을 때<br>
   이를 프로그래밍을 통하여 구현 할 수 있다
 - 하나의 프로세스에서 다른 사람의 계정으로 둔갑하는 것을 Impersonation 이라고 한다

### DLL import
#### import 할 dll 파일은 아래 경로에 위치 해야 함
```
[%SystemRoot%] (Windows 디렉토리)
[%SystemRoot%]\system32\ 경로 (Microsoft Windows XP 일 경우)
실행파일(현재 작업) 디렉토리에 같이 위치
```

#### 외부 라이브러리 사용 코드 예
```c#
using System.Runtime.InteropServices; // [1] DLL import 를 정의하고 있는 namespace
using System.Security; // WindowsImpersonationContext
using System.Security.Principal; // PerissionSetAttributte

// [2] DLL import
[return: MarshalAs(UnmanagedType.Bool)]
[DllImport("advapi32.dll", EntryPoint = "LogonUserW", CharSet = CharSet.Unicode, SetLastError = true)]
/* [3] public 및 static 의 정적 method 로 선언 해야 함
       extern 한정자는 method 가 C# 외부에서 구현된다는 의미
       파라미터와 리턴 값의 자료형을 정확하게 맞추어 줘야 하고 그렇지 않을 경우 데이터가 소실 될 수 있음
*/
// LogonUser(@"Administrator@vplex.net", "network ip in here", "password", 9, 0, ref tokenHandle);
// username 은  email 형식으로 입력되어야 함
// [4] 로그온 API 호출
static extern bool LogonUser(string username, string domain, string password, LogonType logonType, LogonProvider logonProvider, ref SafeTokenHandle token);
```

### Example
```C#
using System;

class Program {
    static void Main(string[] args) {
        using(ImpersonationExample impersonation = new ImpersonationExaple("Administrator@vplext.net", "networkip", "password")) {
            // ...
            // using 문 사용으로 계정 Impersonation 후 인스턴스 소멸 시 사용자 undo 복귀 됨
        }
    }
}
```
```C#
using System;
using System.Runtime.InteropServices; // [1] DLL import 를 정의하고 있는 namespace
using System.Security; // WindowsImpersonationContext
using System.Security.Principal; // PerissionSetAttributte
using Microsoft.Win32.SafeHandles;

namespace ImpersonationExample
{
    public enum LogonType
    {
        Batch = 4,
        Interactive = 2,
        Network = 3,
        NetworkCleartext = 8,
        NewCredentials = 9,
        Service = 5,
        Unknown = 0,
        Unlock = 7
    }

    public enum LogonProvider
    {
        Default,
        Winnt35,
        Winnt40,
        Winnt50
    }

    public sealed class ImpersonationExample
    {
        [return: MarshalAs(UnmanagedType.Bool)]
        // [2] DLL import
        [DllImport("advapi32.dll", EntryPoint = "LogonUserW", CharSet = CharSet.Unicode, SetLastError = true)]
        // [3] public 및 static 의 정적 method 로 선언 해야 함
        // extern 한정자는 method 가 C# 외부에서 구현된다는 의미
        // 파라미터와 리턴 값의 자료형을 정확하게 맞추어 줘야 하고 그렇지 않을 경우 데이터가 소실 될 수 있음
        // LogonUser(@"Administrator@vplex.net", "network ip in here", "password", 9, 0, ref tokenHandle);
        // username 은  email 형식으로 입력되어야 함
        // [4] 로그온 API 호출
        static extern bool LogonUser(string username, string domain, string password, LogonType logonType, LogonProvider logonProvider, ref SafeTokenHandle token);

        enum SECURITY_IMPERSONATION_LEVEL
        {
            Anonymous,
            Identification,
            Impersonation,
            Delegation
        }

        [return: MarshalAs(UnmanagedType.Bool)]
        [DllImport("advapi32.dll", CharSet = CharSet.Unicode, SetLastError = true)]
        static extern bool DuplicateToken(SafeTokenHandle ExistingTokenHandle, SECURITY_IMPERSONATION_LEVEL ImpersonationLevel, out SafeTokenHandle DuplicateTokenHandle);

        private SafeTokenHandle existingTokenHandle = new SafeTokenHandle();
        private SafeTokenHandle duplicateTokenHandle = new SafeTokenHandle();
        private WindowsImpersonationContext impersonationContext;

        public Impersonation()
        {
        }

        public Impersonation(string userName, string password, string domain)
        {
            this.Domain = domain;
            this.Password = password;
            this.UserName = userName;
            this.LogonProvider = Helper.LogonProvider.Default;
            this.LogonType = Helper.LogonType.Interactive;

            this.ImpersonationStart();
        }

        public string Domain { get; set; }

        public string Password { get; set; }

        public string UserName { get; set; }

        public bool Success { get; private set; }

        public Exception Error { get; private set; }

        public LogonProvider LogonProvider { get; set; }

        public LogonType LogonType { get; set; }

        public bool ImpersonationStart()
        { 
            this.Success = false;
            if (LogonUser(UserName, Domain, Password, this.LogonType, this.LogonProvider, ref existingTokenHandle))
            {
                if (DuplicateToken(existingTokenHandle, SECURITY_IMPERSONATION_LEVEL.Impersonation, out duplicateTokenHandle))
                {
                    // WindowsIdentity 클래스는 System.Security.Principal 네임스페이스에 있는 클래스로서 윈도우즈 사용자를 표현하는 클래스
                    // 현재 로그인 사용자를 구하거나 다른 계정으로 Impersonation 하는 등의 기능을 가지고 있다.
                    impersonationContext = WindowsIdentity.Impersonate(duplicateTokenHandle.DangerousGetHandle()); // ~ finalize 함수로 인스턴스 소멸 시 undo
                    if (impersonationContext != null) this.Success = true;
                }
                else
                {
                    int error = Marshal.GetLastWin32Error();
                    throw new Win32Exception(error, string.Format("DuplicateToken failed: {0} : {1}", error, (new Win32Exception(error)).Message));
                }
            }
            return this.Success;
        }
              
        public bool ImpersonationStart(string userName, string password, string domain)
        {
            this.Domain = domain;
            this.Password = password;
            this.UserName = userName;
            this.LogonProvider = Helper.LogonProvider.Default;
            this.LogonType = Helper.LogonType.Interactive;

            return this.ImpersonationStart();
        }

        public void ImpersonationEnd()
        {
            if (impersonationContext != null)
            {
                // 원래 사용자로 복귀
                impersonationContext.Undo(); 
                impersonationContext.Dispose();
                impersonationContext = null;

                if (existingTokenHandle != null && !existingTokenHandle.IsClosed)
                {
                    existingTokenHandle.Close();
                    existingTokenHandle.Dispose();
                }
                if (duplicateTokenHandle != null && !duplicateTokenHandle.IsClosed)
                {
                    duplicateTokenHandle.Close();
                    duplicateTokenHandle.Dispose();
                }
            }
        }

        public void Dispose()
        {
            this.ImpersonationEnd();
            GC.SuppressFinalize(this);
        }

        // ~ 소멸자
        // 소멸자는 자동으로 동작하며 명시적으로 실행할 수 없음
        ~Impersonation()
        {
            this.ImpersonationEnd();
        }
    }
}
```


### Reference
* [WindowsIdentify를 이용한 인증] ("https://netframework.tistory.com/entry/WindowsIdentify-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9D%B8%EC%A6%9D")
* [C#에서 DLL Import 방법] ("https://userpark.net/54")
* [외부 라이브러리 사용] ("https://irontooth.tistory.com/172")
* [Windows Impersonation] ("http://m.csharpstudy.com/Practical/View?aspx=Prac-Impersonation.aspx&title=C%23%20Impersonation")
* [msdn] (https://docs.microsoft.com/en-us/previous-versions/windows/desktop/legacy/ee391633(v=vs.85))