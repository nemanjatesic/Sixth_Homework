<%--
  Created by IntelliJ IDEA.
  User: Nemanja
  Date: 30. 4. 2020.
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Jednostavna forma</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>
<h1>Ocenjivanje asistenata</h1>

<form method="post" action="http://localhost:8080/myServlet">
    <label for="Ime">Ime:</label>
    <input type="text" name="ime" id="ime" required/>

    <label for="Prezime">Prezime:</label>
    <input type="text" name="prezime" id="prezime" required/>

    <label for="Ocena">Ocena:</label>
    <input type="text" name="ocena" id="ocena" required/>

    <input type="submit" name="submit" value="Glasaj" class="submit-button"/>
</form>
<br>
<form method="get" action="http://localhost:8080/myServlet">
    <label>Svi asistenti: </label>
    <input type="submit" name="submit" value="Pogledaj listu" class="submit-button"/>
</form>

</body>

</html>
