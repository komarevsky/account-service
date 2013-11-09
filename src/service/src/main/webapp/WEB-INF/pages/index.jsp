<%@page import="com.freebetbot.as.service.statistics.StatisticsManager"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Statistics:</h2>
CurrentlyServedGetAmount: <%= StatisticsManager.getInstance().getCurrentlyServedGetAmount() %>
<br/>
CurrentlyServedAddAmount: <%= StatisticsManager.getInstance().getCurrentlyServedAddAmount() %>
<br/>
TotalCallsGetAmount: <%= StatisticsManager.getInstance().getTotalCallsGetAmount() %>
<br/>
TotalCallsAddAmount: <%= StatisticsManager.getInstance().getTotalCallsAddAmount() %>
<br/>

<form method="POST" action="<c:url value="/statistics/store"/>">
    <input type="submit" value="Store Statistics" />
</form>
<form method="POST" action="<c:url value="/statistics/reset"/>">
    <input type="submit" value="Reset Statistics" />
</form>

</body>
</html>
