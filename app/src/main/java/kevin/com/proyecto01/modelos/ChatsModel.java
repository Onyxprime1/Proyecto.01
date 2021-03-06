package kevin.com.proyecto01.modelos;

public class ChatsModel {

    private String imagenPerfil;
    private String id;
    private String nombre;
    private String mensaje;
    private String fecha;

    public ChatsModel(String imagenPerfil, String id, String nombre, String mensaje, String fecha) {
        this.imagenPerfil = imagenPerfil;
        this.id = id;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public ChatsModel() {
    }

    public String getId() {
        return id;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }
}
