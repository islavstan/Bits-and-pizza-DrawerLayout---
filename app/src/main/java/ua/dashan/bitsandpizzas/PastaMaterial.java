package ua.dashan.bitsandpizzas;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
//это макет, куда помещаются наши карточки из класса CaptionedImagesAdapter
public class PastaMaterial extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_pasta_material,container,false);
        //Названия пиццы добавляются в массив строк, а изображения — в массив с элементами int.
        String[]pastaName=new String[Pasta.pasta.length];
        for (int i=0;i<pastaName.length;i++){
            pastaName[i]=Pasta.pasta[i].getName();
        }
        int[]pastaImage=new int[Pasta.pasta.length];
        for (int i=0;i<pastaImage.length;i++){
            pastaImage[i]=Pasta.pasta[i].getImageResourceId();
        }
        //Передать массивы адаптеру.
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pastaName, pastaImage);
        pastaRecycler.setAdapter(adapter);
        //Чтобы карточки отображались в линейном списке, используем объект LinearLayoutManager
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        pastaRecycler.setLayoutManager(layoutManager);
        return pastaRecycler;
    }

}
