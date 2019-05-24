package kevin.com.proyecto01.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;

@Dao
public interface ProgramerDao {


    @Insert
    void insertar(ProgramerEntity entity);

    @Update
    void actualizar(ProgramerEntity entity);

    @Query("SELECT * FROM Programador ORDER BY userName")
    LiveData<List<ProgramerEntity>> getAll();

}
