package id.pangu.cecimpedan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.cecimpedan.DetailActivity;
import id.pangu.cecimpedan.Model.CecimpedanItem;
import id.pangu.cecimpedan.R;

public class CecimpedanAdapter extends RecyclerView.Adapter<CecimpedanAdapter.CecimpedanViewHolder> {

    private ArrayList<CecimpedanItem> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public CecimpedanAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public CecimpedanAdapter.CecimpedanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cecimpedan, viewGroup, false);
        return new CecimpedanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CecimpedanAdapter.CecimpedanViewHolder cecimpedanViewHolder, int i) {
        cecimpedanViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(ArrayList<CecimpedanItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    class CecimpedanViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cecimpedan_nama) TextView tvCecimpedan;
        @BindView(R.id.tv_id) TextView tvID;

        public CecimpedanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final CecimpedanItem item) {
            tvCecimpedan.setText(item.getCecimpedan());
            tvID.setText(String.valueOf(item.getId()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra(DetailActivity.EXTRA_INTENT, item);
                    context.startActivity(i);
                }
            });
        }
    }
}
