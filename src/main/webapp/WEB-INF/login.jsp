<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
	<title>로그인 페이지</title>
</head>
<body>
<form action="/auth/joinProc" method="post">
	<sec:csrfInput/>
	WELCOME 로그인~~
	이메일 <input type="email" name="email" value="penekhu@gma.com"/><br />
	비번 <input type="password" name="password" value="asdasdasds" /> <br />
	<button>Login</button>
</form>
</body>
</html>