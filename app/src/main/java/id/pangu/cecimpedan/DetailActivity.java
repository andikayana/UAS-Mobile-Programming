package id.pangu.cecimpedan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.cecimpedan.Model.CecimpedanItem;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_INTENT = "extra_intent";
    @BindView(R.id.tv_cecimpedan) TextView tvCecimpedan;
    @BindView(R.id.tv_arti) TextView tvArti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Arti Cecimpedan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CecimpedanItem item = getIntent().getParcelableExtra(EXTRA_INTENT);
        if(item != null){
            tvCecimpedan.setText(item.getCecimpedan());
            tvArti.setText(item.getArti());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
