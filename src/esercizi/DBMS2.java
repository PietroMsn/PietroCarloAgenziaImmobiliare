/**        
* DBMS.java        
*/
import java.sql.*;
import java.util.*;
import java.util.Date;
import did.*;

/* per gli id */
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */

public class DBMS {

    // Dati di identificazione dell'utente
    private String user = "userlab23";
    private String passwd = "ventitreRH";
	
    /** URL per la connessione alla base di dati e' formato dai seguenti componenti:
     * <protocollo>://<host del server>/<nome base di dati>.
     */
    private String url = "jdbc:postgresql://157.27.10.31/dblab23";
    
    /** Driver da utilizzare per la connessione e l'esecuzione delle query. */
    private String driver = "org.postgresql.Driver";

    /* Numero totale dei tentativi di vendita di palazzine */
    private String tentativiPalazzina = "SELECT COUNT(*) as c1 FROM Immobile, TentativoVendita WHERE Immobile.CodiceImmobile = TentativoVendita.ImmobileInVendita AND Immobile.Tipo = 'palazzina' AND TentativoVendita.DataFineTentativo >= '26/05/2014' AND TentativoVendita.DataInizioTentativo < '26/05/2014' AND TentativoVendita.DataEffettiva IS NULL";

    /* Numero totale dei tentativi di vendita di villette a schiera */
    private String tentativiSchiera = "SELECT COUNT(*) as c2 FROM Immobile, TentativoVendita WHERE Immobile.CodiceImmobile = TentativoVendita.ImmobileInVendita AND Immobile.Tipo = 'schiera' AND TentativoVendita.DataFineTentativo >= '26/05/2014' AND TentativoVendita.DataInizioTentativo < '26/05/2014' AND TentativoVendita.DataEffettiva IS NULL";

    /* Numero totale dei tentativi di vendita di villette singole */
    private String tentativiSingola = "SELECT COUNT(*) as c3 FROM Immobile, TentativoVendita WHERE Immobile.CodiceImmobile = TentativoVendita.ImmobileInVendita  AND Immobile.Tipo = 'singola' AND TentativoVendita.DataFineTentativo >= '26/05/2014' AND TentativoVendita.DataInizioTentativo < '26/05/2014' AND TentativoVendita.DataEffettiva IS NULL";

    /* Elenco degli immobili in vendita, col relativo idTentativoVendita */
    private String tentativiImmobile = "SELECT TentativoVendita.IdTentativoVendita, Immobile.CodiceImmobile, Immobile.IndirizzoImmobile, Immobile.CittaImmobile, Immobile.SuperficieImmobile FROM Immobile, TentativoVendita WHERE Immobile.CodiceImmobile = TentativoVendita.ImmobileInVendita AND Immobile.Tipo = ? AND TentativoVendita.DataFineTentativo >= '26/05/2014' AND TentativoVendita.DataInizioTentativo < '26/05/2014' AND TentativoVendita.DataEffettiva IS NULL";

    /* Informazioni riguardante un immobile con un dato tentativo di vendita */
    private String infoImmobile = "SELECT TentativoVendita.IdTentativoVendita, Immobile.CodiceImmobile, Immobile.IndirizzoImmobile, Immobile.CittaImmobile, Immobile.SuperficieImmobile, Immobile.NumeroVani, Immobile.DescrizioneImmobile FROM Immobile, TentativoVendita WHERE TentativoVendita.IdTentativoVendita = ? AND Immobile.CodiceImmobile = TentativoVendita.ImmobileInVendita";

    /* Informazioni riguadanti un'offerta di un immobile, dato il tentativo di vendita */
    private String offertaImmobile = "SELECT Contatto.GiornoContatto, Contatto.Offerta, AcquirentePotenziale.NomeAcquirentePot, AcquirentePotenziale.CognomeAcquirentePot, AcquirentePotenziale.TelefonoAcquirentePot FROM Contatto, AcquirentePotenziale WHERE Contatto.IdPotenzialeAcquirente = AcquirentePotenziale.IdAcquirentePot AND Contatto.IdTentativoDiVendita = ?";
    

	/* comando per inserire una nuova offerta */
	private String insertOfferta = "INSERT INTO Contatto(IdPotenzialeAcquirente, IdTentativoDiVendita, GiornoContatto, Offerta) VALUES(?, ?, '2014-06-04', ?)";
	
	/* domando per inserire un nuovo acquirente potenziale nel sistema */
	private String insertAcquirenteP = "INSERT INTO AcquirentePotenziale(IdAcquirentePot, NomeAcquirentePot, CognomeAcquirentePot, TelefonoAcquirentePot) VALUES(?, ?, ?, ?)";
    
