# 라이브버디 (Libary + Buddy)
## [ AI 기반 사석화 방지 통합 관리 시스템 ]
- The full code is in the 'master branch'
- This is a simplified one. I uploaded the full final report file.
   
## 📱 앱 기능 및 UI
### 1. 앱 로그인 화면
![ll1](https://user-images.githubusercontent.com/61315014/129152446-2606ef67-e373-437f-b303-719f194a9240.png)

- 간편한 회원 가입 및 로그인과 같은 용이한 접근성을 위해 카카오톡 로그인 API 를 활용하여
로그인을 구현했다.   
- 이용자가 로그인을 하게 되면 DB 에서 로그인 시 자동으로 부여되는 랜덤 넘버인 userID 로
이용자들이 각각 구분되고, 관리자는 특정 userID 에 어플리케이션 페이지의 접근 권한을 부여하여
구분한다.   
### 2. 로그인 후, 앱 초기 화면   
![l2](https://user-images.githubusercontent.com/61315014/129152564-87c98c68-4b4e-416c-a00a-821b5a74cb90.png)
- 상단 검색창에 지역 명을 검색하여 찾을 수 있다.   
- 전국의 도서관 위치를 대한민국 지도로 가시화하여 표현하기 위해 Google Map API 를 사용했다.    
- 주요 도시들을 Marker 로 설정 후, Marker 클릭 시 해당 지역의 공용공간의 리스트를 출력한다.   
### 3. 기관/공용공간 선택 화면
![l3](https://user-images.githubusercontent.com/61315014/129152644-af66d940-a954-4925-9881-916d1f9b5949.png)
1. 대학 리스트 화면   
- 상단 검색창에 대학(기관) 명을 검색하여
찾을 수 있다.
- 앱 초기화면에서 지역 선택 후, 해당 지역의
도서관 리스트를 출력한 화면으로, 대학
선택 시 해당 대학의 공용공간 리스트를
출력하는 화면으로 넘어간다.   
2. 공용공간 리스트 화면   
- 상단 검색창에 대학(기관)의 공용공간 명을
검색하여 찾을 수 있다.
- 대학 선택 후, 해당 대학의 공용공간
리스트를 출력한 화면으로, 공용공간 선택
시 해당 공용공간의 좌석 현황 화면으로
넘어간다
### 4. 좌석 현황 화면   
![l4](https://user-images.githubusercontent.com/61315014/129152839-6cb64514-3017-4c16-84ac-f93677412eba.png)

(예시) 인하대학교 - 정석학술정보관 - 제 3 열람실, 
인하대학교 - 60 주년 기념관 – 월천 라운지
- 이용 중인 좌석/사석화 의심 좌석/이용 가능한 좌석을 각 회색/적색/청색 총 3 가지의 다른
색상으로 분류 및 표시하여 가독성을 높였다.
- 하단에 내비게이션 바를 배치하여 통계/홈/마이페이지로 이동이 가능하다.
- 상단의 이용률 바로 혼잡도를 파악할 수 있다.
### 5. 통계 페이지
![l5](https://user-images.githubusercontent.com/61315014/129152919-4f8a1f0f-5e99-45c1-ad84-9a3c8fc8a91f.png)

(예시) 인하대학교 통계 페이지
- 최상단 ’추천하는 장소’에 현재 좌석 점유율이 가장 낮은 공간을 출력한다.
- 공간 별 이용 가능, 이용 중, 의심 좌석 비율 및 시간대별 이용률 확인이 가능하다.
- 이용 가능, 이용 중, 의심 좌석 비율을 Pie chart 로, 시간대별 이용률을 Bar chart 로 표현하기 위해
MP android chart 오픈 소스를 사용하여 구현했다.

### 6. 팝업 알림
![팝업알림](https://user-images.githubusercontent.com/61315014/129154411-39e47b9c-ca69-4c5d-9324-242d8ff2e24c.png)

### 7. 마이 페이지
![l6](https://user-images.githubusercontent.com/61315014/129153028-a985c670-6b5c-4baa-b47f-8e8db6a19b96.png)
1. 사용자용 마이페이지
- 사용자 관련 정보와 좌석 정보를 확인할 수
있다.
- 하단 ‘즐겨 찾는 장소’에 사용자가 공용공간
리스트에서 즐겨찾기로 등록한 장소를
출력한다.
- 좌석의 이용 시간, 이용 상태 등을 표시한다.

2. 관리자용 마이페이지
- 관리자 관련 정보를 확인할 수 있다.
- 관리자가 속한 기관의 각 공용공간에 설치된
CCTV 를 확인할 수 있다.
## 프로젝트 개요
   
인하대학교를 포함한 대부분의 대학의 경우 사석화 방지를 위해 도서관 열람실을 이용할 때
온라인 좌석 예약제를 실시하고 있지만, 여전히 사석화 문제에 대한 불만이 제기되고 있다. 따라서
좌석 예약 시스템만으로 사석화 문제를 근본적으로 해결할 수 없으며 발권 제도가 없는 카페, 강의실, 
라운지, 대기 좌석 등 아무런 제약 없이 자유롭게 이용할 수 있는 공간에서는 좌석 부족 문제와
사석화 문제가 더욱 심각한 상황이다. 이에 대한 해결책으로 예약 시스템이 없는 넓은 공간에서도
적은 인력으로도 관리가 가능하고, 객체 인식을 활용하여 사석화를 방지하는 서비스를 제공하고자
한다. 따라서, 본 서비스를 통해 이용 가능한 좌석을 최대한 확보, 회전율을 높이고 낭비되는
인건비를 줄여 궁극적으로는 공용공간의 자동화된 관리와 효율적인 사용을 목적으로 한다
   
## 전체 스키마
![전체스키마](https://user-images.githubusercontent.com/61315014/129154480-27ec5e21-1f8c-43f5-a566-c28ed5640821.png)

## 공용공간의 사석화 원인 및 문제점 파악 - 공용공간 부족
 
![1](https://user-images.githubusercontent.com/61315014/129150903-f5f5bcdd-e4ce-47a8-8ac6-8dfec2d1cada.png)

## 본 서비스와 기존 특허 분석표
![f1](https://user-images.githubusercontent.com/61315014/129154223-a8d72325-c07d-4dfd-852f-06a5e101d469.png)
## 요구사항 명세서

![f2](https://user-images.githubusercontent.com/61315014/129154267-706c7ae8-e368-4e5d-a201-f6a390e5ccca.png)
## 간트 차트 (Gaunt Chart)
![f3](https://user-images.githubusercontent.com/61315014/129154306-8b2b4a85-fcf3-4bd6-9ff1-b150eaebb388.png)

## Use case diagram
![f4](https://user-images.githubusercontent.com/61315014/129154360-ae45fe3a-5466-4a47-8b80-bffeb4f09812.png)

## 요구사항 명세서
![명세서](https://user-images.githubusercontent.com/61315014/129154451-61d802e3-826a-4d97-beaa-eb0e2bf867be.png)

## 전체 구성도
![전체구성도](https://user-images.githubusercontent.com/61315014/129154507-263c0d99-c944-43c9-8910-02e2c8f94016.png)


