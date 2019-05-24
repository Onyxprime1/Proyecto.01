package kevin.com.proyecto01.adaptadores;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kevin.com.proyecto01.tabs.Amigos;
import kevin.com.proyecto01.tabs.Favoritos;
import kevin.com.proyecto01.tabs.Historial;
import kevin.com.proyecto01.tabs.Repositorios;

public class TabsAdapter extends FragmentPagerAdapter {

    public static final int NUMERO_DE_PESTAÑAS = 4;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override // Devuelve la posicion de las pestañas
    public Fragment getItem(int positionTabs) {

        switch (positionTabs){
            case 0:
                //Devuelve la pestaña de Repositorios
                Repositorios repositorios = new Repositorios();
                return repositorios;
            case 1:
                //Devuelve la pestaña de Amigos
                Amigos amigos = new Amigos();
                return amigos;
            case 2:
                //Devuelve la pestaña de Historial
                Historial historial = new Historial();
                return historial;
            case 3:
                //Devuelve la pestaña de Favoritos
                Favoritos favoritos = new Favoritos();
                return favoritos;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        //Devuelve la cantidad pestañas visibles
        return NUMERO_DE_PESTAÑAS;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Coloca el nombre de cada pestaña segun su posicion
        switch (position){
            case 0:
                return "Repositorios";
            case 1:
                return "Amigos";
            case 2:
                return "Historial";
            case 3:
                return "Favoritos";
            default:
                return null;
        }
    }
}
