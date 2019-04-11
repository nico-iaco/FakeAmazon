<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrazione</title>
</head>
<body>
    <%--@elvariable id="userForm" type="com.iacovelli.fakeamazon.model.form.UserForm"--%>
    <form:form action="/register" modelAttribute="userForm" method="post">
        <form:label path="username">Email: </form:label>
        <form:input path="username" type="text" name="username" />
        <br/>
        <form:label path="password">Password: </form:label>
        <form:input path="password" type="password" name="password" />
        <br/>
        <input type="submit" name="Invia">
    </form:form>
</body>
</html>