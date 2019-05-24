package kevin.com.proyecto01.modelos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import kevin.com.proyecto01.Database.Dao.ProgramerDao;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.Database.ProgramerRoomDatabase;

public class ProgramadorRepository {

    private ProgramerDao programerDao;
    private LiveData<List<ProgramerEntity>> allProgramers;
    private LiveData<List<ProgramerEntity>> allProgramersFavorites;

    public ProgramadorRepository(Application application){
        ProgramerRoomDatabase database = ProgramerRoomDatabase.getDatabase(application);
        programerDao = database.programerDao();
        allProgramers = programerDao.getAll();
    }

    public void insert(ProgramerEntity programer){
        new InsertAsyncTask(programerDao).execute(programer);
    }

    private class InsertAsyncTask  extends AsyncTask<ProgramerEntity, Void, Void> {

        private ProgramerDao dao;

        public InsertAsyncTask(ProgramerDao programerDao) {
            this.dao = programerDao;
        }

        @Override
        protected Void doInBackground(ProgramerEntity... programerEntities) {
            dao.insertar(programerEntities[0]);

            return null;
        }
    }
}
