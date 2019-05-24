package kevin.com.proyecto01.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import kevin.com.proyecto01.Database.Dao.ProgramerDao;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;

@Database(entities = {ProgramerEntity.class}, version = 1)
public abstract class ProgramerRoomDatabase extends RoomDatabase {

    public abstract ProgramerDao programerDao();
    private static volatile ProgramerRoomDatabase INSTANCE;

    public static ProgramerRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ProgramerRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProgramerRoomDatabase.class, "programer_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
