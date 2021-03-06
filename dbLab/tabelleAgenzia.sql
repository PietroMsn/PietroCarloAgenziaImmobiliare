      --Cancello le tabelle che si trovavano precedentemente sul database

	DROP TABLE IF EXISTS agente, cliente, effettivoacquirente, immobile, offerta, potenzialeacquirente, tentativovendita, invendita;

	DROP DOMAIN Tipologia;

      --Creazione relazioni di una base di dati relativa ad un sistema per la vendita di abitazioni.


	CREATE TABLE Cliente
	(
		CodFis VARCHAR(40) PRIMARY KEY,
		Nome VARCHAR(40) NOT NULL,
		Cognome VARCHAR(40) NOT NULL,
		Citta VARCHAR(40) NOT NULL,
		Telefono VARCHAR(30) NOT NULL,
		Cellulare VARCHAR(30),
		Login VARCHAR(20) NOT NULL UNIQUE,
		Passwd VARCHAR(20) NOT NULL
	
	);

	
	CREATE TABLE Agente(
		Codice VARCHAR(40) PRIMARY KEY,
		Nome VARCHAR(40) NOT NULL,
		Cognome VARCHAR(40) NOT NULL,
		Indirizzo VARCHAR(50) NOT NULL,
		Citta VARCHAR(40) NOT NULL
	);

	CREATE TABLE EffettivoAcquirente(
		Nome VARCHAR(40) NOT NULL,
		Cognome VARCHAR(40) NOT NULL,
		CodFis VARCHAR(40) PRIMARY KEY,
		Telefono VARCHAR(40) NOT NULL
	);
	
	CREATE DOMAIN Tipologia as VARCHAR(30)
        CHECK(VALUE IN ('appartamento in palazzina','villetta a schiera', 'villa singola'));

        CREATE TABLE Immobile
        (       
		CodiceIm VARCHAR(40) PRIMARY KEY,
		Indirizzo VARCHAR(50) NOT NULL,
		Citta VARCHAR(30) NOT NULL,
		SuperficieTotale FLOAT NOT NULL,
		NumeroVani INTEGER NOT NULL,
		Descrizione VARCHAR(150) NOT NULL,
		Tipo Tipologia NOT NULL,
		Piano INTEGER,
		SuperficieGiardino FLOAT,

		CHECK (SuperficieTotale>=0),
		CHECK (NumeroVani>=0),
		CHECK (Piano>=0),
		CHECK (SuperficieGiardino>=0)
		

        );

	CREATE TABLE TentativoVendita
	(
		Id INTEGER PRIMARY KEY,

		PrezzoMinimo FLOAT NOT NULL,
		PeriodoPrezzo DATE NOT NULL,
		DataEffettiva DATE,
		Notaio VARCHAR(50), 
		PrezzoFinale FLOAT default null,
		DataConcordata DATE NOT NULL,
		CodCliente VARCHAR(40) NOT NULL REFERENCES Cliente(CodFis),
		CodiceEffettivoAcquirente VARCHAR(40) REFERENCES EffettivoAcquirente(CodFis),
		CodiceAgente VARCHAR(40) NOT NULL REFERENCES Agente(Codice),
		
		CHECK (PrezzoMinimo>=0),
		CHECK (PrezzoFinale>=0)
		
	);

	
	CREATE TABLE InVendita
	(
		CodImmobile VARCHAR(40) NOT NULL
		REFERENCES Immobile(CodiceIm),
		IdTentativoVendita INTEGER NOT NULL
		REFERENCES TentativoVendita(Id),
	
		PRIMARY KEY(CodImmobile, IdTentativoVendita)
	);

	CREATE TABLE PotenzialeAcquirente(
		Nome VARCHAR(40) NOT NULL,
		Cognome VARCHAR(40) NOT NULL,
		Telefono VARCHAR(40) NOT NULL,
		PRIMARY KEY( Nome, Cognome, Telefono)
	);

	CREATE TABLE Offerta(
		Id INTEGER UNIQUE,
		PrezzoOfferto FLOAT NOT NULL, 
		IdTentativoVendita INTEGER NOT NULL REFERENCES TentativoVendita(Id),
		NomePA VARCHAR(40) NOT NULL,
		CognomePA VARCHAR(40) NOT NULL,
		GiornoContattoPA DATE NOT NULL,
		TelefonoPA VARCHAR(40) NOT NULL,

		FOREIGN KEY(NomePA, CognomePA, TelefonoPA) 
              		REFERENCES PotenzialeAcquirente(Nome, Cognome, Telefono)
                  	ON DELETE SET DEFAULT
                 	ON UPDATE CASCADE,

		PRIMARY KEY( IdTentativoVendita, PrezzoOfferto, NomePA, CognomePA,  TelefonoPA),

		CHECK (PrezzoOfferto>=0)

	);



