package com.example.laba5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    Firm firm1 = new Firm("DHL", "Pushkina stret Kolotushkino house");
    Package Package1 = new Big("B", false, "Car");
    Package Package2 = new Big("S", true, "none");
    Package Package3 = new Big("D", false, "recipient name");
    Order order1 = new Order(firm1, Package1, "Москва", "Питер", 500);
    Order order2 = new Order(firm1, Package2, "Омск", "Сочи", 300);
    Order order3 = new Order(firm1, Package3, "Пермь", "Орел", 100);
    String[] firmNames = {
            order1.getFirm().getName(), order2.getFirm().getName(), order3.getFirm().getName()
    };
    String[] packType = {
            order1.getPackage().getSize(), order2.getPackage().getSize(), order3.getPackage().getSize()
    };
    String[] sourceAddress = {
            order1.getSourceAddress(), order2.getSourceAddress(), order3.getSourceAddress()
    };
    String[] destinationAddress = {
            order1.getDestinationAddress(), order2.getDestinationAddress(), order3.getDestinationAddress()
    };
    String[] orderPrice = {
            Integer.toString(order1.getPrice()), Integer.toString(order2.getPrice()), Integer.toString(order3.getPrice())
    };
    ListView price;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView compName = (ListView) findViewById(R.id.compName);
        ListView pType = (ListView) findViewById(R.id.pType);
        ListView SA = (ListView) findViewById(R.id.SA);
        ListView DA = (ListView) findViewById(R.id.DA);
        price = (ListView) findViewById(R.id.price);

        // находим список
        price.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // создаем адаптер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, firmNames);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, packType);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sourceAddress);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, destinationAddress);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, orderPrice);
        // присваиваем адаптер списку
        compName.setAdapter(adapter1);
        pType.setAdapter(adapter2);
        SA.setAdapter(adapter3);
        DA.setAdapter(adapter4);
        price.setAdapter(adapter5);
        Button okButton = (Button) findViewById(R.id.okButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        okButton.setOnClickListener((OnClickListener) this);
        clearButton.setOnClickListener((OnClickListener) this);
    }

    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.okButton) {
            int summa = 0;
            SparseBooleanArray sbArray = price.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    summa += Integer.parseInt(orderPrice[key]);
                }
            }
            Toast toast = Toast.makeText(getApplicationContext(), "Общая сумма выбранных закахов: " + summa + " рублей",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_multiple_choice, orderPrice);
            price.setAdapter(adapter5);
        }
    }
}