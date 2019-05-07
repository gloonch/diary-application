package diary.tehranqolhak.diary.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import diary.tehranqolhak.diary.R;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<DiaryModel> diaryModels;

    public ListAdapter(Context mContext, List<DiaryModel> diaryModels) {
        this.mContext = mContext;
        this.diaryModels = diaryModels;
    }

    @Override
    public int getCount() {
        return diaryModels.size();
    }

    @Override
    public Object getItem(int i) {
        return diaryModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.listitem, viewGroup, false);

        TextView descr = view.findViewById(R.id.memmoryDescription);
        TextView date = view.findViewById(R.id.memmoryDate);
        descr.setText(diaryModels.get(i).getDescription());
        date.setText(diaryModels.get(i).getDate());
        return view;
    }
}
