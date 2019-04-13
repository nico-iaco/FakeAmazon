<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="it">
<head>
    <title>Login</title>
</head>
<body>
    <%--@elvariable id="userForm" type="com.iacovelli.fakeamazon.model.form.UserForm"--%>
    <form:form action="/login" method="post" modelAttribute="userForm">
        <table>
            <tr>
                <td><form:label path="username">Email: </form:label></td>
                <td><form:input path="username" type="text" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password: </form:label></td>
                <td><form:input path="password" type="password" /></td>
            </tr>
            <tr>
                <td colspan="1"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form:form>
    <script>
        const msg = '<%=request.getAttribute("exception")%>';
        if (msg !== "null") {
            alert(msg);
        }
    </script>
</body>
</html>