	/* Query per recuperare i tentativi di vendita di un cliente con un dato username e password */
	private String tentativiCliente = "SELECT TentativoVendita.idTentativoVendita FROM Cliente, TentativoVendita WHERE Cliente.CodiceFiscaleCliente = TentativoVendita.CodiceFiscaleVenditore AND Cliente.UsernameCliente = ? AND Cliente.PasswordCliente = ?";
	
	/* Metodo per controllare che un certo acquirente potenziale sia registrato nel sistema */
	private String registered = "SELECT AcquirentePotenziale.IdAcquirentePot FROM AcquirentePotenziale WHERE AcquirentePotenziale.NomeAcquirentePot = ? AND AcquirentePotenziale.CognomeAcquirentePot = ? AND AcquirentePotenziale.TelefonoAcquirentePot = ?";

	/* Metodo per il recupero del prezzo minimo di un certo tentativo di vendita */
	private String offertaTent = "SELECT TentativoVendita.PrezzoMinimo FROM TentativoVendita WHERE TentativoVendita.idTentativoVendita = ?";

	// Costruttore di DBMS per il caricamento dei driver
    public DBMS() throws ClassNotFoundException {
		Class.forName(driver);
    }
	
	// Make pre creare un bean riguardante un'offerta 
	private ContattoBean makeContattoBean(ResultSet rs) throws SQLException {
		ContattoBean bean = new ContattoBean();
		bean.setGiornoOfferta(rs.getString("GiornoContatto"));
		bean.setOfferta(rs.getInt("Offerta"));
		bean.setNomeAcquirenteP(rs.getString("NomeAcquirentePot"));
		bean.setCognomeAcquirenteP(rs.getString("CognomeAcquirentePot"));
		bean.setTelefonoAcquirenteP(rs.getString("TelefonoAcquirentePot"));
		return bean;
	}

	// Make per creare un bean contentente i dati relativi ad un tentativo di vendita coi dati dell'immobile
	private ImmobileBean makeImmobileBean(ResultSet rs) throws SQLException {
		ImmobileBean bean = new ImmobileBean();
		bean.setIdTentativoVendita(rs.getString("IdTentativoVendita"));
		bean.setCodiceImmobile(rs.getString("CodiceImmobile"));
		bean.setIndirizzoImmobile(rs.getString("IndirizzoImmobile"));
		bean.setCittaImmobile(rs.getString("CittaImmobile"));
		bean.setSuperficieImmobile(rs.getInt("SuperficieImmobile"));
		return bean;
	}

    // Make per creare un bean riguardante informazioni su un immobile
	private ImmobileBean makeInfoImmobileBean(ResultSet rs) throws SQLException {
		ImmobileBean bean = new ImmobileBean();
		bean.setDescrizioneImmobile(rs.getString("DescrizioneImmobile"));
		bean.setNumeroVani(rs.getInt("NumeroVani"));
		bean.setIdTentativoVendita(rs.getString("IdTentativoVendita"));
		bean.setCodiceImmobile(rs.getString("CodiceImmobile"));
		bean.setIndirizzoImmobile(rs.getString("IndirizzoImmobile"));
		bean.setCittaImmobile(rs.getString("CittaImmobile"));
		bean.setSuperficieImmobile(rs.getInt("SuperficieImmobile"));
		return bean;
	}

	public int getPalazzinaCounter() {
  		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
        ResultSet rs = null;
        int result = -1;
    
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);

			// Preparo la prima interrogazione per ottenere il numero di tentativi di vendita delle villette a schiera
			stmt = con.createStatement();
			// Eseguo l'interrogazione 
			rs = stmt.executeQuery(tentativiPalazzina);
		    rs.next();

			// Memorizzio i tre counter in un bean
            
