
package model;

public class agenziaImmoBean {
		//Definizione dei campi del Bean (variabili private: accessibili solo tramite i metodi get e set)

	private int numImmTipo;
	private int numOffImm;
	private int numVani;
	private float supTotImm;
	private float prezzoOfferto;
	private String codiceImm;
	private String indirizzoImm;
	private String cittaImm;
	private String giornoContattoPA;
	private String nomePA;
	private String cognomePA;
	private String telefonoPA;
	private String login;
	private String descrizione;
    private float prezzominimo;
	

	//Definizione del costruttore del Bean
	    public agenziaImmoBean() {
			descrizione = null;
			numImmTipo = 0;
			numOffImm = 0;
			codiceImm = null;
			indirizzoImm = null;
			cittaImm = null;
			supTotImm = 0;
			giornoContattoPA = null;
			nomePA = null;
			cognomePA = null;
			telefonoPA = null;
			prezzoOfferto = 0;
			login = null;
			numVani = 0;
            prezzominimo=0;
		
	    }

    //metodi set
    	public void setNumImmTipo(int i) {
		numImmTipo = i;
    	}

	public void setNumOffTipo(int i) {
		numOffImm = i;
    	}

	public void setNumVani(int i) {
		numVani = i;
    	}

	public void setCodiceImm(String s){
		codiceImm = s;
	}

	public void setIndirizzoImm(String s){
		indirizzoImm = s;
	}

	public void setCittaImm(String s){
		cittaImm = s;
	}

	public void setGiornoContattoPA(String s){
		giornoContattoPA = s;
	}

	public void setNomePA(String s){
		nomePA = s;
	}

	public void setCognomePA(String s){
	    cognomePA = s;
	}

	public void setTelefonoPA(String s){
		telefonoPA = s;
	}

	public void setLogin(String s){
		login = s;
	}

	public void setSupTotImm(float f){
		supTotImm = f;
	}
	
	public void setPrezzoOfferto(float f){
		prezzoOfferto = f;
	}

	public void setDesc(String s){
		descrizione = s;
	}
    
    public void setPrezzoMinimo(float f){
		prezzominimo = f;
	}
	//metodi get
	public int getNumImmTipo() {
		return numImmTipo;
    	}

	public int getNumOffImm() {
		return numOffImm;
    	}

	public int getNumVani() {
		return numVani;
    	}

	public String getCodiceImm(){
		return codiceImm;
	}

	public String getIndirizzoImm(){
		return indirizzoImm;
	}

	public String getCittaImm(){
		return cittaImm;
	}

	public String getGiornoContattoPA(){
		return giornoContattoPA;
	}

	public String getNomePA(){
		return nomePA;
	}

	public String getCognomePA(){
		return cognomePA;
	}

	public String getTelefonoPA(){
		return telefonoPA;
	}

	public String getLogin(){
		return login;
	}

	public float getSupTotImm(){
		return supTotImm;
	}
	
	public float getPrezzoOfferto(){
		return prezzoOfferto;
	}
	
	public String getDesc(){
		return descrizione;
	}

    public float getPrezzoMinimo(){
		return prezzominimo;
	}

}
