package kevin.com.proyecto01.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import java.util.List;

import kevin.com.proyecto01.Database.Dao.PostDao;
import kevin.com.proyecto01.Database.Entidad.PostEntity;

public class PostRepository {

    private PostDao postDao;
    private LiveData<List<PostEntity>> allPosts;
    private LiveData<List<PostEntity>> allProgramersFavorites;

    public PostRepository(Application application){
        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDao = database.postDao();
        allPosts = postDao.getAll();
    }

    public LiveData<List<PostEntity>> getAll() { return allPosts; }

    public void insert (PostEntity post) {
        new InsertAsyncTask(postDao).execute(post);
    }

    public void update(PostEntity post){
        new UpdateAsyncTask(postDao).execute(post);
    }


    private static class InsertAsyncTask extends AsyncTask<PostEntity, Void, Void>{

        private PostDao postDao;

        InsertAsyncTask(PostDao postDao){
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(PostEntity... postEntities) {

            postDao.insertar(postEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<PostEntity, Void, Void>{

        private PostDao postDao;

        UpdateAsyncTask(PostDao postDao){
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(PostEntity... postEntities) {

            postDao.actualizar(postEntities[0]);
            return null;
        }
    }

}