            result = rs.getInt("c1");
        } catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}

		// Ritorno il bean
		return result;
	}

    public int getSchieraCounter() {
  		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
        ResultSet rs = null;
        int result = -1;
    
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);

			// Preparo la prima interrogazione per ottenere il numero di tentativi di vendita delle villette a schiera
			stmt = con.createStatement();
			// Eseguo l'interrogazione 
			rs = stmt.executeQuery(tentativiSchiera);
		    rs.next();

			// Memorizzio i tre counter in un bean
            
            result = rs.getInt("c2");
        } catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}

		// Ritorno il bean
		return result;
	}

    public int getSingolaCounter() {
  		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
        ResultSet rs = null;
        int result = -1;
    
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);

			// Preparo la prima interrogazione per ottenere il numero di tentativi di vendita delle villette a schiera
			stmt = con.createStatement();
			// Eseguo l'interrogazione 
			rs = stmt.executeQuery(tentativiSingola);
		    rs.next();

			// Memorizzio i tre counter in un bean
            
            result = rs.getInt("c3");
        } catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}

		// Ritorno il bean
		return result;
	}

	public Vector getTentativiImmobile(String tipo) {
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
			pstmt = con.prepareStatement(tentativiImmobile); 
			pstmt.clearParameters();
			pstmt.setString(1, tipo); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean

			while(rs.next())
				result.add(makeImmobileBean(rs));

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

    public ImmobileBean getImmobile(String idTent) {
	    // Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ImmobileBean result = null;

		try {

			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(infoImmobile); 
			pstmt.clearParameters();
			pstmt.setString(1, idTent); 
			rs=pstmt.executeQuery(); 		
			rs.next();

			// Memorizzo il risultato dell'interrogazione nel Bean

			result = makeInfoImmobileBean(rs);

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

	// Metodo per ottenere le offerte riguardanti un dato tentativo di vendita
    public Vector getOfferte(String idTent) {
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
			pstmt = con.prepareStatement(offertaImmobile); 
			pstmt.clearParameters();
			pstmt.setString(1, idTent); 
			rs = pstmt.executeQuery(); 		

			// Memorizzo il risultato dell'interrogazione nel Bean
			while(rs.next()) 
				result.add(makeContattoBean(rs));

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

	// Metodo per inserire una nuova offerta dato un tentativo di vendita
    public void insertOfferta(String idPot, String idTent, int offerta) {
	    // Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(insertOfferta); 
			pstmt.clearParameters();
			pstmt.setString(1, idPot);
			pstmt.setString(2, idTent); 
			pstmt.setInt(3, offerta); 
 
			rs = pstmt.executeQuery(); 		

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
	
	// Metodo per recuperare i tentativi di vendita di un dato cliente
	public Vector getTentativiPerCliente(String username, String password) {
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
			pstmt = con.prepareStatement(tentativiCliente); 
			pstmt.clearParameters();
			pstmt.setString(1, username);
			pstmt.setString(2, password);
 
			rs = pstmt.executeQuery(); 		
		
			// Memorizzo il risultato dell'interrogazione nel Bean

			while (rs.next())
				result.add(rs.getString("idTentativoVendita"));
			
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
	
	// Metodo per sapere se un dato acquirente potenziale è già registrato nel sistema
	// Ritorna -1 nel caso in cui non esiste
	public String eRegistrato(String nome, String cognome, String telefono) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";

		try {

			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(registered); 
			pstmt.clearParameters();
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setString(3, telefono);
 
			rs = pstmt.executeQuery(); 		
		
			// Memorizzo il risultato dell'interrogazione nel Bean

			if (rs.next())
				result = rs.getString("IdAcquirentePot");
			
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

	public String insertNuovoAcquirentePotenziale(String nome, String cognome, String telefono) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    String newID = "acq" + generaId(); // DA CREARE UN NUOVO ID RANDOM
		
		try {

			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.


			pstmt = con.prepareStatement(insertAcquirenteP); 
			pstmt.clearParameters();

			pstmt.setString(1, newID);
			pstmt.setString(2, nome);
			pstmt.setString(3, cognome);
			pstmt.setString(4, telefono);
 			
			// Eseguo l'inserimento
			rs = pstmt.executeQuery(); 		
					
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		
		// Ritorno il nuovo id;
		return newID;
	}

	public float getPrezzoMinimo(String idTent) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float result = 0;

		try {

			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.

			pstmt = con.prepareStatement(offertaTent); 
			pstmt.clearParameters();

			pstmt.setString(1, idTent);
 			
			// Eseguo
			rs = pstmt.executeQuery(); 		
			rs.next();
			result = rs.getInt("PrezzoMinimo");
					
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


    // metodo per generare gli id degli acquirentipotenziali
    private String generaId(){
		
		// data
		Calendar cal = new GregorianCalendar();
		int giorno = cal.get(Calendar.DAY_OF_MONTH);
		int mese = cal.get(Calendar.MONTH);
		int anno = cal.get(Calendar.YEAR);
		
		// orario
		Calendar calendar = new GregorianCalendar();
		int ore = calendar.get(Calendar.HOUR);
		int minuti = calendar.get(Calendar.MINUTE);
		int secondi = calendar.get(Calendar.SECOND);
		
		String a = ((Integer) (anno - 2013)).toString();
		String m = ((Integer) (mese+1)).toString();
		String g = ((Integer) giorno).toString();
		int o = ore*3600;
		int min = minuti*60;
		int s = secondi;
		int t = o + min + s;
		String id = a + m + g + ((Integer) t).toString();
		
		return id;
		
	}

	
}
