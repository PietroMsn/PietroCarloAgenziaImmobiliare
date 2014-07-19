<!DOCTYPE html>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="model.* "%>
<%@page isErrorPage="false"%>

<%
// Recupero i dati ottenuti dalle interrogazioni e passati attraverso l'oggetto request
String tipoImm = (String) request.getAttribute("Tipo");

%>	

<html>
<head>

		
		<title>Agenzia Immobiliare</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link rel="shortcut icon" href="images/favicon.ico" /> 

</head>

<body>

   	<div id="puntina1"> <img src= "images/tack.png" width="100" height="100"></div>
	<div id="puntina2"> <img src= "images/tack.png" width="100" height="100"></div>
	<div id="puntina3"> <img src= "images/tack.png" width="100" height="100"></div>
	
	<div class="header">
	
		<div id="menu"> 

		<div class="logo"><h1><a href="./main">Agenzia Immobiliare</a> </h1> </div>

			<ul>
			   <li><a href="./main">Home</a></li>
			   <li><a href="./main#chisiamo">Chi siamo</a></li>
			   <li><a href="./main#invendita">Cerco casa</a></li>
			   <li class="no_bg ped"><a href="main#contactus">Dove siamo</a></li>
			</ul>
		</div> <!-- end menu-->	
		
	</div> <!-- end header-->

	<div class="content">
			
		<div class="container">
		  	
		 <div class="titolo"><h2>Pagina relativa ai tentativi di vendita.</h2></div>
                
               <% if(tipoImm.equals("appartamento in palazzina")){%>
			   <h2> Appartamenti in vendita presso l'agenzia </h2>
               <%}%>
                <% if(tipoImm.equals("villetta a schiera")){%>
			   <h2> Villette a schiera in vendita presso l'agenzia </h2>
                <%}%>
               <% if(tipoImm.equals("villa singola")){%>
			   <h2> Ville singole in vendita presso l'agenzia </h2>
                <%}%>
			   <h2>Elenco immobili disponibili: </h2>

    <h3>
    <ul> 

    <%

    Vector immInVendita = (Vector)request.getAttribute("inVendita"); 

    for (int i = 0; i < immInVendita.size(); i++) {
        // Ogni elemento dell'array è un agenziaImmoBean
       agenziaImmoBean bean = (agenziaImmoBean) immInVendita.get(i);  
       
    %>
          
    <li> 
        <ul> 
                          
            <% String link = "./main?ps=codice_"; link+= bean.getCodiceImm(); %>

            <li><u><a href="<%= link %>"> Codice: <%= bean.getCodiceImm() %></a></u></li>

            <li> Indirizzo: <%= bean.getIndirizzoImm() %></li> 
            <li> Citt&agrave: <%= bean.getCittaImm() %></li> 
            <li> Superficie totale: <%= bean.getSupTotImm() %></li> 
     
       </ul>
    </li>
   
    <% } %> 

    </ul> 
    </h3>

		</div> <!-- container end--> 
	</div>	<!-- content end-->

</body>

<footer>
	<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>
</footer>
</html>

