<!DOCTYPE html>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="model.* "%>
<%@page isErrorPage="false"%>

<html>
<head>
		 <meta charset="UTF-8"> <!-- accetta determinati caratteri ad es. -->

		<title>Agenzia Immobiliare</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link rel="shortcut icon" href="images/favicon.ico" /> 

</head>

<body>
    
    <% 
        Vector infoImmobile = (Vector)request.getAttribute("infoImmobile"); 
        agenziaImmoBean bean = (agenziaImmoBean) infoImmobile.get(0);
        String link = "./main?ps=offerte_"; link+= bean.getCodiceImm(); 
        int numOff= (Integer)request.getAttribute("numOfferte");
    %>

    <script type="text/javascript"> 
     function controlloLogin() { 
      var nameuser = document.forms["login"]["user"].value; 
        if ( nameuser == "Username") { 
            alert("Inserire nome utente e password!"); 
            return false; 
        } 
    }
    </script>

     <script type="text/javascript"> 
    function controlloOfferta() { 
        var offerta = document.forms["faiOfferta"]["CifraOfferta"].value; 
        var NomeUser = document.forms["faiOfferta"]["Nome"].value; //Inserisci nome
        var CognomeUser = document.forms["faiOfferta"]["Cognome"].value; //Inserisci cognome
        var TelefonoUser = document.forms["faiOfferta"]["Telefono"].value; //Inserisci telefono
        var CifraOfferta = document.forms["faiOfferta"]["Telefono"].value; //Inserisci cifra offerta
        

        if (offerta < <%= bean.getPrezzoMinimo() %>) { 
            alert("Hai inserito un'offerta troppo bassa!"); 
            return false; 
        } 
        if( NomeUser == "Inserisci nome" || CognomeUser == "Inserisci cognome" || TelefonoUser == "Inserisci telefono" || CifraOfferta == "Insersci cifra offerta"){ 
            alert("Assicurati di aver completato tutti i campi dell'offerta!"); 
            return false; 
        } 
    }
    </script>

   	<div id="puntina1"> <img src= "images/tack.png" width="100" height="100"></div>
	
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


  

		<div class="titolo"> <h2>Pagina relativa alle informazioni dell'immobile.</h2></div>
			<h3> <ul>
                <li> Codice: <%= bean.getCodiceImm() %></li> 
				<li> Indirizzo: <%= bean.getIndirizzoImm() %></li> 
			    <li> Citt&agrave: <%= bean.getCittaImm() %></li> 
				<li> Superficie totale: <%= bean.getSupTotImm() %></li> 
				<li> Numero vani: <%= bean.getNumVani() %></li> 
                <li> Descrizione: <%= bean.getDesc() %></li>
                           <li> Prezzo minimo: <%= bean.getPrezzoMinimo() %> </li>
                
               
                <li> Numero offerte di acquisto pervenute: <%= numOff %> </li>

                FOTO!!! <BR>

			</h3>
		</div> <!-- container end -->


    <div class="container">

    <div id="two-columns">
			    
 <!--***** FORM PER IL LOGIN   ****-->
    <div id="col1">
	      <div class="login-card">

		        <h1>Log-in</h1><br>
                
		      <form name="login" action="./main" metCifraOffertahod="post" onsubmit="controlloLogin()"> 
		        <input type = "hidden" name = "codImm" value = "<%= bean.getCodiceImm() %>" >
		        <input type = "hidden" name = "ps" value = "login" >
		        <input type="text" name="user" value="Username">
                <center><h4> password: </h4></center>
		        <input type="password" name="pass">
		        <input type="submit" name="Entra" value="login">
		      </form>
	    </div>	
            </div> <!--fine col1-->

           <div id="col2">
	      <div class="login-card">
		        <h1>Inserisci Offerta</h1><br>

		      <form name="faiOfferta" action="./main" method="post" onsubmit="controlloOfferta()">

                <input type = "hidden" name = "idTent" value = "100" >
                <input type = "hidden" name = "ps" value = "inserimento" >
                <input type = "hidden" name = "codiceImm" value = "<%= bean.getCodiceImm() %>" >    
                   
		        <input type="text" name="Nome" value="Inserisci nome">
                   
		        <input type="text" name="Cognome" value="Inserisci cognome">
                  
		        <input type="text" name="Telefono" value="Inserisci telefono">
                  
		        <input type="text" name="CifraOfferta" value="Inserisci cifra offerta">

		        <input type="submit" name="Invia" value="login">
		      </form>

	    </div>	
        </div> <!--**fine col2 -->
        </div> <!--**fine 2 colonne -->    

	    </div>	<!-- content-end-->
</div> <!-- fine container  -->
</body>

<footer>
	<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>
</footer>
</html>

