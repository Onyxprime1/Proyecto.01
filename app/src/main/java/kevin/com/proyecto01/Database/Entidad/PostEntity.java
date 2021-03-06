package kevin.com.proyecto01.Database.Entidad;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "PostEntity")
public class PostEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String propietario;
    private String mensaje;
    private String documento;
    private String imagen;
    private String fecha;
    private boolean favorite;
    private int numLikes;
    private String position;

    public PostEntity(){}

    public PostEntity(String titulo, String propietario, String mensaje, String documento, String imagen, String fecha, String position) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.propietario = propietario;
        this.documento = documento;
        this.imagen = imagen;
        this.fecha = fecha;
        this.favorite = false;
        this.numLikes = 0;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
