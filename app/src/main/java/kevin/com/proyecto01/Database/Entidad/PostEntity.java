package kevin.com.proyecto01.Database.Entidad;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "PostEntity")
public class PostEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String mensaje;
    private String documento;
    private String imagen;
    private String fecha;
    private boolean favorite;
    private int numLikes;

    public PostEntity(String titulo, String mensaje, String documento, String imagen, String fecha) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.documento = documento;
        this.imagen = imagen;
        this.fecha = fecha;
        this.favorite = false;
        this.numLikes = 0;
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
}
