# culticare-main-app
### culticare backend 메인 레포지토리입니다. 

# 👨‍👩‍👧 CultiCare

![sodapdf-converted_Page_1](https://github.com/user-attachments/assets/71b2ee05-80e5-4543-a3f4-381d1c983f37)

## 팀 소개
![sodapdf-converted_Page_2](https://github.com/user-attachments/assets/76ea2db2-c217-4e36-8088-f06c90760898)

## 서비스 한줄 소개

#### 다문화가정 구성원이 한국사회에 잘 적응할 수 있도록 감정관리, 정보제공, 커뮤니티 기능을 제공하는 앱
![sodapdf-converted_Page_6](https://github.com/user-attachments/assets/520a263d-107d-434d-8e68-284e8b900b1a)

## 핵심 기능 - IA

![sodapdf-converted_Page_7](https://github.com/user-attachments/assets/4dfec578-e243-4afc-8be8-538b8f5cab2d)

### 기능 상세

1️⃣ 정서 분석 및 말동무 상담 기능
![sodapdf-converted_Page_8](https://github.com/user-attachments/assets/9f53c607-9e2c-4cd1-93a3-8f8a904097bb)
2️⃣ 커뮤니티 기능
![sodapdf-converted_Page_9](https://github.com/user-attachments/assets/d49a7d2e-9384-4383-a4fc-3ecc571ac649)
3️⃣ 정보 조회
![sodapdf-converted_Page_10](https://github.com/user-attachments/assets/88acbe5f-9ef3-452c-bcdf-f71c212c286c)
4️⃣ 문화탐구 카드뉴스 제공
![sodapdf-converted_Page_11](https://github.com/user-attachments/assets/d6707005-5b20-4a53-a370-16c5b9749091)

## 🏛️ System Architecture

![sodapdf-converted_Page_13](https://github.com/user-attachments/assets/d5a4f70b-ee2b-41b8-a761-d02f7b123cb3)

## 📜 ERD 설계도

![sodapdf-converted_Page_14](https://github.com/user-attachments/assets/4ffc8a5d-3a12-4a02-a235-afe59f01cfd1)

## 💻 Technology

![sodapdf-converted_Page_12](https://github.com/user-attachments/assets/d6f87424-add5-43c5-9e93-d58264dfde0c)

## 🔎 개발 방법

![sodapdf-converted_Page_14](https://github.com/user-attachments/assets/4ffc8a5d-3a12-4a02-a235-afe59f01cfd1)
![sodapdf-converted_Page_15](https://github.com/user-attachments/assets/e184fa25-d351-4960-956d-e31eb718140a)
![sodapdf-converted_Page_16](https://github.com/user-attachments/assets/a27e1133-9226-4825-8b33-93b32f5ad93b)

## 🔗 시연 영상
---
#### [CultiCare 시연영상](https://m.youtube.com/watch?v=R7GSRKPJUSE)

### ✉️ Commit Messge Rules
**서버** 들의 **Git Commit Message Rules**

  - 반영사항을 바로 확인할 수 있도록 작은 기능 하나라도 구현되면 커밋을 권장합니다.
  - 기능 구현이 완벽하지 않을 땐, 각자 브랜치에 커밋을 해주세요.
    
### 📌 Commit Convention
**[태그] 제목의 형태**

| 태그 이름 | 설명 |
|-----------|------|
| FEAT      | 새로운 기능을 추가할 경우 |
| FIX       | 버그를 고친 경우 |
| CHORE     | 짜잘한 수정 |
| DOCS      | 문서 수정 |
| INIT      | 초기 설정 |
| TEST      | 테스트 코드, 리펙토링 테스트 코드 추가 |
| RENAME    | 파일 혹은 폴더명을 수정하거나 옮기는 작업인 경우 |
| STYLE     | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| REFACTOR  | 코드 리팩토링 |



### 커밋 타입
  - `[태그] 설명` 형식으로 커밋 메시지를 작성합니다.
  - 태그는 영어를 쓰고 대문자로 작성합니다.

예시 >
```
  [FEAT] 검색 api 추가
```  
### 💻 Github mangement
WorkFlow : **Gitflow Workflow**

  - Develop, Feature, Hotfix 브랜치

  - 개발(develop): 기능들의 통합 브랜치

  - 기능 단위 개발(feature): 기능 단위 브랜치

  - 버그 수정 및 갑작스런 수정(hotfix): 수정 사항 발생 시 브랜치

  - 개발 브랜치 아래 기능별 브랜치를 만들어 작성합니다.

### ✍🏻 Code Convention
[에어비앤비 코드 컨벤션](https://github.com/airbnb/javascript)

### 📍 Gitflow 규칙
- Develop에 직접적인 commit, push는 금지합니다.
- 커밋 메세지는 다른 사람들이 봐도 이해할 수 있게 써주세요.
- 작업 이전에 issue 작성 후 pullrequest 와 issue를 연동해 주세요.
- 풀리퀘스트를 통해 코드 리뷰를 전원이 코드리뷰를 진행합니다.
- 기능 개발 시 개발 브랜치에서 feature/기능 으로 브랜치를 파서 관리합니다.
- feature 자세한 기능 한 가지를 담당하며, 기능 개발이 완료되면 각자의 브랜치로 Pull Request를 보냅니다.
- 각자가 기간 동안 맡은 역할을 전부 수행하면, 각자 브랜치에서 develop브랜치로 Pull Request를 보냅니다.
- develop 브랜치로의 Pull Request는 상대방의 코드리뷰 후에 merge할 수 있습니다.
  
### ❗️ branch naming convention
 - develop
 - feature/issue_number-도메인-http Method-api
 - fix/issue_number-도메인-http Method-api
 - release/version_number
 - hotfix/issue_number - Short Description
   
예시 >
```
  feature/#3-user-post-api
```
  
### 📋 Code Review Convention
- P1: 꼭 반영해주세요 (Request changes)
- P2: 적극적으로 고려해주세요 (Request changes)
- P3: 웬만하면 반영해 주세요 (Comment)
- P4: 반영해도 좋고 넘어가도 좋습니다 (Approve)
- P5: 그냥 사소한 의견입니다 (Approve)


### 🚀 Test Code Convention
1. given, when, then을 사용한다.
2. 테스트 메서드명은 다음과 같이 작성한다. -> 메서드명_테스트하고자하는상태_예상되는결과 (ex. giveCotton_CottonCountIs0_NotEnoughCotton)
3. 설마 이런 거까지 생각해야하나싶은 거까지 작성한다. (ex. 솜뭉치를 여러 개 줄 수 있다.)
4. 다수의 값을 다룰 때는 @ParameterizedTest를 활용한다.
