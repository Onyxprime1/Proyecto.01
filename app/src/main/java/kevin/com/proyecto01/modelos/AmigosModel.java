package kevin.com.proyecto01.modelos;

public class AmigosModel {

    private String urlFotoAmigo;
    private String nombreAmigo;

    public AmigosModel(String urlFotoAmigo, String nombreAmigo) {
        this.urlFotoAmigo = urlFotoAmigo;
        this.nombreAmigo = nombreAmigo;
    }

    public String getUrlFotoAmigo() {
        return urlFotoAmigo;
    }

    public void setUrlFotoAmigo(String urlFotoAmigo) {
        this.urlFotoAmigo = urlFotoAmigo;
    }

    public String getNombreAmigo() {
        return nombreAmigo;
    }

    public void setNombreAmigo(String nombreAmigo) {
        this.nombreAmigo = nombreAmigo;
    }
}
