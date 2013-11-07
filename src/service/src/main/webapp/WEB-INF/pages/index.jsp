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

<!--form name="myform" method="POST"-->
    <input type="submit" value="Reset Statistics" formaction="<c:url value="/statistics/reset"/>">
    <br/>
    <input type="submit" value="Store Statistics" formaction="<c:url value="/statistics/store"/>">
<!--/form-->

</body>
</html>
