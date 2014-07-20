<!DOCTYPE html>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="model.* "%>
<%@page isErrorPage="false"%>

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
			   <li class="no_bg ped"><a href="./main#contactus">Dove siamo</a></li>
			</ul>
		</div> <!-- end menu-->	
		
	</div> <!-- end header-->

	<div class="content">
		<div class="container">


     <% 
     
        Vector offerte = (Vector)request.getAttribute("offerteImm"); 
        
    %>
            
   <h3>	
		<div class="titolo"><h2>Pagina relativa alle offerte sull'immobile.</h2></div>
			<ul> Elenco offerte
                <% for (int i = 0; i < offerte.size(); i++) {
                    agenziaImmoBean bean = (agenziaImmoBean) offerte.get(i);
                 %>
			       <li> Giorno offerta: <%= bean.getGiornoContattoPA()%> </li>
                   <li> Cognome acquirente: <%= bean.getCognomePA() %> </li> 
                   <li> Nome acquirente: <%= bean.getNomePA() %> </li>
                   <li> Numero di telefono acquirente: <%= bean.getTelefonoPA() %> </li>
                   <li> Prezzo offerto: <%= bean.getPrezzoOfferto() %> </li>
        
              <% } %>
			  
			</ul>
		   </h3>

    
		</div> <!-- container end -->
	</div>	<!-- content-end-->

</body>

<footer>
	<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>
</footer>
</html>

