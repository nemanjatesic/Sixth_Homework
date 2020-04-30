<%--
  Created by IntelliJ IDEA.
  User: Nemanja
  Date: 30. 4. 2020.
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Assistants :</h1>
<br>
<c:choose>
    <c:when test="${fn:length(assistants) == 0}">
        <h1>Nema ni jednog unosa</h1>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th>Assistents</th>
                <th style="background-color: grey">Scores</th>
            </tr>
            <c:forEach var="assistant" items="${assistants}">
                <tr>
                    <td>
                        <c:out value = "${assistant.name}"/>
                        <c:out value = "${assistant.lastName}"/>
                    </td>
                    <td style="background-color: grey">
                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${assistant.points}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
