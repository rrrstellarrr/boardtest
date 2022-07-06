
# SpringBoot 게시판 미니프로젝트


### 1. 사용 기술 및 개발 환경
  * Language :
    * JAVA
  * Framework :
    *  Spring-Boot 2.6.7
  * Library :
    *  JPA(Spring Data JPA)
    *  Thymeleaf
    *  Spring Security
  * Build Tool :
    *  Gradle 7.2
  * DataBase :
    *  MySql

### 2. 기능
  * 게시판
    *  게시글 등록
    *  게시글 조회
    *  게시글 수정
    *  게시글 삭제
    *  게시글 페이징
    *  게시글 검색
       *  전체, 제목
    *  게시글 목록 정렬
       *  최신순, 조회순, 댓글순

  * 사용자
    * 회원 가입
       *  회원가입시 유효성 검사 및 중복 검사
    *  회원 로그인
    *  회원 정보 수정(마이페이지 - 회원정보)
       *  프로필 사진 등록
       *  회원정보 수정시 유효성 검사 및 중복 검사
    *  회원 탈퇴
       *  회원 탈퇴시 작성된 댓글, 게시글 삭제
    *  회원글 목록
  
  * 댓글
    *  댓글 작성
    *  댓글 삭제
    
### 3. 실행 화면
<details markdown="1">
<summary>게시판</summary>
1-1. 게시글 전체목록 화면 (기본 최신순 정렬)
<img width="1598" alt="Screen Shot 2022-07-06 at 15 57 23" src="https://user-images.githubusercontent.com/87481519/177488542-00fd21fa-ec4b-407c-9fc6-f2e5b62d0af7.png">
1-2. 게시글 전체목록 화면 (조회수순 정렬)
<img width="1598" alt="Screen Shot 2022-07-06 at 16 00 13" src="https://user-images.githubusercontent.com/87481519/177489412-4f265f6a-f041-4d3f-805d-51ef390e21aa.png">
1-3. 게시글 전체목록 화면 (댓글순 정렬)
<img width="1598" alt="Screen Shot 2022-07-06 at 16 00 36" src="https://user-images.githubusercontent.com/87481519/177489523-cbadbf3d-e9a9-4eee-ad6b-c5402c1f5193.png">

2. 게시글 등록
2-1. 게시글 제목 입력 안하도 등록 버튼 누를시 제목 입력 요구 알림창이 뜬다
<img width="1598" alt="Screen Shot 2022-07-06 at 16 04 29" src="https://user-images.githubusercontent.com/87481519/177489970-e16d9d82-be28-4af8-b845-cbac128d4a29.png">
2-2. 게시글 등록 완료 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 06 08" src="https://user-images.githubusercontent.com/87481519/177490126-a734ca54-1a9e-48d6-ace6-7f2842e7abb6.png">

3. 게시글 상세보기
3-1. 게시글 상세보기 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 07 44" src="https://user-images.githubusercontent.com/87481519/177490456-cad56687-f4c1-4c3e-96be-1a670c25c62d.png">
3-2. 다른 유저가 쓴 글 상세보기(수정, 삭제 버튼이 없다)
<img width="1598" alt="Screen Shot 2022-07-06 at 16 14 52" src="https://user-images.githubusercontent.com/87481519/177491862-e0a6823a-f97e-4a61-b31d-fa7817e6ef37.png">

4 게시글 수정하기
4-1. 게시글 수정화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 08 41" src="https://user-images.githubusercontent.com/87481519/177490726-703455f2-4f36-4506-b42b-60a3a5d2cca4.png">
4-2. 게시글 수정 완료 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 08 53" src="https://user-images.githubusercontent.com/87481519/177490823-7044513c-8568-4ddd-94b1-797df3fa02f2.png">
4-3. 게시글 수정 완료후 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 10 21" src="https://user-images.githubusercontent.com/87481519/177490909-0406670e-5b1e-4904-b9a8-2ef5ba8c9728.png">
4-4. 게시글 수정 후 게시글 전체목록 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 11 55" src="https://user-images.githubusercontent.com/87481519/177491349-b25c5f9a-819f-44a2-8477-2dd860bc4bae.png">

5 게시글 삭제 
5-1. 게시글 삭제버튼 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 11 09" src="https://user-images.githubusercontent.com/87481519/177491075-f8454657-ff62-4b4c-9c76-fed03c5a08bb.png">
5-2. 게시글 삭제 완료 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 14 02" src="https://user-images.githubusercontent.com/87481519/177491613-e2319d42-cb8a-4029-9e69-f4ed50fa367a.png">

6. 페이징
6-1. 페이지 버튼 불렀을 때, 게시글 목록 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 45 30" src="https://user-images.githubusercontent.com/87481519/177497497-3cf4fca6-5be1-4c7d-b045-0302531dbd3f.png">

