package ua.dashan.bitsandpizzas;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
/*ViewHolder предоставляет ссылку на представление (или
        представления) каждого варианта данных в RecyclerView; это
        своего рода «ячейка» для размещения отображаемых данных.
        При создании адаптера для RecyclerView необходимо создать
        ViewHolder внутри адаптера. Для этого вы расширяете класс
        RecyclerView.ViewHolder и указываете, данные какого типа
        он должен содержать.
        Каждый вариант в RecyclerView представляет собой кар-
        точку; следовательно, класс ViewHolder должен содер-
        жать CardView*/
public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    //Предоставляет ссылку на представления, используемые в RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder{
        /*В нашем компоненте RecyclerView должны отображаться карточки, поэтому мы указываем, что  ViewHolder
        содержит представления CardView.Если вы захотите отображать в RecyclerView данные другого типа,
        определите их здесь.*/
       private CardView cardView;
        public ViewHolder(CardView v) {
            //При создании реализации ViewHolder необходимо вызвать конструктор суперкласса:
            super(v);
            cardView = v;

    } }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Заполнение заданного представления данными
    }

    @Override
    public int getItemCount() {
        //Возвращает количество вариантов в наборе данных
    }



}
