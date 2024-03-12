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