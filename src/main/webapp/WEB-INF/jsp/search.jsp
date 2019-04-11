<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="it">
<head>
    <title>Ricerca</title>
</head>
<body>
    <div>
        <input id="search" type="text" name="searchText"/>
        <input type="button" value="Cerca per codice" onclick="searchCodice()"/>
        <input type="button" value="Cerca per categoria" onclick="searchCategoria()"/>
        <input type="button" value="Cerca per descrizione" onclick="searchDescrizione()"/>
    </div>
    <div id="result">

    </div>
    <script>
        const baseUrl = "/api/product";

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
            return tr;
        }

        function generateRow(rowJson) {
            //TODO: Generare i td tramite le colonne del'oggetto json passato
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
            return tr;
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