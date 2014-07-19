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
   		  <div id="chisiamo">
				
			<div class="titolo"><h2>Presentazione dell'azienda</h2></div>

			<div id="puntina1"> <img src= "images/tack.png" width="100" height="100" ></div>
			<div id="puntina2"> <img src= "images/tack.png" width="100" height="100" ></div>				
			<div id="puntina3"> <img src= "images/tack.png" width="100" height="100" ></div>

		<div id="two-columns">
			<div id="col1">
			   <h4> Il rapporto personale con i nostri clienti ci distingue. <br> Abbiamo a cuore le loro necessit&agrave e cerchiamo con insistenza di trovare la soluzione che risponda ai loro desideri in ogni dettaglio in tutti i casi. <br> I nostri clienti che desiderano una consulenza possono sempre contare sulla nostra disponibilit&agrave, professionalit&agrave, cordialit&agrave e riservatezza che consentono alla nostra agenzia di soddisfare il cliente in ogni fase della trattativa. <br>L'agenzia Immobiliare &egrave presente nella realta' del mercato immobiliare con immobili residenziali e commerciali nella provincia del Verbano, vero core business della societ&agrave oltre che in alcune localit&agrave turistiche pi&ugrave esclusive.<br> 
Siamo sempre pi&ugrave convinti dell'importanza della collaborazione tra colleghi.<br> Il cliente che ci affida l'incarico per vendere il proprio immobile pu&ograve contare non solo sul nostro impegno, ma anche sulla collaborazione di altre agenzie operanti nella stessa zona e non, mantenendo comunque sempre l'agenzia immobiliare come unico interlocutore e referente in tutte le fasi della trattativa. </h4><br></div>
<div id="col2"><h4>
La  nostra agenzia ha deciso di impegnare parte delle proprie risorse economiche nella pubblicit&agrave degli immobili, ben sapendo che una buona pubblicit&agrave &egrave garanzia di successo. <br> Per questo motivo ha scelto di pubblicizzare gli immobili acquisiti nelle pagine di riviste specializzate, nei portali di maggior riscontro, nel volantinaggio, per proporre gli immobili a chi ci lascia la propria richiesta. <br> Tutto questo a costi decisamente superiori alla media, ma l'esperienza infatti insegna che chi &egrave interessato alla vendita della propria casa vuole che la propria inserzione sia di facile ed immediato approccio, in modo da avere una rosa pi&ugrave ampia possibile di potenziali acquirenti, cos&igrave come eventuali acquirenti desiderano trovare il giusto affare con il minor dispendio di energia, tempo ed a prezzo equo. </h4>
</div></div>
		</div> <!-- chi siamo end-->
	     </div> <!-- container end -->
	
	    <div class="container">
		<div id="invendita">

			<div class="titolo"><h2> In vendita oggi: </h2></div>

			<h3>Elenco tipi di immobile per i quali esistono offerte di vendita  ( pu&ograve essere anche vuoto) </h3>


    <%

    Integer nVillette = (Integer) request.getAttribute("numVillette1");
    Integer nVille = (Integer) request.getAttribute("numVille1");
    Integer nAppartamenti = (Integer) request.getAttribute("numAppartamenti1");
    

    if(nVillette==0 && nVille==0 && nAppartamenti==0)
   {  

    %>		
            <h3><ul> Oggi non ci sono immobili in vendita. </ul></h3>

<%
     } else {
%>
    
            <h3><ul> In vendita oggi: <u>
			   <%if(nVillette!=0) {
                    String link = "./main?ps=villetta a schiera"; %>
                    <li><a href="<%= link %>">Villetta a schiera <%= nVillette %></a></li>
                                             <%}%>
			   <%if(nVille!=0) {
                    String link = "./main?ps=villa singola"; %>
                    <li><a href="<%= link %>">Villa singola <%= nVille %></a></li>
                                             <%}%>
			   <%if(nAppartamenti!=0) {
                    String link = "./main?ps=appartamento in palazzina"; %>
                    <li><a href="<%= link %>">Appartamento in palazzina <%= nAppartamenti %></a></li> 
                                             <%}%>
			</u></ul><h3>

<% } //fine else relativo all'esistenza o meno di immobili in vendita %>

           
						
		</div> <!-- invendita end-->
	     </div> <!-- container end -->

	     <div class="container">
		<div id="contactus">
		
			<div class="titolo"><h2>Sede dell'azienda e contatti</h2></div>

			<center>
				    <iframe width="425" height="350" frameborder="0"
				    scrolling="no" marginheight="0" marginwidth="0"
src="https://maps.google.it/maps?f=q&amp;source=s_q&amp;hl=it&amp;geocode=&amp;q=via+golino+verona&amp;aq=&amp;sll=45.421646,10.986046&amp;sspn=0.0694,0.156898&amp;ie=UTF8&amp;hq=&amp;hnear=Via+Golino,+Verona&amp;t=m&amp;z=14&amp;ll=45.402224,10.9909&amp;output=embed"></iframe> <br /><small><a
 href="https://maps.google.it/maps?f=q&amp;source=embed&amp;hl=it&amp;geocode=&amp;q=via+golino+verona&amp;aq=&amp;sll=45.421646,10.986046&amp;sspn=0.0694,0.156898&amp;ie=UTF8&amp;hq=&amp;hnear=Via+Golino,+Verona&amp;t=m&amp;z=14&amp;ll=45.402224,10.9909" style="color:#0000FF;text-align:left"></a></small>
			</center>

			 <h3> 
				<ul>	
					<li>Affiliato: "C&agrave Vignal Case Sas" </li>
					<li>Responsabile: Eugenio Turri </li>
					<li>Indirizzo: Via Golino 37b Verona </li>
					<li>Recapito telefonico: 045 / 907 302</li>
				</ul> 
			 </h3>

		</div> <!-- contactus-->
	    </div> <!-- container end -->
	</div>	<!-- content-end-->
	
</body>

<footer>
	<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>
</footer>
</html>
