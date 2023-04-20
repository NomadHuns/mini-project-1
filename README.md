# 구인구직 사이트 프로젝트

## 팀 소개
- 김태훈(팀장)
- 이상현
- 강민호
- 김지윤

## 기술스택
![기술스택](https://user-images.githubusercontent.com/118786401/233411539-b1f8fc3a-9f97-427a-b30c-fc685018eed4.png)

## 기능
**자바라**는 구인하는 기업과 구직하는 구직자의 매칭 기능 제공하는 사이트입니다.

### 공통 기능
- 기업/일반 회원 로그인 시 하나의 메소드만 호출하는 방식으로 구현
- 회원가입 시 유저네임 중복 체크 및 비밀번호 유효성(동일한 비밀번호를 입력했는지) 체크
- 회원정보수정 시 버튼 한번 클릭으로 사진까지 수정
- 사진 등록 전 미리 보기 기능
- 레디스로 구현하여 브라우저가 닫혀도(JSessionID가 없어도) 로그인 유지
- 구직자/공고 페이징 기능

### 기업 관련 기능
- 이력서 등록한 원하는 기술을 보윻란 유저 추천 기능
- 구직자 목록 정렬 조건 기능
- 공고 등록 기능
- 등록한 공고 목록 수정 및 삭제 기능
- 지원한 구직자 합/불합격 선택 기능

### 구직자 관련 기능

- 채용 합/불합격시 등록된 이메일로 메일링 기능
- 여러개의 자소서 쓰기 및 수정 기능
- 공고 지원 기능
- 보유 기술을 등록하여 기업 추천받기 기능
- 관심 있는 기업 북마크 

## 주요 기능 시연
|||
|:--:|:--:|
|회원가입|로그인|
|![회원가입1](https://user-images.githubusercontent.com/118786401/232196774-ce324eb5-a4ad-4ccb-9625-7efee63358be.gif)|![로그인](https://user-images.githubusercontent.com/118786401/232635843-3aa73e3d-7f1d-496e-bf1a-eaa79a8b8829.gif)|
|정보 수정|공고 등록|
|![정보-수정](https://user-images.githubusercontent.com/118786401/232636464-ffc9660d-5867-4878-b1bb-5f19956329ef.gif)|![공고등록](https://user-images.githubusercontent.com/118786401/232636481-0759ccc8-5389-406d-a1ca-c0452307b979.gif)|
|합/불합 처리|이력서 수정|
|![합불합처리](https://user-images.githubusercontent.com/118786401/232636507-b8b4366b-04b3-4b1d-89f3-3d352f271c16.gif)|![이력서수정](https://user-images.githubusercontent.com/118786401/232636544-794341f5-ec36-44f5-9945-2702d10704db.gif)|
|북마크 기능|지원 신청|
|![북마크기능](https://user-images.githubusercontent.com/118786401/232636566-eba7e6a0-8d4b-47bd-b8c9-041fe1bee583.gif)|![지원신청](https://user-images.githubusercontent.com/118786401/233406164-9168732d-8ead-4d2d-bcb7-83ad7f8bc974.gif)|

## 테이블 설계
![테이블](https://user-images.githubusercontent.com/118786401/233412160-0d63fe4b-acb1-4d0e-b077-1d38cb985bfe.png)

## 블로깅
https://spark-mailbox-fe3.notion.site/K-Digital-1-5690c9f9f2db4c17ba8ede0b97ccc4e7

## 구현하지 못한 기능
- 주소를 받을 때 주소 등록 API를 사용하여서 받기
- 기업 회원이 구직자 북마크 기능
- 공고 새창에 띄우기
- 완성 후 테스트 DB -> MyBatis등 실제 배포 DB로 바꾸기
- 배포해보기
- 검색 기능
- 다크 모드

## 느낀점
- GitHub 사용 시에 Complete 충돌상황이 잦게 발생했다. 중간에 도메인 별로 나누어 작업하여서 충돌을 최소화할 수 있었다.
- 또한 Git을 사용할 때 커밋 메시지가 중구난방 형식이라 이전 코드를 찾을 필요가 있을 때 불편했다. 후에는 커밋 메시지 컨벤션이 필요할 것 같다.
- 프로젝트를 진행하면서 블로깅의 중요성을 확연히 느꼈다. 진행할 때마다 블로깅을 해두면 팀원간에 정보 공유가 원활해지는 장점이 있었다. 또한 블로깅하면서 다시 코드를 보면서 보완하는 부분에서 좋았다.
- 팀원간에 소통 부분에서 아쉬웠다. 해당 작업을 팀원에게 요청할 때 나 또한 완벽하게 이해해야 함을 깨달았다.
- 테스트 코드를 작성하는 부분에서 막히는 부분이 많았다. 최대한 테스트 코드를 모두 다 작성하고 싶었지만, JUnit이 익숙지않아 부족했다. JUnit에 대한 공부가 필요할 것 같다.
- 모든 테스트를 통합테스트로 진행하였기 때문에 제어하지 못한 예외가 발생시 어떤 레이어에서 문제가 생겼는지 파악하기가 어려웠다. 레이어 테스트에 대해서 공부를 해야겠다는 생각이 들었다.
- AOP를 배우기 전이라 AOP의 필요성을 알지 못했는데, 프로젝트 마지막 부분에서 공부하여서 AOP를 구현하였다.(세션 확인 AOP) 사용 이후에 AOP 하나만으로 공통 처리가 가능하며, 비지니스 로직에 집중할 수 있다는 것을 느꼈다.
