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

<body onload="JavaScript:timedRefresh(1000);">
    
<p>Note: this page is refreshed every second</p>
    
<h2>Statistics:</h2>
CurrentLoadGetAmount: <c:out value="${currentLoadGetAmount}"/> (invocations/sec)
<br/>
CurrentLoadAddAmount: <c:out value="${currentLoadAddAmount}"/> (invocations/sec)
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
<form method="POST" action="<c:url value="/statistics/reset"/>">
    <input type="submit" value="Reset Statistics" />
</form>
    
</body>
</html>
