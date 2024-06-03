package com.example.bigproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class SecondPage extends AppCompatActivity {
    private GameAdapter gameAdapter;
    private List<Game> gameList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initGames();
        gameAdapter = new GameAdapter(SecondPage.this,
                R.layout.game_item, gameList);
        ListView listView = (ListView) findViewById(R.id.list_view_game);
        listView.setAdapter(gameAdapter);
    }
    private void initGames() {
        Game wohn = new Game("魔法使之夜", R.drawable.wohn);
        gameList.add(wohn);
        Game witcher = new Game("巫师3 狂猎", R.drawable.witcher);
        gameList.add(witcher);
        Game uo = new Game("圣兽之王", R.drawable.uo);
        gameList.add(uo);
        Game nikke = new Game("胜利女神：NIKKE", R.drawable.nikke);
        gameList.add(nikke);
        Game tdj = new Game("天地劫：幽城再临", R.drawable.tdj);
        gameList.add(tdj);
        Game gfr = new Game("碧蓝幻想Relink", R.drawable.gfr);
        gameList.add(gfr);
    }
}
