<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
</head>
<body onload="showCart()">
<div id="result">

</div>
<br/>
<div>
    <button onclick="pay()">Vai al pagamento</button>
    <button onclick="deleteAllProducts()">Svuota il carrello</button>
    <a href="/search"><button>Torna indietro</button></a>
</div>
<script>

    function showCart() {
        const cartId = '<%=session.getAttribute("cartId")%>';
        const url = "/api/cart/"+cartId;
        fetch(url)
            .then(result => result.json())
            .then(json => processResult(json))
            .catch(err => console.log(err));
    }

    function processResult(jsonResult) {
        const resultDiv = document.getElementById("result");
        resultDiv.innerHTML = "";
        const table = createNode("table");
        table.border = "1";
        append(table, generateTableHeader());
        for (let i = 0; i < jsonResult.length; i++) {
            append(table, processRow(jsonResult[i]));
        }
        append(resultDiv, table);
    }

    function generateTableHeader() {
        const tr = createNode("tr");
        const idTd = createNode("td");
        idTd.textContent = "Id prodotto";
        append(tr, idTd);
        const nameTd = createNode("td");
        nameTd.textContent = "Nome prodotto";
        append(tr, nameTd);
        const descrTd = createNode("td");
        descrTd.textContent = "Prezzo";
        append(tr, descrTd);
        const rimuoviTd = createNode("td");
        rimuoviTd.textContent = "Rimuovi";
        append(tr, rimuoviTd);
        return tr;
    }

    function processRow(rowJson) {
        const tr = createNode("tr");
        const idTd = createNode("td");
        idTd.textContent = rowJson.id;
        append(tr, idTd);
        const nameTd = createNode("td");
        nameTd.textContent = rowJson.name;
        append(tr, nameTd);
        const descrTd = createNode("td");
        descrTd.textContent = "30€";
        append(tr, descrTd);
        const rimuoviTd = createNode("td");
        const rimuoviBtn = createNode("button");
        rimuoviBtn.textContent = "Elimina";
        rimuoviBtn.onclick = () => deleteProduct(rowJson.id);
        append(rimuoviTd, rimuoviBtn);
        append(tr, rimuoviTd);
        return tr;
    }

    function deleteProduct(id) {
        const cartId = '<%=session.getAttribute("cartId")%>';
        const url = "/api/cart/delete/"+cartId+"/"+id;
        fetch(url)
            .then(res => {
                console.log(res);
                showCart();
            })
            .catch(err => console.log(err));
    }

    function deleteAllProducts() {
        const cartId = '<%=session.getAttribute("cartId")%>';
        const url = "/api/cart/delete/"+cartId;
        fetch(url)
            .then(res => {
                console.log(res);
                showCart();
                })
            .catch(err => console.log(err));

    }

    function pay() {
        alert("Someone has been truffato")
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