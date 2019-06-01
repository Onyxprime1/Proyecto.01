package kevin.com.proyecto01.modelos;

public class MessageModel {

    private String receptor;
    private String emisor;
    private String mensaje;
    private String hora;

    public MessageModel(String receptor, String emisor, String mensaje) {
        this.receptor = receptor;
        this.emisor = emisor;
        this.mensaje = mensaje;
    }

    public MessageModel() {
    }

    public String getReceptor() {
        return receptor;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
