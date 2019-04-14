<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <title>Ricerca</title>
</head>
<body onload="showAll()">
    <div>
        <input id="search" type="text" name="searchText"/>
        <input type="button" value="Cerca per codice" onclick="searchCodice()"/>
        <input type="button" value="Cerca per categoria" onclick="searchCategoria()"/>
        <input type="button" value="Cerca per descrizione" onclick="searchDescrizione()"/>
        <input type="button" value="Reset filtri" onclick="showAll()"/>
    </div>
    <br/>
    <div id="result">

    </div>
    <br/>
    <a href="/cart"><button>Vai al carrello</button></a>
    <script>
        const baseUrl = "/api/product";

        function showAll() {
            executeFetch(baseUrl);
        }

        function searchCodice() {
            const element = document.getElementById("search");
            const url = baseUrl+"/codice/"+element.value;
            executeFetch(url);
        }

        function searchCategoria() {
            const element = document.getElementById("search");
            const url = baseUrl+"/categoria/"+element.value;
            executeFetch(url);
        }

        function searchDescrizione() {
            const element = document.getElementById("search");
            const url = baseUrl+"/descrizione/"+element.value;
            executeFetch(url);
        }

        function executeFetch(url) {
            fetch(url)
                .then(result => result.json())
                .then(json => processResult(json))
                .catch(e => console.log(e));
        }

        function processResult(jsonResult) {
            const result = document.getElementById("result");
            result.innerHTML = "";
            const table = createNode("table");
            append(table, generateTableHeader());
            table.border = "1";
            for (let i = 0; i < jsonResult.length; i++) {
                const row = generateRow(jsonResult[i]);
                append(table, row);
            }
            append(result, table);
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
            descrTd.textContent = "Descrizione";
            append(tr, descrTd);
            const categoriaTd = createNode("td");
            categoriaTd.textContent = "Categoria";
            append(tr, categoriaTd);
            const sottocategoriaTd = createNode("td");
            sottocategoriaTd.textContent = "Sottocategoria";
            append(tr, sottocategoriaTd);
            const acquistaTd = createNode("td");
            acquistaTd.textContent = "Acquista";
            append(tr, acquistaTd);
            return tr;
        }

        function generateRow(rowJson) {
            const tr = createNode("tr");
            const idTd = createNode("td");
            idTd.textContent = rowJson.id;
            append(tr, idTd);
            const nameTd = createNode("td");
            nameTd.textContent = rowJson.name;
            append(tr, nameTd);
            const descrTd = createNode("td");
            descrTd.textContent = rowJson.descrizione;
            append(tr, descrTd);
            const categoriaTd = createNode("td");
            categoriaTd.textContent = rowJson.categoria;
            append(tr, categoriaTd);
            const sottocategoriaTd = createNode("td");
            sottocategoriaTd.textContent = rowJson.sottocategoria;
            append(tr, sottocategoriaTd);
            const acquistaTd = createNode("td");
            const acquistaBtn = createNode("button");
            acquistaBtn.textContent = "Acquista";
            acquistaBtn.onclick = () => buyProduct(rowJson.id);
            append(acquistaTd, acquistaBtn);
            append(tr, acquistaTd);
            return tr;
        }

        function buyProduct(idProduct) {
            const cartId = '<%=session.getAttribute("cartId")%>';
            const url = "/api/cart/add/"+cartId+"/"+idProduct;
            fetch(url)
                .then(result => {console.log(result)})
                .catch(err => console.log(err));
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