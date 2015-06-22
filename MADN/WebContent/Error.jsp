<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>

<br><br>
<div id="header">
</div>

<div id="form">

	<div id="error">
		<h1>ERROR</h1>
		
    <% if (request.getAttribute("fehler") == null) {
				//out.print("Fehler");
			} else {
				out.print(request.getAttribute("fehler"));
			}
    %>
</div>


</div>

</body>
</html>