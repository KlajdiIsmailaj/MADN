<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Wait</title>
<!--<META HTTP-EQUIV="refresh" CONTENT="5">-->
</head>
<body>

<br>
<h1>Warte auf andere Spieler ...</h1>

<div id="form">


<%out.println(request.getAttribute("a")); %>
<%out.println(request.getAttribute("b")); %>





</div>


<form action="Wait" method="post">
<center><input id="weiterButtonWait" type = "submit" value = "weiter"></center>
</form>


</body>
</html>