7. 게시글 검색
7-1. 전체로 검색
<img width="1598" alt="Screen Shot 2022-07-06 at 16 46 13" src="https://user-images.githubusercontent.com/87481519/177498302-29f5f652-4e92-454e-9946-1702d168c7bd.png">
7-2. 전체로 검색 결과 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 46 50" src="https://user-images.githubusercontent.com/87481519/177498412-0a98a935-6448-40a9-a0d0-3ccc7fdd9592.png">
7-3. 제목으로 검색
<img width="1598" alt="Screen Shot 2022-07-06 at 16 48 13" src="https://user-images.githubusercontent.com/87481519/177498485-22767376-49df-4bf0-84e7-77237ef834de.png">
7-4. 제목으로 검색 결과 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 51 30" src="https://user-images.githubusercontent.com/87481519/177498638-ae3bb0f2-74f1-431d-a47b-c23c93d10e53.png">
</details>

<details markdown="1">
<summary>댓글</summary>
1-1. 댓글 등록
<img width="1598" alt="Screen Shot 2022-07-06 at 16 16 35" src="https://user-images.githubusercontent.com/87481519/177492177-907fe76a-1c55-48dc-972d-c2d3baaaac84.png">
1-2. 댓글 등록 완료후 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 17 53" src="https://user-images.githubusercontent.com/87481519/177492315-770421c7-dfb8-4aec-bce6-a8b634510ddd.png">

2-1. 댓글 삭제
<img width="1598" alt="Screen Shot 2022-07-06 at 16 18 17" src="https://user-images.githubusercontent.com/87481519/177492402-7dd68835-b5f0-492b-b77e-4e0d9f605e2d.png">
2-2. 댓글 삭제 완료 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 19 07" src="https://user-images.githubusercontent.com/87481519/177492545-2144753b-c3d2-4620-90f2-0a0f24c7d3b1.png">
</details>

<details markdown="1">
<summary>사용자</summary>
1-1. 로그인 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 20 26" src="https://user-images.githubusercontent.com/87481519/177493031-33369924-c1c6-4cf9-b9ba-f45253d50b99.png">
1-2. 로그인 실패 화면
<img width="1335" alt="Screen Shot 2022-07-06 at 21 05 27" src="https://user-images.githubusercontent.com/87481519/177546367-8893284f-45d2-4be7-bc25-4ada2bdc6339.png">

2. 회원가입 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 23 06" src="https://user-images.githubusercontent.com/87481519/177493243-79b75759-8ad4-4731-8c96-638c67f9e7f7.png">
2-1. 회원 가입 입력란에 입력 없이 회원가입 버튼 눌렀을 경우
<img width="1598" alt="Screen Shot 2022-07-06 at 16 20 41" src="https://user-images.githubusercontent.com/87481519/177494227-2c5457a8-a542-4b0f-85f6-96b47e5cde47.png">
2-2. 회원 중복 체크 
<img width="1598" alt="Screen Shot 2022-07-06 at 16 21 22" src="https://user-images.githubusercontent.com/87481519/177494481-aadad5da-cf11-4627-aa22-99320bc5440c.png">

3. 회원정보 수정
3-1. 마이페이지 - 내정보 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 30 45" src="https://user-images.githubusercontent.com/87481519/177494880-d0d2e60a-31c4-4302-a46f-1af41d514c55.png">
3-2. 내 정보 수정시 비밀번호 입력없이 내정보 수정 버튼을 눌렀을 경우
<img width="1598" alt="Screen Shot 2022-07-06 at 16 35 02" src="https://user-images.githubusercontent.com/87481519/177495956-49f2ea15-c775-4c4b-96f7-972d9041ef23.png">
3-3. 내 정보 수정시 중복 이메일로 수정할 경우
<img width="1598" alt="Screen Shot 2022-07-06 at 16 35 28" src="https://user-images.githubusercontent.com/87481519/177496132-6cd5d5af-371d-49a1-a0e2-b6f1d7a8159a.png">
3-4. 내 정보 수정시 중복 닉네임으로 수정할 경우
<img width="1598" alt="Screen Shot 2022-07-06 at 16 36 04" src="https://user-images.githubusercontent.com/87481519/177496221-36d52e53-eac3-4808-b74d-faee2978bf0a.png">
3-5. 내정보 수정 완료 알림창
<img width="1598" alt="Screen Shot 2022-07-06 at 16 36 31" src="https://user-images.githubusercontent.com/87481519/177496360-20b03e6a-5a7f-44d0-8db6-bd8826dc7059.png">
3-6. 내정보 수정 완료후 마이페이지 - 내정보 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 36 38" src="https://user-images.githubusercontent.com/87481519/177496449-e13bc1c8-af61-4769-9d91-4f564c96dc1f.png">

4. 내가 작성한 게시글 목록 화면
<img width="1598" alt="Screen Shot 2022-07-06 at 16 36 54" src="https://user-images.githubusercontent.com/87481519/177496512-4f4d428f-a191-4ed5-96bb-e9fce4692b57.png">

5. 로그아웃시 바로 로그인 화면으로 이동

</details>

#
### 진행 상황
##### 게시글 CRUD 완료 게시글 페이징 및 검색 완료( 전체글, 타이틀 검색)
##### 사용자 회원가입, 로그인, 회원 정보수정, 회원탈퇴 완료
##### 댓글 작성, 삭제 완료


    
  



