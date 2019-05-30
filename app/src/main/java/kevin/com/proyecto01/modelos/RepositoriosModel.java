package kevin.com.proyecto01.modelos;

public class RepositoriosModel {

    private String titulo;
    private String vistas;
    private String puntos;

    public RepositoriosModel(String titulo, String vistas, String puntos) {
        this.titulo = titulo;
        this.vistas = vistas;
        this.puntos = puntos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVistas() {
        return vistas;
    }

    public void setVistas(String vistas) {
        this.vistas = vistas;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}