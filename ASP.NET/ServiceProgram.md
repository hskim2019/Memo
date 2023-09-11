# Windows Service Program

## 프로그램 형태
```C#
using System.Configuration;
using System.IO;
using System.Net;
using System.ServiceProcess;
using System.Timers;

namespace MyService
{
    public partial class Service1 : ServiceBase // ServiceBase 상속
    {
        private Timer timer;
        private string dataFolder;
       
        public Service1() 
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            // 서비스 실행 시 수행할 함수 여기에
            StartExample();
        }

        protected override void OnStop()
        {
        }

        protected static void StartExample() 
        {
            ServiceStart();

        }

        private Thread thread {get; set;}
        private object lockObject {get; set;}

        public void ServiceStart() 
        {
            // 한번에 한 쓰레드만 lock 블럭 실행
            lock(lockObject) 
            {

            }
        }
    }
}
```

### Reference
* [Windows Service Program 작성] ("https://www.csharpstudy.com/Practical/Prac-service-program.aspx")