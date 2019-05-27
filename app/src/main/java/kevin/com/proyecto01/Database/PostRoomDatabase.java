package kevin.com.proyecto01.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import kevin.com.proyecto01.Database.Dao.PostDao;
import kevin.com.proyecto01.Database.Entidad.PostEntity;

@Database(entities = {PostEntity.class}, version = 1)
public abstract class PostRoomDatabase extends RoomDatabase {

    public abstract PostDao postDao();
    private static volatile PostRoomDatabase INSTANCE;

    public static PostRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PostRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostRoomDatabase.class, "post_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }



}
