package ua.dashan.bitsandpizzas;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
    private int currentPosition = 0;
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;

    /*ActionBarDrawerToggle — особой разновидностью DrawerListener,
работающей с панелью действий. Она обеспечивает прослушивание
   событий DrawerLayout, как и обычный объект DrawerListener,
   а также позволяет открывать и закрывать выдвижную панель, щелкая
   на значке на панели действий.*/
    private ActionBarDrawerToggle drawerToggle;
    private ShareActionProvider shareActionProvider;
    /*Метод onItemClick() реализаци OnItemClickListener вызывается
    тогда, когда пользователь щелкает на одном из вариантов списка на выдвижной панели.*/
    //Реализует OnItemClickListener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Код, выполняемый при выборе варианта в списке
            //По щелчку на элементе вызывается метод selectItem()
            selectItem(position);

        }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //заполняем массив данными из strings.xml
        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Для заполнения ListView используется класс ArrayAdapter.
       /* Режим simple_list_item_activiated_1 означает,
          что вариант, на котором щелкнул пользователь, выделяется подсветкой.*/
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
       /* Добавить новый экземпляр OnItemClickListener
        к списковому представлению выдвижной панели*/
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        /*При исходном создании MainActivity использовать метод selectItem()
        для отображения TopFragment.*/
        if (savedInstanceState != null) {
            /*Если активность была уничтожена и создается заново, взять
            значение currentPosition из предыдущего состояния активности
            и использовать его для назначения заголовка панели действий.*/
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        } else {
            selectItem(0);

        }
        //Создание ActionBarDrawerToggle (это наша выдвижная панель)
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            //Вызывается при переходе выдвижной панели в полностью закрытое состояние.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //Вызывать invalidateOptionsMenu() при открытии или закрытии выдвижной панели.
                invalidateOptionsMenu();
            }

            //Вызывается при переходе выдвижной панели в полностью открытое состояние.
            public void onDraverOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        //Назначить ActionBarDrawerToggle слушателем для DrawerLayout
        drawerLayout.setDrawerListener(drawerToggle);
        /*Включить кнопку Ввверх, чтобы она могла использоваться объек-
                том ActionBarDrawerToggle.*/
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragMan=getFragmentManager();
                //Получает фрагмент, в настоящее время связанный с активностью.
                Fragment fragment=fragMan.findFragmentByTag("visible_fragment");
                //Проверить, к какому типу относится фрагмент, и присвоить соответствующее значение currentPosition.
                if (fragment instanceof TopFragment) {
                    currentPosition = 0;
                }
                if (fragment instanceof PizzaFragment) {
                    currentPosition = 1;
                }
                if (fragment instanceof PastaFragment) {
                    currentPosition = 2;
                }
                if (fragment instanceof StoresFragment) {
                    currentPosition = 3;
                }
                setActionBarTitle(currentPosition);
                //Вывести текст на панели действий и выделить правильный вариант в списке на выдвижной панели.
                drawerList.setItemChecked(currentPosition, true);

            }
        });

    }
   // Метод selectItem() вызывается при выборе пользователем варианта в списке на выдвижной панели.
    //Проверить позицию элемента,на котором был сделан щелчок.
    private void selectItem(int position) {
        currentPosition = position;
        Fragment fragment;
        switch (position) {
           /* В зависимости от позиции создается фрагмент правильного
            типа. Например, вариант “Pizzas” находится в позиции 1, поэтому
            в этом случае создается фрагмент PizzaFragment.*/
            case 1:
                fragment = new PizzaFragment();
                break;
            case 2:
                fragment = new PastaFragment();
                break;
            case 3:
                fragment = new StoresFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        //Отобразить фрагмент
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible_fragment");
        ft.addToBackStack(null);
        //Для замены текущего фрагмента используется транзакция фрагмента
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        /*Вызвать метод setActionTitle()
        и передать ему позицию варианта,
                на котором был сделан щелчок.*/
        //Вывести правильный текст заголовка на панели действий
        setActionBarTitle(position);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* drawerList — выдвижная панель, связанная
        с DrawerLayout. Вызов приказывает объекту
        DrawerLayout закрыть панель drawerList.*/
        drawerLayout.closeDrawer(drawerList);

    }
    //Вызывается при каждом вызове invalidateOptionsMenu()
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Задать видимость действия Share при открытии и закрытии выдвижной панели
        // Если выдвижная панель открыта, скрыть элементы, связанные с контентом
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    //Синхронизировать состояние ActionBarDrawerToggle с состоянием выдвижной панели
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Синхронизировать состояние выключателя после onRestoreInstanceState.
        drawerToggle.syncState();
    }
    @Override
    // Для передачи информации о любых изменениях конфигурации ActionBarDrawerToggle
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Передать информацию об изменениях конфигурации ActionBarDrawerToggle.
        drawerToggle.onConfigurationChanged(newConfig);
    }


    // изменяем заголовок на панели действий, чтобы он соответствовал текущему фрагменту.
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0) {
            /*Если пользователь выбрал вариант
            “Home”, в качестве текста заголовка
            используется имя приложения.*/
            title = getResources().getString(R.string.app_name);
        } else {
            /*В противном случае получить из массива
            titles строку, соответствующую позиции
            выбранного варианта, и использовать ее.*/
            title = titles[position];
        }
        //Вывести заголовок на панели действий
        getActionBar().setTitle(title);

    }



    @Override
   /* Реализация этого метода добавляет
    на панель действий все элементы дейст-
    вий, содержащиеся в файле ресурсов меню.*/
    public boolean onCreateOptionsMenu(Menu menu) {
        //Для добавления элементов на панель действий используется вызов
        getMenuInflater().inflate(R.menu.menu_main, menu);
       /* Получить ссылку на провайдера действия передачи информации и при-
                своить ее приватной переменной.Затем вызвать метод setIntent().*/
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setIntent("Stas");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /*Этот метод выполняется каждый раз,
    когда выбирается элемент на панели действий.*/
    /*Объект MenuItem представляет элемент на панели действий,
    на котором был сделан щелчок.*/
    public boolean onOptionsItemSelected(MenuItem item) {
        //Поручить обработку ActionBarDrawerToggle.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_create_order:
                /*Интент используется для запуска OrderActivity
                при выборе элемента действия Create Order.*/
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);

                /*Возвращаемое значение true сообщает Android,
                что щелчок на элементе обработан.*/
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Мы создаем метод setIntent(), который создает
     интент и передает его провайдеру действия передачи
     информации при помощи его метода setShareIntent().*/
    private void setIntent(String txt) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, txt);
        shareActionProvider.setShareIntent(intent);
    }




    @Override
    /*Сохранить состояние currentPosition, если
    активность готовится к уничтожению*/
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }

}
