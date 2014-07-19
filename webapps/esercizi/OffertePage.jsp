<!--	OffertaPage.jsp: 
	La JSP visualizza le offerte relative all'immobile selezionato, successivamente all'avvenuta registrazione
    del proprietario dell'immobile per cui si gestisce la vendita
-->

<!-- Eseguo gli import necessari -->
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>
<%@page isErrorPage="false" errorPage="error.jsp"%>

<html>
<body>

<link rel="stylesheet" type="text/css" href="stile.css">


<% 
    Vector offerte = (Vector) request.getAttribute("offerte");
%> 
    
<h1> Benvenuto nell'area riservata! <br> Offerte pervenute per l'immobile selezionato </h1>

<% 
    if( offerte.size()==0 ){
%>
    Non ci sono offerte pervenute per questo immobile

<% } %>

<% for (int i = 0; i < offerte.size(); i++) { %>
 
    <table border="2" align="center">
    <tr>
    <td>

    <i><b> Offerta <%= i+1 %> </b></i>
    <% 
    ContattoBean bean = (ContattoBean) offerte.get(i);   
    %>

 
        <br> Giorno offerta: <%= bean.getGiornoOfferta()%> </a>
        <br> Cognome acquirente: <%= bean.getCognomeAcquirenteP() %> 
        <br> Nome acquirente: <%= bean.getNomeAcquirenteP() %>
        <br> Numero di telefono acquirente: <%= bean.getTelefonoAcquirenteP() %>
        <br> Prezzo offerto: <%= bean.getOfferta() %>


</td>
</tr>
</table>
<br>

<% } %>

<center>
<a href="./Main"><font color="00AA00">Home</font></a>
</center>

</body>
</html>
