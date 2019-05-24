package kevin.com.proyecto01.Database.Entidad;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Programador")
public class ProgramerEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String imagen;
    private String userName;
    private String time;
    private String like;
    private boolean favorito;


    public ProgramerEntity(String imagen, String userName, String time, String like, boolean favorito) {
        this.imagen = imagen;
        this.userName = userName;
        this.time = time;
        this.like = like;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
