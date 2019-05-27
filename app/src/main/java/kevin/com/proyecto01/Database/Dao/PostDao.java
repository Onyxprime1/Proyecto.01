package kevin.com.proyecto01.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kevin.com.proyecto01.Database.Entidad.PostEntity;

@Dao
public interface PostDao {

    @Insert
    void insertar(PostEntity entity);

    @Update
    void actualizar(PostEntity entity);

    @Query("SELECT * FROM PostEntity ORDER BY fecha")
    LiveData<List<PostEntity>> getAll();
}
