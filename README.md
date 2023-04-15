# 구인구직 사이트 프로젝트

## 기술스택
- JDK 11
- Springboot 2.7.8
- MyBatis
- 테스트 h2 디비
- JSP
- Tomcat Jasper
- JSTL
- Redis

## 모델링
### 1단계 (완료)
- User
- Company
- Employee
- Board
### 2단계 (완료)
- Apply
- Tech
- Resume
### 3단계 (완료)
- Love

## 기능정리
### 1단계 (완료)
- [x]  1단계 테이블 기본적인 CRUD 쿼리 생성
- [x]  기업로그인
- [x]  기업회원가입
- [x]  기업회원정보수정
- [x]  일반회원로그인
- [x]  일반회원가입
- [x]  일반회원정보수정
- [x]  로그아웃
- [x]  채용공고 목록보기
- [x]  채용공고 상세보기
- [x]  구직자 목록 페이지
- [x]  구직자 상세 페이지
- [x]  자소서 쓰기
- [x]  채용공고 쓰기
- [x]  채용공고 수정
### 2단계 (완료)
- [x]  내가 작성한채용 공고 목록 이동 board 3
- [x]  지원자 테이블 생성 apply 1
- [x]  보유 기술 테이블 생성 tech 1
- [x]  채용공고 지원하기 apply 1
- [x]  채용공고 지원자 목록 apply 1
- [x]  구직자 목록 정렬하기(보유 기술이 유사한 순으로 정렬 기능 추가) user 2
- [x]  채용공고 목록 정렬하기(보유 기술이 유사한 순으로 정렬 기능 추가) board 3
- [x]  지원하지 않은 구직자 추천 board 3
- [x]  프로필 사진 변경 user, company 2 3
- [x]  아이디 중복 체크(서버에서도 체크) user, company 2 3
- [x]  비밀번호 확인 user, company 1
- [x]  이력서 테이블 생성
### 3단계 (완료)
- [x]  이력서 목록 페이지
- [x]  자신의 이력서 관리 기능
- [x]  채용 공고 지원시 이력서 선택 제출
- [x]  페이징 처리
- [x]  구직자 구독 기능
- [x]  공고 구독 기능
- [x]  비밀번호 SHA-256을 사용하여 해시화
### 4단계
- [ ]  회사 별점
- [x]  공고 지원 남은 날짜 표시
- [x]  AOP 사용
- [x]  Redis 사용
- [ ]  지도 API
- [ ]  주소입력 API
- [ ]  이메일 유효성 검사

# 주요 시연
## 회원가입
![회원가입1](https://user-images.githubusercontent.com/118786401/232196774-ce324eb5-a4ad-4ccb-9625-7efee63358be.gif)

## 로그인
![로그인_AdobeExpress](https://user-images.githubusercontent.com/118786401/232196800-63bdc0d9-8105-4d73-bdfb-415671233e58.gif)

## 정보 수정
![정보_수정_AdobeExpress](https://user-images.githubusercontent.com/118786401/232197009-9506d2dd-76a3-4fb9-a79a-9628d0bb4915.gif)

## 공고 등록
![공고등록_AdobeExpress](https://user-images.githubusercontent.com/118786401/232197289-130ae8d7-6e8f-4c44-9630-da7e25ce3a2c.gif)

## 공고 수정
![공고수정_AdobeExpress](https://user-images.githubusercontent.com/118786401/232196858-0efeff8d-e712-4da9-ba04-49928368c337.gif)

## 합/불합 처리
![합불합처리_AdobeExpress](https://user-images.githubusercontent.com/118786401/232196960-48e85d02-6823-4b2e-846e-4f1dbc1eef4b.gif)

## 이력서 수정
![이력서수정_AdobeExpress](https://user-images.githubusercontent.com/118786401/232197020-1a5a3c0e-8450-47b8-8f1b-a9690686c125.gif)

## 북마크 기능
![북마크기능_AdobeExpress](https://user-images.githubusercontent.com/118786401/232197051-eff2c37a-1329-42a3-9770-018d12fcb8fa.gif)

## 지원 신청
![지원신청_AdobeExpress](https://user-images.githubusercontent.com/118786401/232197063-5dad2ed8-8a93-45b7-acb1-d12f96c3a983.gif)

# 발표 자료

![page1](https://user-images.githubusercontent.com/118786401/232197082-f4d0cd48-1ec2-4a8a-8b46-49896aaa70ef.png)
![page2](https://user-images.githubusercontent.com/118786401/232197085-b923576e-0314-46b1-8615-1d8a3028deb9.png)
![page3](https://user-images.githubusercontent.com/118786401/232197087-f68e0fcb-2422-4aab-8025-814fb88a42c0.png)
![page4](https://user-images.githubusercontent.com/118786401/232197088-98bed8de-d63a-4f6f-a682-f1afe2e6d8f6.png)
![page5](https://user-images.githubusercontent.com/118786401/232197089-170eb0cb-7c91-4e4d-8b38-3da644c60e5b.png)
![page6](https://user-images.githubusercontent.com/118786401/232197094-91b5b530-a735-4300-953e-696a23a36348.png)
![page7](https://user-images.githubusercontent.com/118786401/232197095-59ada3af-7181-4f0b-8850-09e572557325.png)
![page88](https://user-images.githubusercontent.com/118786401/232197120-ebd87422-9e8a-47c7-8a2d-34b6e9fa319f.png)
![page9](https://user-images.githubusercontent.com/118786401/232197096-efb445f3-3bb7-4b4e-b4eb-392babd35fa3.png)
![page10](https://user-images.githubusercontent.com/118786401/232197097-2133f96c-bf06-4366-adb6-8432c3554505.png)
![page11](https://user-images.githubusercontent.com/118786401/232197099-13e2ec3f-bd1e-4d3c-abab-27c0c9fda90f.png)
![page12](https://user-images.githubusercontent.com/118786401/232197101-2f94e77e-8c92-4984-bcac-c7d39d62bdfc.png)
![page13](https://user-images.githubusercontent.com/118786401/232197102-927a8e8f-a598-443b-8d74-3d1673c5eecb.png)
![page14](https://user-images.githubusercontent.com/118786401/232197104-1b5ebe75-d83a-4786-9dfd-54a489f060b1.png)
![page15](https://user-images.githubusercontent.com/118786401/232197105-2a6c32f6-2376-446c-9b52-061c7f23f1bc.png)
![page16](https://user-images.githubusercontent.com/118786401/232197108-176be1d0-02d5-4912-a856-58c4dda34ee7.png)
![page17](https://user-images.githubusercontent.com/118786401/232197110-2eb1d999-c191-418c-961d-dcc751e155e2.png)
![page18](https://user-images.githubusercontent.com/118786401/232197136-ed6702e4-d4fa-4c09-b2b8-58d850e018f7.png)
![page19](https://user-images.githubusercontent.com/118786401/232197142-10bbca8b-2fa0-4594-bf23-7da933fd6205.png)
![page20](https://user-images.githubusercontent.com/118786401/232197143-dcb81dcc-0ebf-4a6f-9334-d97162693a38.png)
![page21](https://user-images.githubusercontent.com/118786401/232197145-91f2962b-3ead-4135-8ab7-2a4aa5c9010a.png)
![page22](https://user-images.githubusercontent.com/118786401/232197147-a7f34128-583d-4636-8c8a-e12f766fad1b.png)
![page23](https://user-images.githubusercontent.com/118786401/232197148-0eb3d95b-1399-4ecd-928e-b7306ec2f259.png)

