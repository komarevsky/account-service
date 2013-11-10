<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Statistics:</h2>
CurrentlyServedGetAmount: <c:out value="${currentlyServedGetAmount}"/>
<br/>
CurrentlyServedAddAmount: <c:out value="${currentlyServedAddAmount}"/>
<br/>
TotalCallsGetAmount: <c:out value="${totalCallsGetAmount}"/>
<br/>
TotalCallsAddAmount: <c:out value="${totalCallsAddAmount}"/>
<br/>

<br/>
<a href="<c:url value="/"/>">Back to index</a>
<br/>

<form method="POST" action="<c:url value="/statistics/store"/>">
    <input type="submit" value="Store Statistics" />
</form>
<form method="POST" action="<c:url value="/statistics/reset"/>">
    <input type="submit" value="Reset Statistics" />
</form>
    
</body>
</html>
