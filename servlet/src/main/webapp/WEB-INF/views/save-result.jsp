<%--
  Created by IntelliJ IDEA.
  User: alskd
  Date: 2025-01-09
  Time: 오후 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=${member.id}</li> <%-- request에 담긴 속성을 편리하게 사용한 것! --%>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href = "/index.html">메인</a>
</body>
</html>
