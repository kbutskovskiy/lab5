package com.example.laba5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//#TODO: В рамках одной сессии у курьера есть кнопка принять. Все заказы , которые были выбраны,
//#TODO: уходят в меню курьера, которое можно посмотреть контекстное меню ContextMenu.
public class MainActivity extends AppCompatActivity implements NewOrderDialog.OnNewOrderListener{

    //курьер
    Courier courier = new Courier("Буцковский Кирилл Антонович", "55555555228");

    //посылки
    SmallPackage clock = new SmallPackage("30*40*50", false, "Самара", "Сочи");
    SmallPackage keys = new SmallPackage("10*10*40", true, "Томск", "Москва");
    Documents document = new Documents("Киров", "Самара");
    BigPackage playstation = new BigPackage("100*70*80", false, 10, "Москва", "Благовещенск");
    BigPackage chair = new BigPackage("100*100*100", false, 5, "Хабаровск", "Новокузнецк");
    SmallPackage tshirt = new SmallPackage("10*10*2", false, "Москва", "Санкт-Петербург");
    SmallPackage sneakers = new SmallPackage("30*20*10", false, "Казань", "Владивосток");
    SmallPackage flashDrive = new SmallPackage("5*5*1", true, "Сочи", "Красноярск");
    BigPackage tv = new BigPackage("100*70*10", false, 20, "Уфа", "Мурманск");
    BigPackage laptop = new BigPackage("40*30*5", false, 3, "Саратов", "Ростов-на-Дону");
    BigPackage phone = new BigPackage("15*8*1", false, 1, "Калининград", "Тула");
    BigPackage battery = new BigPackage("20*20*10", true, 2, "Тверь", "Нижний Новгород");

    //компании
    Company yandex = new Company("Yandex", "Москва, ул. Льва Толстого, 16");
    Company google = new Company("Google", "America");
    Company ozon = new Company("Ozon", "Москва, Пресненская Набережная 10");
    Company avito = new Company("Avito", "Москва, Лесная улица, 7, А");
    Company amazon = new Company("Amazon", "Seattle, WA, USA");
    Company apple = new Company("Apple", "Cupertino, CA, USA");
    Company ebay = new Company("Ebay", "San Jose, CA, USA");

    //заказы
    Order order1 = new Order(yandex, keys, keys.getFrom(), keys.getTo(), 1200);
    Order order2 = new Order(ozon, clock, clock.getFrom(), clock.getTo(), 1500);
    Order order3 = new Order(google, document, document.getFrom(), document.getTo(), 1600);
    Order order4 = new Order(google, playstation, playstation.getFrom(), playstation.getTo(), 3500);
    Order order5 = new Order(avito, chair, chair.getFrom(), chair.getTo(), 5000);
    Order order6 = new Order(amazon, tshirt, tshirt.getFrom(), tshirt.getTo(), 800);
    Order order7 = new Order(apple, sneakers, sneakers.getFrom(), sneakers.getTo(), 1800);
    Order order8 = new Order(ebay, flashDrive, flashDrive.getFrom(), flashDrive.getTo(), 300);
    Order order9 = new Order(amazon, tv, tv.getFrom(), tv.getTo(), 4500);
    Order order10 = new Order(apple, laptop, laptop.getFrom(), laptop.getTo(), 6000);
    Order order11 = new Order(ebay, phone, phone.getFrom(), phone.getTo(), 4000);
    Order order12 = new Order(amazon, battery, battery.getFrom(), battery.getTo(), 1500);

    private ListView listView;
    private OrderAdapter adapter;

    private List<Order> selectedOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView courier_name = findViewById(R.id.courier_name);
        courier_name.setText(courier.getFIO());

        courier.setFeatures("Имеется личный автомобиль");

        TextView features = findViewById(R.id.couruier_features);
        features.setText(courier.getFeatures());

        courier.addOrder(order1);
        courier.addOrder(order2);
        courier.addOrder(order3);
        courier.addOrder(order4);
        courier.addOrder(order5);
        courier.addOrder(order6);
        courier.addOrder(order7);
        courier.addOrder(order8);
        courier.addOrder(order9);
        courier.addOrder(order10);
        courier.addOrder(order11);
        courier.addOrder(order12);


        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        adapter = new OrderAdapter(this, courier.getOrders());


        listView.setAdapter(adapter);

        Button btn_ok = findViewById(R.id.button_ok);
        Button btn_clear = findViewById(R.id.button_clear);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    if (adapter.getOrders().get(i).isSelected()) {
                        result += Double.parseDouble(adapter.getOrders().get(i).getCost());
                        selectedOrders.add(adapter.getOrders().get(i));
                    }
                }
                // Remove selected orders from courier's orders
                courier.getOrders().removeAll(selectedOrders);
                // Notify adapter to update the list view
                adapter.notifyDataSetChanged();
                showInfo(result);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    adapter.getOrders().get(i).setSelected(false);
                }
                adapter.notifyDataSetChanged();

            }
        });

        Button btn_show = findViewById(R.id.button_show);
        registerForContextMenu(btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });

        //Button btnAdd = findViewById(R.id.button_add);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NewOrderDialog newOrderDialog = new NewOrderDialog();
//                newOrderDialog.show(getSupportFragmentManager(), "newOrderDialog");
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        for (Order order : selectedOrders) {
            String menuTitle = "Тип: " + order.getPack().getType() + "; " + order.getAddrFrom() + " -> " + order.getAddrTo() + " " +
                    ": " + order.getCost();
            menu.add(Menu.NONE, Menu.NONE, Menu.NONE, menuTitle);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.add_to_menu) {
            // вы можете добавить заказ в список заказов курьера и удалить его из доступных заказов
            Order selectedOrder = adapter.getOrders().get(info.position);
            courier.addOrder(selectedOrder);
            adapter.getOrders().remove(info.position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Order added to the menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onNewOrderCreated(Order newOrder) {
        courier.addOrder(newOrder);
        adapter.notifyDataSetChanged();
    }

    private void showInfo(double cost) {
        Toast.makeText(this, "Общая стоимость: " + cost, Toast.LENGTH_LONG).show();
    }

}