# 스프링 MVC - 백엔드 핵심 기술 1
- 서블릿

## 서버에서 처리해야 하는 업무

### 웹 애플리케이션 서버 직접 구현할 경우
```http request
POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

username=kim&age=20
```
- 서버 TCP/IP 연결 대기, 소켓 연결
- HTTP 요청 메세지를 파싱해서 읽기
- POST 방식, /save URL 인지
- Content-Type 확인
- HTTP 메세지 바디 내용 파싱
  - username, age 데이터를 사용할 수 있게 파싱
- 저장 프로세스 실행
- **비즈니스 로직 실행**
  - **데이터베이스에 저장 요청**
- HTTP 응답 메세지 생성 시작
  - HTTP 시작 라인 생성
  - Header 생성
  - 메시지 바디에 HTML 생성해서 입력
- TCP/IP 응답 전달, 소켓 종료
```http request
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8
Content-Length: 2423

<html>
  <body>...</body>
</html>
```

### 서블릿을 지원하는 WAS 사용
- 비즈니스 실행 로직을 제외한 모든 부분을 자동화하여 실행

## 서블릿
```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        // 애플리케이션 로직
    }
}
```
- urlPattern(/hello) 의 URL 이 호출되면 서블릿 코드가 실행
- HTTP 요청 정보를 편리하게 사용할 수 있는 `HttpServletRequest`
  - HTTP 메세지를 직접 파싱하지 않아도 된다
- HTTP 응답 정보를 편리하게 제공할 수 있는 `HttpServletResponse`
  - HTTP 스펙을 몰라도 응답 정보를 편리하게 만들 수 있다
- 개발자는 HTTP 스펙을 매우 편리하게 사용

### HTTP 요청, 응답 흐름
**HTTP 요청 시**
- WAS 는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
- 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용
- 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력
- WAS 는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성

## 서블릿 컨테이너
- 톰캣처럼 서블릿을 지원하는 WAS 를 서블릿 컨테이너라고 함
- 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리
- 서블릿 객체는 **싱글톤으로 관리**
  - 고객의 요청이 올 때마다 계속 객체를 생성하는 것은 비효율
  - 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용
  - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
  - **공유 변수 사용 주의**
  - 서블릿 컨테이너 종료시 함께 종료
- JSP 도 서블릿으로 변환 되어서 사용
- 동시 요청을 위한 멀티 쓰레드 처리 지원