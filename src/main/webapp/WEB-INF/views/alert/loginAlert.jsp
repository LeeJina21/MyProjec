<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    String user_id = CmmUtil.nvl((String)session.getAttribute("user_id"));
    String res = CmmUtil.nvl((String)request.getAttribute("res"), "0");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    if(res.equals("1")){
%>
<select>
    alert('<%=user_id%>님 로그인 성공')
    document.location.href="/index"
</select>
<%
    }else{
%>
<select>
    alert('로그인 실패')
    document.location.href="/user/login"
</select>
<%
    }
%>
</body>
</html>