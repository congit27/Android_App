package com.android.baikt1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView singleRV;
    private List<Single> singleList;
    private SingleAdapter singleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
        singleRV = (RecyclerView) findViewById(R.id.singleRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        singleRV.setLayoutManager(layoutManager);
        singleRV.setHasFixedSize(true);
        singleAdapter = new SingleAdapter(singleList,this);
        singleRV.setAdapter(singleAdapter);
    }

    private void getData() {
        singleList = new ArrayList<>();

        singleList.add(new Single("Justin Bieber", "Bieber", "Canada", R.drawable.bieber, R.drawable.five));
        singleList.add(new Single("Lee Ji-eun", "IU", "Korea", R.drawable.iu, R.drawable.five));
        singleList.add(new Single("Peter Gene Hernandez", "Bruno Mars", "USA", R.drawable.brunomars, R.drawable.five));
        singleList.add(new Single("Shakira Isabel Mebarak Ripoll", "Shakira", "Spain", R.drawable.shakira, R.drawable.five));
    }
}