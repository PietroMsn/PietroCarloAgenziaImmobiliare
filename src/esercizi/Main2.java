
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import did.*;

/**
 * Questa classe gestisce le richieste HTTP
 */
public class Main extends HttpServlet {

    /**
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Definizione e recupero dell'eventuale parametro della servlet 
		// ps indica la pagina da lanciare
		String ps = "";

		// Definisco l'oggetto Dispatcher necessario per passare il controllo ad una JSP
		RequestDispatcher rd = null;
	
		// Ottengo se presente il parametro ps
		if (request.getParameter("ps") != null) {
			ps = request.getParameter("ps");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();

			// Lancio la JSP HomePage
			if (ps.equals("")) {                

				// Preparo il Dispatcher
				// Passo alla JSP i contatori per i tre tipo di immobili
                request.setAttribute("conteggioPalazzina", dbms.getPalazzinaCounter());
                request.setAttribute("conteggioSchiera", dbms.getSchieraCounter());
                request.setAttribute("conteggioSingola", dbms.getSingolaCounter());
				rd = request.getRequestDispatcher("./HomePage.jsp");
			}			
				
			// Lancio la JSP TentativiDiVenditaPage.jsp
			if (ps.equals("tvp")) {

				String tipo = "";	
			
				// Recupero il tipo di immobile
                if (request.getParameter("tipo") != null) {
					tipo = request.getParameter("tipo");
				} 

                if (!tipo.equals("palazzina") && !tipo.equals("schiera") && !tipo.equals("singola"))
                    rd = request.getRequestDispatcher("./error.jsp");
                else {

				    // Preparo l'attributo che indica il tipo dell'immobile
				    request.setAttribute("tipoImmobile", tipo);
				
				    // Preparo l'attributo che indica il Vector di tentativi di vendita
				    request.setAttribute("tentativi", dbms.getTentativiImmobile(tipo));					
					
                    // Preparo il Dispatcher
				    rd = request.getRequestDispatcher("./TentativiDiVenditaPage.jsp");	
                }
            } 
				
			// Lancio la JSP ImmobilePage per la visualizzazione dell'immobile e dei form
			if (ps.equals("imm")) { 
				String idTent = "";	

    			// Recupero idTent            
				if (request.getParameter("idTent") != null) {
					idTent = request.getParameter("idTent");
				} 

                // Preparo il Dispatcher

				request.setAttribute("immobile", dbms.getImmobile(idTent));

                request.setAttribute("minprice", (int) dbms.getPrezzoMinimo(idTent));

				rd = request.getRequestDispatcher("./ImmobilePage.jsp");
			}
		} catch(Exception e) {  
			// Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}

        if (!ps.equals("tvp") && !ps.equals("imm") && !ps.equals(""))
          rd = request.getRequestDispatcher("./error.jsp");

        // Passo il controllo alla JSP
        rd.forward(request,response);
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
			DBMS dbms = new DBMS();
    
        if (ps.equals("offerte")) { 
                boolean correct = false;
				String idTent = "";	
                String username = "";
                String password = "";

				// Recupero idTent
                if (request.getParameter("idTent") != null) {
    				idTent = request.getParameter("idTent");
                }

                // Recupero username
                if (request.getParameter("username") != null) {
    				username = request.getParameter("username");
                }
				
				// Recupero password
                if (request.getParameter("password") != null) {
    				password = request.getParameter("password");
				}
				
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
				}
                if (!correct) 
                 rd = request.getRequestDispatcher("./error.jsp");
				// LANCIARE UNA PAGINA D'ERRORE
		} else if (ps.equals("inserimento")) {

				String idTent = "";	
                String nome = "";
                String cognome = "";
				String telefono = "";
				
				int offerta = 0;

				// Recupero idTent
                if (request.getParameter("idTent") != null) 
    				idTent = request.getParameter("idTent");
                
                // Recupero il nome
                if (request.getParameter("firstname") != null) 
    				nome = request.getParameter("firstname");
                
                // Recupero il cognome
                if (request.getParameter("lastname") != null) 
    				cognome = request.getParameter("lastname");

				// Recupero il telefono
				if (request.getParameter("nTelefono") != null) 
    				telefono = request.getParameter("nTelefono");

                // Recupero l'offerta
				if (request.getParameter("offerta") != null) 
    				offerta = Integer.parseInt(request.getParameter("offerta"));
				
				// Controllo che sia il cliente giusto
				String found = dbms.eRegistrato(nome, cognome, telefono);

				// Se l'acquirente potenziale non è memorizzato nel sistema
				// lo inserisco e ottengo il suo nuovo identificativo.
		
				if (found.equals("")) 
					found = dbms.insertNuovoAcquirentePotenziale(nome, cognome, telefono);
				
				float prezzoMinimo = dbms.getPrezzoMinimo(idTent);


			    if (offerta >= prezzoMinimo) {
					dbms.insertOfferta(found, idTent,offerta);
				}

                request.setAttribute("conteggioPalazzina", dbms.getPalazzinaCounter());
                request.setAttribute("conteggioSchiera", dbms.getSchieraCounter());
                request.setAttribute("conteggioSingola", dbms.getSingolaCounter());
				rd = request.getRequestDispatcher("./HomePage.jsp");

				// GESTIRE IL CASO IN CUI L'OFFERTA NON È VALIDA
		
       		 } 

            throw new Exception();
        } catch(Exception e) {  
			// Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		} // fine catch
        
        if (!ps.equals("inserimento") && !ps.equals("offerte") && !ps.equals("") )
          rd = request.getRequestDispatcher("./error.jsp");

        // Passo il controllo alla JSP
        rd.forward(request,response);
    
    } // fine doPost
}

