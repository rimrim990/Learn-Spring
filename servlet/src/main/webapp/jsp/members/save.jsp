<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: yerim
  Date: 2023/03/08
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 자바 코드 입력 가능 -->
<%
  // req, res 사용 가능
  // JSP 도 서블릿으로 변환되기 때문
  MemberRepository  memberRepository = MemberRepository.getInstance();
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);

  // 비즈니스 로직의 처리와 view 와 관련된 로직이 한 파일에 포함되어 있음
  // 비즈니스 로직은 다른 곳에서 처리하고, JSP 는 화면 (View) 을 그리는 일에 집중하도록 하자.
  // MVC 패턴
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
  <li>id=<%member.getId();%></li>
</ul>
</body>
</html>
