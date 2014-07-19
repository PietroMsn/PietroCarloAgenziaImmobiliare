import java.sql.*;
import java.util.*;
import model.*;
/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {
	//Dati di identificazione dell'utente (da personalizzare)
    private String user = "userlab06";
    private String passwd = "seiHh";
	
    /** URL per la connessione alla base di dati e' formato dai seguenti componenti:
     * <protocollo>://<host del server>/<nome base di dati>.
     */
   // private String url = "jdbc:postgresql://dbserver.sci.univr.it/did2014";
    private String url = "jdbc:postgresql://localhost/dblab06";
    
	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
    private String driver = "org.postgresql.Driver";

	//definizione delle Query 

    // query usata nell'index per ottenere il numero di immobili invenduti per la tipologia passata in input
	private String numImm = "select count(immobile.codiceim) as numImmTipo from immobile, tentativovendita, invendita where immobile.tipo = ? and immobile.codiceim = invendita.codimmobile and invendita.idtentativovendita = tentativovendita.id and tentativovendita.codiceeffettivoacquirente not like 'null';";

    //query usata nella pagina TentativoVendita.jsp che riporna per la tipologia di immobili data in input le informazioni 
    //di tutti gli immobili in vendita che si trovano nel database
    private String tentVendita = "select immobile.codiceIm, immobile.indirizzo, immobile.citta, immobile.superficietotale from immobile, tentativovendita, invendita where tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceIm and immobile.tipo = ? and tentativovendita.codiceeffettivoacquirente not like 'null';";

    //query usata per ottenere offerte per un determinato immobile
   	private String infoImm = "select codiceIm, indirizzo, citta, superficietotale, numerovani, descrizione from immobile where codiceIm = ?;";

    //query usata per ottenere il numero di offerte attive nel database al momento
  	private String numOff = "select count(offerta.id) as numOffTipo from offerta, tentativovendita, invendita, immobile where offerta.idtentativovendita = tentativovendita.id and tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceim and immobile.codiceim = ?;";


	private String infoOfferte = " select offerta.giornocontattoPA, offerta.nomePA, offerta.cognomePA, offerta.telefonoPA, offerta.prezzoofferto from offerta, tentativovendita, cliente, immobile, invendita where offerta.idtentativovendita = tentativovendita.id and tentativovendita.codcliente = cliente.codfis and tentativovendita.id = invendita.idtentativovendita and invendita.codimmobile = immobile.codiceim and cliente.login = ? and immobile.codiceIm = ?;";




	private String login = " select login from cliente where login = ? and passwd = ?;";


    private String insertOfferte = "INSERT INTO Offerta(Id, PrezzoOfferto, IdTentativoVendita, NomePA, CognomePA, GiornoContattoPA, TelefonoPA) VALUES ('23', ?, ?, ?, ?, '2017-05-10', ?);";
    
	/*private String insertTentativo ="INSERT INTO TentativoVendita (Id, PrezzoMinimo, PeriodoPrezzo, DataEffettiva, Notaio, PrezzoFinale, DataConcordata, CodCliente, CodiceAgente) VALUES ('616', '180000', '2018-10-08', '2018-10-08', 'Borsini', '140000', '2014-12-17', 'ptrmsn93e05l781t', 'id001');";*/
	
    /**
     * Costruttore della classe. Carica i driver da utilizzare per la
     * connessione alla base di dati.
     *
     * @throws ClassNotFoundException Eccezione generata nel caso in cui
     *         i driver per la connessione non siano trovati nel CLASSPATH.
     */
    public DBMS() throws ClassNotFoundException {
		Class.forName(driver);
    }

	//Metodi per la creazione di un bean a partire dal record attuale del ResultSet dato come parametro
	private agenziaImmoBean makeNumImmTipoBean(ResultSet rs) throws SQLException {
		agenziaImmoBean bean = new agenziaImmoBean();
		bean.setNumImmTipo(rs.getInt("numimmtipo"));
		return bean;
    	}

	private agenziaImmoBean makeTentVenditaBean(ResultSet rs) throws SQLException {
		agenziaImmoBean bean = new agenziaImmoBean();
    	bean.setCodiceImm(rs.getString("codiceIm"));
		bean.setIndirizzoImm(rs.getString("indirizzo"));
		bean.setCittaImm(rs.getString("citta"));
		bean.setSupTotImm(rs.getFloat("superficietotale"));
		return bean;
    	}
    
	private agenziaImmoBean makeInfoImmBean(ResultSet rs) throws SQLException {
 	
		agenziaImmoBean bean = new agenziaImmoBean();
		bean.setCodiceImm(rs.getString("codiceIm"));
		bean.setIndirizzoImm(rs.getString("indirizzo"));
		bean.setCittaImm(rs.getString("citta"));
		bean.setSupTotImm(rs.getFloat("superficietotale"));
		bean.setNumVani(rs.getInt("numerovani"));
		bean.setDesc(rs.getString("descrizione"));
		return bean;
    	}
	
	private agenziaImmoBean makeNumOffTipoBean(ResultSet rs) throws SQLException {
		agenziaImmoBean bean = new agenziaImmoBean();
		bean.setNumOffTipo(rs.getInt("numOffTipo"));
		return bean;
    	}

	private agenziaImmoBean makeInfoOfferteBean(ResultSet rs) throws SQLException {
		agenziaImmoBean bean = new agenziaImmoBean();
		bean.setGiornoContatto(rs.getString("giornoContatto"));
		bean.setNomePA(rs.getString("nomePA"));
		bean.setCognomePA(rs.getString("cognomePA"));
		bean.setTelefonoPA(rs.getString("telefonoPA"));
		bean.setPrezzoOfferto(rs.getFloat("prezzoofferto"));
		return bean;
    	}

	private agenziaImmoBean makeLoginBean(ResultSet rs) throws SQLException {
		agenziaImmoBean bean = new agenziaImmoBean();
		bean.setLogin(rs.getString("login"));
		return bean;
    	}
	

	
	

	
	//Metodo per il recupero del numero di immobili per il tipo passato in input
	public Integer getNumImmTipo(String tipo) {

    
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(numImm); 
			pstmt.clearParameters();
			pstmt.setString(1, tipo); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			rs.next();
			result.add(makeNumImmTipoBean(rs));
		
		} catch(SQLException sqle) {                // Catturo le eventuali eccezioni! 
			sqle.printStackTrace();
		} finally {                                 // Alla fine chiudo la connessione. 
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return ((agenziaImmoBean)result.get(0)).getNumImmTipo();
	}

    //metodo che ritorna il numero delle offerte
	public Integer getNumOfferte(String codiceImm) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(numOff); 
			pstmt.clearParameters();
			pstmt.setString(1, codiceImm); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			rs.next();
			result.add(makeNumOffTipoBean(rs));
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return ((agenziaImmoBean)result.get(0)).getNumOffImm();
	}

		
	//metodo che restituisce il nome di login se va a buon fine (se nel database, a login è associata passwd)
	public String getAccess(String username, String password) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(login); 
			pstmt.clearParameters();
			pstmt.setString(1, username);
			pstmt.setString(2, password); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			rs.next();
			result.add(makeLoginBean(rs));
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return ((agenziaImmoBean)result.get(0)).getLogin();
	}

	//metodo che dato in ingresso il tipo di immobile restituisce il vector contenente 
    // tutti i tentativi di vendita per quel tipo e i relativi attributi

	public Vector getTentVenditaTipo(String tipo) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(tentVendita); 
			pstmt.clearParameters();
			pstmt.setString(1, tipo);
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			while(rs.next())
				result.add(makeTentVenditaBean(rs));
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}


	//metodo che, dato in input un codice di immobile, restituisce un vector con tutte le informazioni per quell'immobile
	public Vector getInfoImm(String codiceImm) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(infoImm); 
			pstmt.clearParameters();
			pstmt.setString(1, codiceImm);
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			rs.next();
			result.add(makeInfoImmBean(rs));
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	//metodo che, dato in ingresso il login del cliente e il codice dell'immobile selezionato, restituisce i vector con tutte le info
	// sulle offerte di un immobile
	public Vector getInfoOfferte(String login, String codiceImm) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(infoOfferte); 
			pstmt.clearParameters();
			pstmt.setString(1, login);
			pstmt.setString(2, codiceImm);
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			while(rs.next())
				result.add(makeInfoOfferteBean(rs));
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

					//dbms.insertOfferta( nome, cognome, idTent, offerta, telefono);
				

    public void insertOfferta(String nome,String cognome, int idTent, int offerta, String telefono) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(insertOfferte); 
			pstmt.clearParameters();
			pstmt.setInt(1, offerta);
			pstmt.setInt(2, idTent);
            pstmt.setString(3, nome);
            pstmt.setString(4, cognome);
            pstmt.setString(5, telefono);
			rs=pstmt.executeQuery(); 		
			
		
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
	}

    

	
}
