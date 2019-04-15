<%@ page import="com.iacovelli.fakeamazon.model.form.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<html lang="it">
<head>
    <title>Inserimento prodotti</title>
</head>
<body>
    <%--@elvariable id="productForm" type="com.iacovelli.fakeamazon.model.form.ProductForm"--%>
    <form:form method="post" action="/products" modelAttribute="productForm">
        <table>
            <tr>
                <td><form:label path="title">Titolo prodotto:</form:label></td>
                <td><form:input path="title" type="text" /></td>
            </tr>
            <tr>
                <td><form:label path="description">Descrizione:</form:label></td>
                <td><form:input path="description" type="text" /></td>
            </tr>
            <tr>
                <td><form:label path="categoria">Categoria: </form:label></td>
                <td>
                    <form:select id="category" path="categoria" onchange="selectChanged()">
                        <form:option value="Seleziona una categoria"/>
                        <form:options items="<%=categories%>" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="sottocategoria">Sottocategoria: </form:label></td>
                <td>
                    <form:select id="subcategory" path="sottocategoria">
                        <form:option value="Inserisci sottocategoria"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="1">
                    <input type="submit" value="Salva"/>
                </td>
            </tr>
        </table>
    </form:form>
    <script>
        function selectChanged() {
            const idElement = document.getElementById("category");
            const selectedValue = idElement.options[idElement.selectedIndex].value;
            console.log(selectedValue);
            const url = "/api/subcategory/"+selectedValue;
            console.log(url);
            fetch(url)
                .then(result => result.json())
                .then((json) => {
                    const sub = document.getElementById("subcategory");
                    sub.innerHTML = "";
                    for (let i = 0; i < json.length; i++) {
                        const option = createNode("option");
                        option.value = json[i];
                        option.textContent = json[i];
                        append(sub, option);
                    }
                })
                .catch(error => console.log(error));
        }
        function createNode(element) {
            return document.createElement(element); // Create the type of element you pass in the parameters
        }

        function append(parent, el) {
            return parent.appendChild(el); // Append the second parameter(element) to the first one
        }
    </script>
</body>
</html>