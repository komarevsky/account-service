<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>AccountService is running.</h2>
<h3>Server Management</h3>
<a href="<c:url value="/statistics/"/>">Statistics Management</a>
<br/>

<h3>Client Management</h3>
<p>
To call Account Service methods on client machine please do the following:
<ul>
    <li>Install java7 on client machine</li>
    <li>Copy client-[version]-jar-with-dependencies.jar file on client machine</li>
    <li>Run client jar in console:
        <br/>
        java -jar client-[version]-jar-with-dependencies.jar --rCount=[x] --wCount=[y] --idList=[idList] --url=http://[ip]:[port]/account-service/account-service
        <br/>
        where [version] is jar version. For example 1.0.0-SNAPSHOT
        <br/>
        [x] - number of readers. For example 3
        <br/>
        [y] - number of writers. For example 1
        <br/>
        [idList] - list of Ids to use for reading/writing. For example 1,2,3
        <br/>
        [ip] - ip address of this machine. For example 192.168.172.128
        <br/>
        [port] - port used by this Application Server. For example 8080
    </li>
    <li>Press Enter to complete work of client</li>
</ul>
</p>

</body>
</html>
