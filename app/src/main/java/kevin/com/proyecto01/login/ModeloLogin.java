package kevin.com.proyecto01.login;

public class ModeloLogin {
    private String nombre;
    private String apellido;
    private String correo;
    private String urlImage;
    private String idUser;
    private String contracena;
    private String confirmacion;

    public ModeloLogin() {
    }


    public ModeloLogin(String nombre, String apellido, String correo, String urlImage, String idUser) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.urlImage = urlImage;
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContracena() {
        return contracena;
    }

    public void setContracena(String contracena) {
        this.contracena = contracena;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    @Override
    public String toString() {
        return "ModeloLogin{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", contracena='" + contracena + '\'' +
                ", confirmacion='" + confirmacion + '\'' +
                '}';
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
