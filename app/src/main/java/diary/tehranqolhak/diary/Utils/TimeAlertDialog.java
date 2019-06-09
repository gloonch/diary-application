package diary.tehranqolhak.diary.Utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.orhanobut.hawk.Hawk;

import diary.tehranqolhak.diary.R;

public class TimeAlertDialog extends DialogFragment {
    private final onTimePickedInterface mListener;
    int hours;
    int mins;

    public interface onTimePickedInterface {
        void onTimePicked(int hour, int min);
    }

    public TimeAlertDialog(onTimePickedInterface listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Hawk.init(getActivity().getApplicationContext()).build();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.time_alertdialog, null);
        builder.setView(v);
        TimePicker time = v.findViewById(R.id.time);

        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hours = timePicker.getHour();
                mins = timePicker.getMinute();
                Hawk.put("hour", hours);
                Hawk.put("min", mins);
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Hawk.put("hour", hours);
                Hawk.put("min", mins);
                dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener.onTimePicked(hours, mins);
    }
}