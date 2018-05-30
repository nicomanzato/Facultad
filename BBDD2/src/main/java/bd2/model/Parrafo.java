package bd2.model;


public class Parrafo {

	private long idParrafo;
	private String texto;
	private Documento documento;
	
	public Parrafo(){
		
	}
	
	public Parrafo(String p, Documento doc){
		this.texto = p;
		this.documento = doc;
	}
	
	public String getTexto(){
		return texto;
	}
	
	public void setTexto(String t){
		this.texto = t;
	}
	
	public Documento getDocumento(){
		return documento;
	}
	
	public void setDocumento(Documento doc){
		this.documento = doc;
	}

	public long getIdParrafo() {
		return idParrafo;
	}

	public void setIdParrafo(long idParrafo) {
		this.idParrafo = idParrafo;
	}

	
	
	
}
