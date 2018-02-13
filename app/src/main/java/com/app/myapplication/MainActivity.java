package com.app.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = getSampleData();
        listView = findViewById(R.id.listView);

        listView.setAdapter(new CustomAdapter(this, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked Item==>" + items.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Item> getSampleData() {
        List<Item> itemList = new ArrayList<>();

        Item item = new Item();
        item.setId("1");
        item.setName("Item 1");
        item.setOther("Roll");
        item.setQty("3");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");

        itemList.add(item);

        item = new Item();
        item.setId("2");
        item.setName("Item 2");
        item.setOther("Roll");
        item.setQty("3");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");

        itemList.add(item);

        item = new Item();
        item.setId("3");
        item.setName("Item 3");
        item.setOther("Roll");
        item.setQty("3");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/boat.png");

        itemList.add(item);

        item = new Item();
        item.setId("4");
        item.setName("Item 4");
        item.setOther("Roll");
        item.setQty("2");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/goldhill.png");

        itemList.add(item);

        item = new Item();
        item.setId("5");
        item.setName("Item 5");
        item.setOther("Roll");
        item.setQty("3");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/monarch.png");

        itemList.add(item);

        item = new Item();
        item.setId("6");
        item.setName("Item 6");
        item.setOther("Roll");
        item.setQty("1");
        item.setImgURL("https://homepages.cae.wisc.edu/~ece533/images/mountain.png");

        itemList.add(item);


        return itemList;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public class CustomAdapter extends BaseAdapter {
        List<Item> itemList;
        Context mContext;

        public CustomAdapter(Context context, List<Item> itemList) {
            this.itemList = itemList;
            mContext = context;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return itemList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_listview, viewGroup, false);
            }
            Item item = itemList.get(i);
            ImageView imgItem = view.findViewById(R.id.imgItem);
            TextView textDescription = view.findViewById(R.id.textDescription);
            TextView textQuantity = view.findViewById(R.id.textQuantity);
            TextView text = view.findViewById(R.id.text);
            EditText edPrice = view.findViewById(R.id.edPrice);
            textDescription.setText(fromHtml("<b>D</b> " + item.getName()));
            textQuantity.setText(fromHtml("<b>Q</b> " + item.getQty()));
            text.setText(fromHtml("<b>U</b> " + item.getOther()));
            Glide.with(mContext)
                    .load(item.getImgURL())
                    .into(imgItem);
            return view;
        }
    }
}
