<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    String user_id = CmmUtil.nvl((String)session.getAttribute("user_id"));

    String res = CmmUtil.nvl((String)request.getAttribute("res"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인 결과창</title>

<%
    String msg ="";

    if(res.equals("1")){
        msg = user_id + "님 로그인 완료";
    }else{
        msg = " 시스템 문제 발생. 잠시후 다시 시도해 주세요";
    }
%>
</head>
<body>
<%=msg%>
</body>
</html>