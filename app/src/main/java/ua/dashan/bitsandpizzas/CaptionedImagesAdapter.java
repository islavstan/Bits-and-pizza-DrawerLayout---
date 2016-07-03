package ua.dashan.bitsandpizzas;


import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//это всё относится к карточке
//здесь мы создаём  карточки с названиями и изображениями разных видов пиццы
//Карточки определяются в макете card_captioned_image.xml.
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
    //Переменные для названий и идентификаторов ресурсов изображений разных видов пиццы.
    private String[]captions;
    private int[]imageIds;
    //Добавить объект Listener как приватную переменную.
    private Listener listener;
    public static interface Listener {
        public void onClick(int position);
    }
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

    public CaptionedImagesAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }
    //Активности и фрагменты используют этот метод для регистрации себя в качестве слушателя
    public void setListener(Listener listener){
        this.listener = listener;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Заполнение заданного представления данными
        CardView cardView=holder.cardView;
        //Изображение выводится в графическом представлении ImageView
        ImageView imageView=(ImageView)cardView.findViewById(R.id.info_image);
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        //Название выводится в компоненте TextView
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //При щелчке на CardView вызвать метод onClick() интерфейса Listener.
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //Возвращает количество вариантов в наборе данных
        return captions.length;//Количество элементов данных.
    }



}
