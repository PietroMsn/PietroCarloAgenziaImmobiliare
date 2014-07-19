<!--	ImmobiletaPage.jsp: 
	La JSP visualizza le informazioni relative al particolare immobile che si sta tentando di vendere
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
    ImmobileBean bean = (ImmobileBean) request.getAttribute("immobile");
    int prezzomin = (Integer) request.getAttribute("minprice");
%> 

<script type="text/javascript"> 
function controllo() { 
    var offerta = document.forms["faiOfferta"]["offerta"].value; 
    if (offerta < <%= prezzomin %>) { 
        alert("ATTENZIONE: hai inserito un'offerta troppo bassa!"); 
        return false; 
    } 
}
</script>

<h1> Informazioni relative all'immobile selezionato </h1>

    <table border="2" align="center">
    <tr>
    <td>

             Codice immobile: <%= bean.getCodiceImmobile()%> </a> 
        <br> Indirizzo immobile: <%= bean.getIndirizzoImmobile() %>
        <br> Citt&agrave immobile: <%= bean.getCittaImmobile() %>
        <br> Superficie totale dell'immobile: <%= bean.getSuperficieImmobile() %>
        <br> Numero di vani dell'immobile: <%= bean.getNumeroVani() %>
        <br> Descrizione immobile: <%= bean.getDescrizioneImmobile() %>  

</td>
</tr>
</table>
<br>


<form name="faiOfferta" action="./Main" method="post" onsubmit="controllo()">
	    <input type = "hidden" name = "idTent" value = "<%= bean.getIdTentativoVendita() %>" >
    	<input type = "hidden" name = "ps" value = "inserimento" >
    
		<table align="center"> <b>Area potenziali acquirenti: </b> <br>

        <tr> 
        <td> Nome: </td> <td> <input type="text" name="firstname"> </td> 
        </tr>

        <tr> 
        <td> Cognome: </td> <td> <input type="text" name="lastname"> </td>
        </tr>

        <tr> 
        <td> Numero di telefono: </td> <td> <input type="text" name="nTelefono"> </td>
        </tr>

        <tr> 
        <td> Offerta: </td> <td> <input type="integer" name="offerta"> </td>
        </tr>

        </table>

        <input align="center" type="submit" value="Fai offerta">

    

</form>

<br>

<form name="Login" action="./Main" method="post">
    <input type = "hidden" name = "idTent" value = "<%= bean.getIdTentativoVendita() %>" >
    <input type = "hidden" name = "ps" value = "offerte" >
    
    <table align="center"> <b>Area proprietari: </b> <br>

        <tr> 
        <td> Username: </td> <td> <input type="text" name="username"> </td> 
        </tr>

        <tr> 
        <td> Password: </td> <td> <input type="password" name="password"> </td>
        </tr>

        </table>

        <input align="center" type="submit" value="Login" > </td>

</form>

<center>
<a href="./Main"><font color="00AA00">Home</font></a>
</center>

</body>
</html>
