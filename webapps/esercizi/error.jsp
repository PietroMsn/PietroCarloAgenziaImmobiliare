<!--
Pagina di errore per mostrare tutti i corsi nel caso in cui l'utente non inserisca il dipartimento
-->

<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="did.*"%>
<%@page isErrorPage="true"%>

<link rel="stylesheet" type="text/css" href="stile.css">

<html>

    <body>

    <h2>Attenzione!</h2>
    <h3>Si &egrave verificato un errore fatale: tornare alla home!</h3>
    <h4>Possibili errori</h4>
    <ul>
        <li> URL non corretto</li>
        <li> Username e/o password errati</li>
        <li> Problemi di connessione</li>
    </ul>
    <br>
    <a href="./Main"><font color="00AA00">Home</font></a>

    </body>

<html>
