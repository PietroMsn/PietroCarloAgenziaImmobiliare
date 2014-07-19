/**
* Bean per rappresentare un immobile
*/

package did;

public class ImmobileBean {
	// Definizione dei campi del Bean
	private String idTentativoVendita;
	private String codiceImmobile;	
	private String indirizzoImmobile;
	private String cittaImmobile;
	private double superficieImmobile;
	private int numeroVani;
	private String descrizioneImmobile;

	// Definizione del costruttore del Bean
    public ImmobileBean() {

    }

    // Metodi set
    public void setIdTentativoVendita(String id) {
		idTentativoVendita = id;
    }

	public void setCodiceImmobile(String codice) {
		codiceImmobile = codice;
    }

	public void setIndirizzoImmobile(String indirizzo) {
		indirizzoImmobile = indirizzo;
	}

	public void setCittaImmobile(String citta) {
		cittaImmobile = citta;
	}
	
	public void setSuperficieImmobile(double superficie) {
		superficieImmobile = superficie;
	}

	public void setNumeroVani(int numero) {
		numeroVani = numero;
	}

	public void setDescrizioneImmobile(String descrizione) {
		descrizioneImmobile = descrizione;
	}
	
    // Metodi get
	public String getIdTentativoVendita() {
		return idTentativoVendita;
	}

	public String getCodiceImmobile() {
		return codiceImmobile;
	}

	public String getIndirizzoImmobile() {
		return indirizzoImmobile;
	}

	public String getCittaImmobile() {
		return cittaImmobile;
	}
	
	public double getSuperficieImmobile() {
		return superficieImmobile;
	}

	public int getNumeroVani() {
		return numeroVani;
	}

	public String getDescrizioneImmobile() {
		return descrizioneImmobile;
	}
}
