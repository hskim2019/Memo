# python installation and set-up

## installation
1) python anaconda 설치
    - https://www.anaconda.com
    - 설치 옵션 선택 시 path 설정도 check 할 것

2) 설치 후 버전 확인
```cmd
$ python --version
$ anaconda --version
```

3) anaconda 가상환경 생성
```cmd
-- conda 명령어
--$ conda create --name askcompany python*3.9 -- 실행이 안 됨
$ conda create -n askcompany python=3.9
$ conda create -n [name] python=[python version to proceed]
```

* 가상환경 조회
```cmd
$ conda info --envs
$ conda env list
```
* 가상환경 삭제
```cmd
$ conda deactivate  -- 환경 비활성화
$ conda remove --name [name] --all -- 환경 삭제
```

4) conda activate
```cmd
$ conda activate askcompany
```

5) checking python / pip directory
```cmd
$ where python
$ where pip
```

* pip
- python으로 작성 된 패키지 라이브러리를 관리 해 주는 시스템
- python packiage manager

6) jango 설치 (pip 이용해서 설치)
-- 설치 후 jango 명령어도 사용할 수 있다
```cmd
$ pip install "django~=3.0.0"
$ django-admin --version -- 설치 후 버전 확인
```


# create jango project

1) 장고 프로젝트 생성
```cmd

$ conda activate askcompany -- 가상환경 실행
$ cd C:\ 
$ mkdir PythonDev
$ cd PythonDev  -- 프로젝트 생성 위치

$ django-admin startproject [project name] -- 프로젝트 생성
$ django-admin startproject askcompany 
-- or
$ python -m django startproject [project name]

$ cd askcompany
```


2) 개발서버 구동
```cmd
$ python manage.py migrate
$ python manage.py creatsuperuser -- id , pw 생성
$ python manage.py runserver
```

3) 브라우저에서 접속
- http://127.0.0.1:8000/admin

# install visual studio code
1) marketplace 에서 python 설치
2) ctrl+shift+p - 명령 팔레트에서 python selectinterpreter 선택

* terminal 기본 세팅 변경하려면
- ctrl + shift + p > terminal: select default profile (터미널 기본 프로필 선택) > cmd / powershell 원하는 것으로 변경


# create jango app
* 테스트로 장고앱 만들기
* 장고앱 형태로 격리해서 만들어두면 다른 프로젝트에서도 사용할 수 있다

1) 기본 앱템플릿으로 생성
```cmd
-- manage.py 가 있는 경로에서
$ python manage.py startapp [app name]
# python manage.py startapp blog1
```

2) 앱 등록
* settings.INSTALLED_APPS 에 등록 해야 함
* askcompany > settings.py > 
```py
INSTALLED_APPS = [
    ..., 
    'blog1', # 'comma' 있어야 함 
    ]
```


# memo
* 장고 모델을 통해 db형상 관리하는 경우
 1) model class 생성
 2) model class 로부터 migration file 생성 -> makemigrations 명령
 3) migration file을 database 에 적용 -> migrate 명령
 4) model 활용
* 장고 외부에서 db 형상 관리 하는 경우
 1) database 로부터 model class source 생성 -> inspectdb 명령
 2) model 활용

* model명과 DB table 명
- e.g) blog 앱의 comment 모델 - blog_comment
- 커스텀 지정 하려는 경우 makemigrations 하기 전에 할 것

=======================================================================

# create another jango app
1) 가상환경 실행
``` cmd
$ conda activate askcompany -- 가상환경 실행
```
2) 앱 생성
```
-- manage.py 가 있는 경로에서
$ python manage.py startapp instagram
```
3) 앱 등록
- settings.py
```python
INSTALLED_APPS = [
    'instagram'
]
```
4) urls.py 생성
- instagram 폴더에 urls.py 파일 생성