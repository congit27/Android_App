package com.android.baikt3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.baikt3.my_interface.IClickItemLanguagueListener;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LanguaguesAdapter languaguesAdapter;
    RecyclerView languageRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleRecycleView();

    }

    private void handleRecycleView() {
        languageRV = (RecyclerView) findViewById(R.id.languageRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        languageRV.setLayoutManager(layoutManager);
        languageRV.setHasFixedSize(true);
        languaguesAdapter = new LanguaguesAdapter(getData(), MainActivity.this, new IClickItemLanguagueListener() {
            @Override
            public void onClickItemLanguague(Languague languague) {
                handleClickItemLanguagueRV(languague);
            }
        });
        languageRV.setAdapter(languaguesAdapter);
    }

    private List<Languague> getData() {
        List<Languague> languagues = new ArrayList<>();
        languagues.add(new Languague("English", " là một ngôn ngữ Giécmanh Tây thuộc ngữ hệ Ấn-Âu. Dạng thức cổ nhất của ngôn ngữ này được nói bởi những cư dân trên mảnh đất Anh thời sơ kỳ trung cổ."));
        languagues.add(new Languague("Vietnamese", "cũng gọi là tiếng Việt Nam[9] hay Việt ngữ là ngôn ngữ của người Việt và là ngôn ngữ chính thức tại Việt Nam."));
        languagues.add(new Languague("Chinese", "là một nhóm các ngôn ngữ tạo thành một ngữ tộc trong ngữ hệ Hán-Tạng."));
        languagues.add(new Languague("Russian", "à một ngôn ngữ Đông Slav bản địa của người Nga ở Đông Âu."));
        return languagues;
    }

    private void handleClickItemLanguagueRV(Languague language) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("language",language);
        startActivity(intent);
    }
}