package es.raulprieto.inventory.ui.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public static final String TAG = "basedialogfragment";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    // Callback method
    // Accepting response, dismissing won't return anything
    public interface onFinishDialogListener {
        void onFinishDialog();
    }

    public static BaseDialogFragment newInstance(Bundle bundle) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        if (bundle != null)
            baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString(TITLE);
        String message = getArguments().getString(MESSAGE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onFinishDialogListener listener = (onFinishDialogListener) getTargetFragment();
                        listener.onFinishDialog();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}
