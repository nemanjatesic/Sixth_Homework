<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Ocenili ste asistenta</h1>
<br>
<form method="get" action="http://localhost:8080/myServlet">
    <label>Pogledajte listu asistenata : </label>
    <input type="submit" name="submit" value="Pogledaj listu" class="submit-button"/>
</form>
<br>
<form method="get" action="http://localhost:8080/">
    <label>Ocenite novog asistenta : </label>
    <input type="submit" name="submit" value="Oceni novog" class="submit-button"/>
</form>



</body>
</html>
