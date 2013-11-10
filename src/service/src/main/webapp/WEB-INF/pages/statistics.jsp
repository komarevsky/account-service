<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    
<head>
<script type="text/JavaScript">
function timedRefresh(timeoutPeriod) {
	setTimeout("location.reload(true);",timeoutPeriod);
}
</script>
</head>

<body onload="JavaScript:timedRefresh(10000);">
    
<p>Note: this page is refreshed every 10 seconds</p>
    
<h2>Statistics:</h2>
CurrentlyServedGetAmount: <c:out value="${currentlyServedGetAmount}"/>
<br/>
CurrentlyServedAddAmount: <c:out value="${currentlyServedAddAmount}"/>
<br/>
TotalCallsGetAmount: <c:out value="${totalCallsGetAmount}"/>
<br/>
TotalCallsAddAmount: <c:out value="${totalCallsAddAmount}"/>
<br/>

<a href="<c:url value="/"/>">Back to index</a>
<br/>
<br/>

<form method="POST" action="<c:url value="/statistics/store"/>">
    <input type="submit" value="Store Statistics" />
</form>
<form method="POST" action="<c:url value="/statistics/reset/current"/>">
    <input type="submit" value="Reset CurrentlyServed Counters" />
</form>
<form method="POST" action="<c:url value="/statistics/reset/total"/>">
    <input type="submit" value="Reset Total Counters" />
</form>
    
</body>
</html>
