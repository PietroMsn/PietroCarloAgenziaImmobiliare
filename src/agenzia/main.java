import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;

/**
 * Questa classe gestisce le richieste HTTP
 * 
 */
public class main extends HttpServlet {
    /**
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		//Definizione e recupero dell'eventuale parametro della servlet
		String ps = "";
		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;
	
		if (request.getParameter("ps") != null) {// Ottengo se presente il parametro 'ps'
			ps = request.getParameter("ps");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();
			if (ps.equals("")) {

				// Parametro ps assente o vuoto, visualizzo la home page del sito.
				 
			    request.setAttribute("numVillette1", dbms.getNumImmTipo("villetta a schiera"));
                request.setAttribute("numVille1", dbms.getNumImmTipo("villa singola"));
                request.setAttribute("numAppartamenti1", dbms.getNumImmTipo("appartamento in palazzina"));
				
                //Preparo il Dispatcher
				rd = request.getRequestDispatcher("./index.jsp");
                
			}			

			
			if (ps.equals("villa singola") || ps.equals("villetta a schiera") || ps.equals("appartamento in palazzina") ) { 
           
                //Visualizzo le informazioni di dettaglio del dipartimento
                
                request.setAttribute("Tipo", ps);

                Vector vettore = dbms.getTentVenditaTipo(ps);
				request.setAttribute("inVendita", vettore);

				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("./tentativoVendita.jsp");	
			}

            if (ps.startsWith("codice_" )) { 
           
                //Vector vettore = dbms.getTentVenditaTipo("villa singola");
                Vector vettore = dbms.getInfoImm(ps.substring(7));
                
                request.setAttribute("infoImmobile", vettore);
                
                request.setAttribute("numOfferte", dbms.getNumOfferte(ps.substring(7)));

				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("./immobilePage.jsp");	
			}

            if (ps.startsWith("offerte_" )) { 
           
                Vector vettore= new Vector();
                request.setAttribute("offerteImm", vettore);
                 
				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("./offertePage.jsp");	
			}
		   
                 try {
                //Passo il controllo alla JSP
                rd.forward(request,response);
                    } catch(NullPointerException e) {

		    //se il login e' errato invio una segnalazione d'errore
		    rd = request.getRequestDispatcher("./error.jsp");  
		    rd.forward(request,response);
		    
			}
		} catch(Exception e) {  //Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }

    
    /**
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ps = "";

		//Definisco l'oggetto Dispatcher necessario per passare il controllo ad una JSP
		RequestDispatcher rd = null;
	
		// Ottengo se presente il parametro ps
		if (request.getParameter("ps") != null) {
			ps = request.getParameter("ps");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();request.getAttribute("numOfferte");
    
        if (ps.equals("login")) { 
                
                String codiceImm = "";
		String username = "";	
                String password = "";
                String esito = "";
                

                // Recupero codice immobile
                if (request.getParameter("codImm") != null) {
    				codiceImm = request.getParameter("codImm");
                }

				// Recupero username
                if (request.getParameter("user") != null) {
    				username = request.getParameter("user");
                }

                // Recupero password
                if (request.getParameter("pass") != null) {
    				password = request.getParameter("pass");
                }

                esito = dbms.getAccess(username, password);

                if(esito.equals(username)){
                       
                       request.setAttribute("offerteImm", dbms.getInfoOfferte(username, codiceImm));
                    	rd = request.getRequestDispatcher("./offertePage.jsp");
                }
                
				/*
				// Controllo che sia il cliente giusto recuperando
				// i tentativi di vendita attivi per quel cliente.
				Vector tentativi = (Vector) dbms.getTentativiPerCliente(username, password);
			
                for (int i= 0; i < tentativi.size(); i++) {
					String id = (String) tentativi.get(i);

					// Se l'idTent è nei tentativi di vendita del cliente lancio la JSP delle offerte
					if (id.equals(idTent)) {
                        correct = true;
						// Lancio la JSP che visualizza le offerte relative ad un tentativo di vendita
						request.setAttribute("offerte", dbms.getOfferte(idTent));
                		//Preparo il Dispatcher
						rd = request.getRequestDispatcher("./OffertePage.jsp");		
					} 
				}*/
                //else
                    //rd = request.getRequestDispatcher("./error.jsp");
				// LANCIARE UNA PAGINA D'ERRORE
            
		} else if (ps.equals("inserimento")) {

				int idTent = 0;	
				String nome = "";
				String cognome = "";
				String telefono = "";
				
				int offerta = 0;

				// Recupero idTent
				if (request.getParameter("idTent") != null) 
		    				idTent = Integer.parseInt(request.getParameter("idTent"));
				
				// Recupero il nome
				if (request.getParameter("Nome") != null) 
		    				nome = request.getParameter("Nome");
				
				// Recupero il cognome
				if (request.getParameter("Cognome") != null) 
		    				cognome = request.getParameter("Cognome");

				// Recupero il telefono
				if (request.getParameter("Telefono") != null) 
    				telefono = request.getParameter("Telefono");

                // Recupero l'offerta
				if (request.getParameter("CifraOfferta") != null) 
    					offerta = Integer.parseInt(request.getParameter("CifraOfferta"));
				
				// Controllo che sia il cliente giusto
				//String found = dbms.eRegistrato(nome, cognome, telefono);

				// Se l'acquirente potenziale non è memorizzato nel sistema
				// lo inserisco e ottengo il suo nuovo identificativo.
		
				/*if (found.equals("")) 
					found = dbms.insertNuovoAcquirentePotenziale(nome, cognome, telefono);
				
				float prezzoMinimo = dbms.getPrezzoMinimo(idTent);*/

                		System.out.println("\n" + offerta + "\n");
				dbms.insertOfferta( nome, cognome, idTent, offerta, telefono);
				
                

               // Parametro ps assente o vuoto, visualizzo la home page del sito.
				 
			    	request.setAttribute("numVillette1", dbms.getNumImmTipo("villetta a schiera"));
				request.setAttribute("numVille1", dbms.getNumImmTipo("villa singola"));
				request.setAttribute("numAppartamenti1", dbms.getNumImmTipo("appartamento in palazzina"));
				
				rd = request.getRequestDispatcher("./index.jsp");

				// GESTIRE IL CASO IN CUI L'OFFERTA NON È VALID
		
       		 } 

            //throw new Exception();
        } catch(Exception e) {  
			// Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		} // fine catch
        
        /*if (!ps.equals("inserimento") && !ps.equals("offerte") && !ps.equals("") )
          rd = request.getRequestDispatcher("./error.jsp");
	*/
        try { 

        // Passo il controllo alla JSP
        rd.forward(request,response);

        } catch(NullPointerException e) {

            //se il login e' errato invio una segnalazione d'errore
            rd = request.getRequestDispatcher("./error.jsp");  
            rd.forward(request,response);
		    
		}
    } // fine doPost
}
