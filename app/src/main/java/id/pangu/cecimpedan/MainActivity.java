package id.pangu.cecimpedan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.cecimpedan.Adapter.CecimpedanAdapter;
import id.pangu.cecimpedan.Database.CecimpedanHelper;
import id.pangu.cecimpedan.Model.CecimpedanItem;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_cecimpedan) RecyclerView rvCecimpedan;
    CecimpedanAdapter cecimpedanAdapter;
    CecimpedanHelper cecimpedanHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        cecimpedanHelper = new CecimpedanHelper(this);
        cecimpedanAdapter = new CecimpedanAdapter(this);
        rvCecimpedan.setLayoutManager(new LinearLayoutManager(this));
        rvCecimpedan.setAdapter(cecimpedanAdapter);

        cecimpedanHelper.open();
        ArrayList<CecimpedanItem> item = cecimpedanHelper.getAllData();
        cecimpedanHelper.close();

        cecimpedanAdapter.addItem(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
