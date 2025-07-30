# stash test
1. master 에서 stash
 -- git stash  -m "algorithm.md stash test"
2. master 로부터 branch 생성
-- git branch 생성 : git branch "branch1"
3. branch 로 switch 해서 branch stash apply
-- git branch로 전환 : git switch branch1
-- git stash 목록 확인 : git stash list
 -- stash 내용 branch 에 적용 : git stash apply stash@{1}
4. branch 커밋
-- git add .
-- git commit -m "stash 한 내용 커밋 테스트"
-- git push origin branch1 (git push origin [브랜치명] github 에 브랜치 생성하지 않았으나 이 명령어로 브랜치까지 생성)
5. branch 를 master 에 merge
-- git merge branch1
-- git push

-- merge 되돌리기
 - git reset --hard HEAD^

-- git stash 삭제
 - git stash drop stash@{1}
 - git stash clear (모든 stash 삭제)

 - [참고링크](https://doing-programming.tistory.com/entry/Git-%EC%9E%91%EC%97%85%ED%95%98%EB%8D%98-%EB%82%B4%EC%9A%A9-%EB%B8%8C%EB%A0%8C%EC%B9%98-%EC%98%AE%EA%B8%B0%EA%B8%B0-Git-Stash)

 # branch 목록 확인
 ## git branch

 # branch 삭제
 ## git branch -d {branch이름}

 # git merge 
 1. B 를 A 에 병합하려면 A 에서 
 - git merge B 실행
 2. Please enter a commit message to explain why this merge is necessary,...
 - i 입력
 - merge message 입력
 - [esc]
 - :wq 입력
 - [enter] 

 # git init commit
 - 프로젝트 생성 후 처음 git 에 commit 할 때

 1. github 에 repository 생성
 2. git bash - 프로젝트 디렉토리로 이동하여
    - git 초기화 (이미 git 프로젝트라면 그동안 쌓인 git 기록이 초기화 될 수 있으므로 주의)
    - .git 폴더가 생성된다
 ```cmd
 > git init
 ```
 3. 원격 저장소 이름 지정
```cmd
> git remote add [이름] [HTTPS 또는 SSH 주소]
> git remote add origin https://github.com/hskim2019/CSPROJ_TEST.git
```

4. commit
```cmd
> git add .
> git commit -m "init commit"

> git push -u [저장소이름] [branch이름]
> git push -u origin master
```

# 이미 프로젝트에 올라간 ignore 되어야 할 파일 정리
```cmd
> git rm -r --cached .
> git add .
> git commit -m "적용할 커밋 메시지"
> git push
```