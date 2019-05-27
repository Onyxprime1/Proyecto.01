package kevin.com.proyecto01.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import kevin.com.proyecto01.Database.Entidad.PostEntity;


public class NuevoPostDialogViewModel extends AndroidViewModel {

    private LiveData<List<PostEntity>> allPosts;
    private PostRepository postRepository;

    public NuevoPostDialogViewModel(Application application){
        super(application);

        postRepository = new PostRepository(application);
        allPosts = postRepository.getAll();
    }


    public LiveData<List<PostEntity>> getAllPosts(){return allPosts;}

    public void insertarPost(PostEntity post){
        postRepository.insert(post);}

    public void actualizarPost(PostEntity post){
        postRepository.update(post);}
}
