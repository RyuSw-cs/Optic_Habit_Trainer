# Optic_Habit_Trainer
### 누진다초점 렌즈 사용자의 시습관을 트레이닝 합니다.🔍
CustomView와 Camera2 API를 사용하여 단계별로 시습관을 관찰, 학습, 비교를 합니다.
</br></br>
<img width="400" src="https://user-images.githubusercontent.com/72602912/148717627-4763c16e-0f9f-4f3e-b6f1-4716d10894a0.png">
</br>
<hr>

### 📱 플랫폼
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=white"/>


### 🙆‍♂️ 언어
<img src="https://img.shields.io/badge/Kotlin-0095D5?style=flat-square&logo=Java&logoColor=white"/> 
</br>

### ⚙ 기술 스택
#### Camera2 API</br>
<img width="830" alt="paging" src="https://user-images.githubusercontent.com/72602912/148720375-47ad296a-2621-48e6-9339-eede27c1adce.png">
- 페이징 된 데이터의 메모리 내 <strong>캐싱</strong>을 통해 효율적인 리소스 활용</br>
- <strong>비동기</strong> 처리</br>
- 새로고침 및 재시도 기능, 오류처리를 지원</br>
</br>

#### 주요 기술🛠

<strong>Custom View &nbsp; MediaRecoder &nbsp; Thread &nbsp;</strong>

<hr>

### 📅 프로젝트 구성

![structure](https://user-images.githubusercontent.com/72602912/148727205-b85fe758-73a9-4478-a9b4-fc8c7616dd33.PNG)

- **models**
  - HTTP Method를 사용하여 데이터를 가져올 모델 패키지

- **api -> ApiService**
  - HTTP Method를 구현한 인터페이스
  - Repository의 데이터를 가져오므로 GET만 생성

- **module**
  - Retrofit 객체를 빌드 및 생성

- **viewModel**
  - 데이터 갱신처리 요청 및 참조
  - keyword 값에 따른 데이터 변경

- **paging**
  - Paging 라이브러리 구현

- **ui**
  - 검색 기능을 사용할 UI
  - Databinding 사용
  - Observer를 활용하여 데이터가 변경 시 작동

- **adapter**
  - PagingDataAdapter 구현
  - 페이징 기능을 구현하기 위한 RepoLoadStateAdapter 구현

