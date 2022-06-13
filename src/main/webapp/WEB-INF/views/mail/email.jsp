<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form name="form" id="form" method="post" action="/mail/emailLogic">
    <table border="1"> <!--table 태그 쓰지 말것 폼테그 써라... div나...-->
        <col width="100px" />
        <col width="500px" />
        <tr>
            <td>제목</td>
            <td><label for="title"></label><input type="text" name="title" id="title" maxlength="100" style="width: 450px" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <label>
                    <textarea ID="contents" name="contents" style="width: 550px; height: 400px" cols="" rows=""></textarea>
                </label>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <input type="submit" value="메일 보내기" />
                <input type="reset" value="다시 작성" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>