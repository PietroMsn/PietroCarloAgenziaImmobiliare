<!--	TentativiVenditaPage.jsp: 
	La JSP visualizza i tentativi di vendita del tipo di immobile selezionato dalla HomePage
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
    String nomeTipo = (String) request.getAttribute("tipoImmobile");
    Vector tentativi = (Vector) request.getAttribute("tentativi");
%> 
    
<h1> Tentativi di vendita di immobili di tipo <%= nomeTipo %>  </h1>


<% for (int i = 0; i < tentativi.size(); i++) { %>
 
    <% 
    ImmobileBean bean = (ImmobileBean) tentativi.get(i);  
    String url = "./Main?ps=imm&idTent=" + bean.getIdTentativoVendita(); 
    %>

    <table border="2" align="center">
    <tr>
    <td>
 
             Codice immobile: <a href=" <%= url %>"> <%= bean.getCodiceImmobile()%> </a>
        <br> Indirizzo immobile: <%= bean.getIndirizzoImmobile() %>
        <br> Citt&agrave immobile: <%= bean.getCittaImmobile() %>
        <br> Superficie totale dell'immobile: <%= bean.getSuperficieImmobile() %>


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

