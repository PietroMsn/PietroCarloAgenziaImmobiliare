/**
* Bean per rappresentare un'offerta
*/

package did;

public class ContattoBean {
	//Definizione dei campi del Bean
	private String giornoOfferta;
	private double offerta;
	private String nomeAcquirenteP;
	private String cognomeAcquirenteP;
	private String telefonoAcquirenteP;

	//Definizione del costruttore del Bean
    public ContattoBean() {

    }

    // Metodi set
    public void setGiornoOfferta(String data) {
		giornoOfferta = data;
    }

	public void setOfferta(double prezzo) {
		offerta = prezzo;
    }

	public void setNomeAcquirenteP(String nome) {
		nomeAcquirenteP = nome;
	}

	public void setCognomeAcquirenteP(String cognome) {
		cognomeAcquirenteP = cognome;
	}
	
	public void setTelefonoAcquirenteP(String telefono) {
		telefonoAcquirenteP = telefono;
	}
	
    // Metodi get
	public String getGiornoOfferta() {
		return giornoOfferta;
	}

	public double getOfferta() {
		return offerta;
	}

	public String getNomeAcquirenteP() {
		return nomeAcquirenteP;
	}

	public String getCognomeAcquirenteP() {
		return cognomeAcquirenteP;
	}
	
	public String getTelefonoAcquirenteP() {
		return telefonoAcquirenteP;
	}
}
