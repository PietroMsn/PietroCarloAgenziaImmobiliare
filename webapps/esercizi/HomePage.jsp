<!--	HomePage.jsp: 
	La JSP visualizza la home paga dell'agenzia immobiliare
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
    int totalePalazzina = (Integer) request.getAttribute("conteggioPalazzina");
    int totaleSchiera = (Integer) request.getAttribute("conteggioSchiera");
    int totaleSingola = (Integer) request.getAttribute("conteggioSingola");
%> 

<h1> Agenzia immobiliare Arceri&Calabria </h1>

<h2> Elenco dei tipi di immobile attualmente in vendita </h2>

<% if(totalePalazzina > 0) %>
<p> <a href ="./Main?ps=tvp&tipo=palazzina"> Appartamenti in palazzine in vendita:</a> <%= totalePalazzina %> </p>

<% if(totaleSchiera > 0) %>
<p> <a href ="./Main?ps=tvp&tipo=schiera"> Villette a schiera in vendita:</a> <%= totaleSchiera %> </p>

<% if(totaleSingola > 0) %>
<p> <a href ="./Main?ps=tvp&tipo=singola"> Ville singole in vendita:</a> <%= totaleSingola %> </p>

<br>
<h3> Chi siamo</h3>
<p> 

Arceri&Calabria &egrave una societ&agrave di servizi immobiliari autonoma con sede e 4000 uffici in Verona e Desenzano.
La societ&agrave si caratterizza per l'elevata qualit&agrave dei servizi e per la professionalit&agrave che offre ai propri clienti, grazie ad una particolare capacit&agrave di ascolto delle loro reali e personali esigenze.
L'utilizzo di strategie di marketing moderne, un ottimo posizionamento nel WEB (siti e portali aziendali), l'appartenenza a network immobiliari internazionali, uniti ad un costante agiornamento del personale commerciale ed amministrativo garantiscono uno standard elevato di  servizi ed intermendiazione immobiliare al cliente.
Il personale di Cofim immobiliare Vr parla,  inoltre, correttamente inglese, francese, spagnolo, tedesco, russo e cinese.
Gli azionisti di maggioranza (detenitori del 51% della quota societaria) sono Vincenzo Arceri e Matteo Calabria.

</p>




</body> 

</html>
