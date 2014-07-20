
-------restituisce il numero di immobili in vendita per una determinata tipologia -------------
select count(immobile.codiceim) as numImmTipo
from immobile, tentativovendita, invendita
where immobile.tipo = ? and immobile.codiceim = invendita.codimmobile and invendita.idtentativovendita = tentativovendita.id and tentativovendita.codiceeffettivoacquirente not like 'null';


---------per una tipologia scelta mostra tutti i tentativi di vendita con  immobili invenduti
select immobile.codiceIm, immobile.indirizzo, immobile.citta, immobile.superficietotale
from immobile, tentativovendita, invendita
where tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceIm and immobile.tipo = ? and tentativovendita.codiceeffettivoacquirente not like 'null';

--------premendo un codice immobile vai alle info di quell'immobile
select codiceIm, indirizzo, citta, superficietotale, numerovani, descrizione
from immobile
where codiceIm = ?;

-----------numero di offerte per l'immobile selezionato
select count(offerta.id)
from offerta, tentativovendita, invendita, immobile
where offerta.idtentativovendita = tentativovendita.id and tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceim and immobile.codiceim = ?;


---una volta fatto il login mostra tutte le offerte per quel cliente e le info sulle offerte per l'immobile di quella pagina
select offerta.giornocontattoPA, offerta.nomePA, offerta.cognomePA, offerta.telefonoPA, offerta.prezzoofferto
from offerta, tentativovendita, cliente, immobile, invendita
where offerta.idtentativovendita = tentativovendita.id and tentativovendita.codcliente = cliente.codfis and tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceim and cliente.login = ? and immobile.codiceIm = ?;


---verifica il login, restituisce il nome utente
select login 
from cliente
where login = ? and passwd = ?;
