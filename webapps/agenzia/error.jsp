<!--
Pagina di errore per mostrare tutti i corsi nel caso in cui l'utente non inserisca il dipartimento
-->

<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page isErrorPage="true"%>

<html>
 
 
    <head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Agenzia Immobiliare</title>
    <link rel="shortcut icon" href="images/favicon.ico" /> 
    </head>

    <body>
    <div class="header">
        <div id="menu"> 
    		<div class="logo">
    <h1>Attenzione!</h1>
    <h2>Si &egrave verificato un errore fatale: tornare alla home!</h2>
            </div>
        </div>
    </div>
    <div class="content">
		<div class="container">
    <h3>Possibili errori
    <ul>
        <li> URL non corretto</li>
        <li> Username e/o password errati</li>
        <li> Problemi di connessione</li>
    </ul>
    </h3>
    <br>
    <h3 align="right">
    <a href="./main">Home</a>
    </h3>
    </div>
    </div>

    </body>

<html>
