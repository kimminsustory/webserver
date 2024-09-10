# Tmax WAPL Web Application

이 프로젝트는 CentOS 9에서 Kubernetes 클러스터(Master, Worker1, Worker2)를 기반으로 구축된 웹 애플리케이션입니다. Tibero7 데이터베이스와 Tomcat 웹 서버를 사용하여 구현되었습니다. 주요 기능은 사용자 회원가입, 로그인, 2차 인증, 그리고 마이페이지에서의 캘린더 서비스 제공입니다. 모든 페이지의 오른쪽 상단에는 실시간 날씨 정보가 연동됩니다.

## 목차
- [프로젝트 구조](#프로젝트-구조)
- [설치 및 설정](#설치-및-설정)
- [사용법](#사용법)
- [기여 방법](#기여-방법)
- [라이센스](#라이센스)

## 프로젝트 구조

- **홈 화면**
  - "I Love Tmax WAPL ♡" 문구 표시
  - 회원가입, 로그인 버튼 제공
  - 회원가입 버튼 클릭 시 회원가입 화면으로, 로그인 버튼 클릭 시 로그인 화면으로 이동

- **회원가입 화면**
  - ID, PW, Email 입력 필드
  - 등록 버튼 클릭 시 Tibero7 DB에 데이터 삽입
  - 이미 등록된 ID일 경우 "등록된 ID입니다!" 메시지 출력 후 페이지 유지
  - 등록되지 않은 ID일 경우 "회원가입완료!" 메시지 출력 후 로그인 화면으로 이동

- **로그인 화면**
  - Tibero7에 등록된 ID와 PW 입력 필드
  - ID와 PW가 DB에 존재할 경우 2차 인증 진행
  - "등록하신 이메일로 인증번호를 보내드렸어요" 메시지와 인증번호 입력 필드
  - 인증번호 확인 버튼 클릭 시, 인증번호가 맞을 경우 "환영합니다!" 메시지 출력 후 마이페이지로 이동
  - 인증번호가 틀릴 경우 "인증번호를 확인해주세요!" 메시지 출력 후 페이지 유지
  - DB에 존재하지 않는 ID일 경우 "존재하지 않는 ID입니다!" 메시지 출력 후 페이지 유지

- **마이페이지 화면**
  - "My Calendar" 하단에 캘린더 서비스 제공
  - 하단에 로그아웃 버튼 제공, 클릭 시 홈 화면으로 이동

## 설치 및 설정

### 1. 시스템 요구 사항

- CentOS 9
- Kubernetes 클러스터 (Master, Worker1, Worker2)
- Tibero7 데이터베이스
- Apache Tomcat 웹 서버
- Java 17 이상

### 2. 프로젝트 설정

1. **클러스터 구성**:
   - CentOS 9에서 Kubernetes 클러스터를 Master, Worker1, Worker2 노드로 구성합니다.

2. **Tibero7 데이터베이스 설정**:
   - Tibero7을 설치하고 사용자 테이블을 생성합니다. 예를 들어, `users` 테이블을 생성하여 ID, PW, Email 컬럼을 포함합니다.

3. **Tomcat 웹 서버 설정**:
   - Tomcat을 설치하고 `/usr/share/tomcat/webapps/your-app` 디렉토리에 프로젝트 파일을 배치합니다.
   - `WEB-INF` 디렉토리 아래에 `web.xml` 파일을 생성하여 서블릿 및 JSP 설정을 추가합니다.

4. **실시간 날씨 정보 연동**:
   - OpenWeatherMap API 키를 발급받아 `application.properties` 또는 `web.xml`에 설정합니다.
   - 각 JSP 페이지에서 날씨 정보를 표시하도록 설정합니다.

## 사용법

### 1. 애플리케이션 실행

```bash
cd /usr/share/tomcat/webapps/your-app
mvn clean install -DskipTests
mvn spring-boot:run
