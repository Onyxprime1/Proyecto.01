package kevin.com.proyecto01.modelos;

public class Notificacion {

    private String Emisor;
    private String Receptor;
    private String EsAmigo;


    public Notificacion() {
    }

    public Notificacion(String emisor, String receeptor, String esAmigo) {
        this.Emisor = emisor;
        this.Receptor = receeptor;
        this.EsAmigo = esAmigo;
    }

    public String getEmisor() {
        return Emisor;
    }

    public void setEmisor(String emisor) {
        this.Emisor = emisor;
    }

    public String getReceptor() {
        return Receptor;
    }

    public void setReceptor(String receptor) {
        this.Receptor = receptor;
    }

    public String getEsAmigo() {
        return EsAmigo;
    }

    public void setEsAmigo(String esAmigo) {
        this.EsAmigo = esAmigo;
    }
}
