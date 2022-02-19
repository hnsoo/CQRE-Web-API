<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
	<title>회원가입 페이지</title>
</head>
<body>
<form action="/register" method="post">
	<sec:csrfInput/>
	회원가입 welcome
	학번 <input type="text" name="studentId" value="20194581" /> <br />
	이메일 <input type="email" name="email" value="penekhu@gma.com"/> <br />
	비번 <input type="password" name="password" value="asdasdasds" /> <br />
	닉네임 <input type="nickname" name="nickname" value="nickN" /> <br />
	프로필 <input type="text" name="profile" text="qqaa" /> <br />
	<button>회원가입</button>
</form>
</body>
</html>