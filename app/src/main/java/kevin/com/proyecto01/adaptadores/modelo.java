package kevin.com.proyecto01.adaptadores;

public class modelo {

    String imagen;
    String userName;
    String time;
    String like;

    public modelo(String imagen, String userName, String time, String like) {
        this.imagen = imagen;
        this.userName = userName;
        this.time = time;
        this.like = like;
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
}
