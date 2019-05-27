package kevin.com.proyecto01.modelos;

public class ChatsModel {

    private String imagenPerfil;
    private String nombre;
    private String mensaje;
    private String fecha;
    private int numero;

    public ChatsModel(String imagenPerfil, String nombre, String mensaje, String fecha, int numero) {
        this.imagenPerfil = imagenPerfil;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.numero = numero;
    }

    public ChatsModel() {
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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
