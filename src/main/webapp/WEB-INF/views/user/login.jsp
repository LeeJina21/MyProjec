<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인 화면</title>

    <script type="text/javascript">
        function doLoginUserCheck(f){
            if (f.user_id.value==""){
                alert("아이디를 입력하세요");
                f.user_id.focus();
                return false;
            }
            if (f.user_pw.value==""){
                alert("비밀번호를 입력하세요");
                f.user_pw.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<form name="f" method="post" action="/user/getUserLoginCheck" onsubmit="return doRegUserCheck(this);">
<table border="1">
    <col width="150px">
    <col width="150px">
    <col width="150px">
    <col width="150px">
    <tr>
        <td>아이디</td>
        <td colspan="3"><input type="text" name="user_id"></td>
        <td>비밀번호</td>
        <td colspan="3"><input type="password" name="user_pw"></td>
    </tr>
</table>
</form>
<input type="submit" value="로그인"/>
</body>
</html>