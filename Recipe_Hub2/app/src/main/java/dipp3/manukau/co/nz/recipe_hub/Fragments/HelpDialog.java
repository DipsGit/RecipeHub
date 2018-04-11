package dipp3.manukau.co.nz.recipe_hub.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import dipp3.manukau.co.nz.recipe_hub.R;

public class HelpDialog extends AppCompatDialogFragment {

    private TextView help1, help2, help3;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.help_dialog,null);
        builder.setView(view).setTitle("Help!").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        help1 = view.findViewById(R.id.helpTxtAr);
        help2 = view.findViewById(R.id.helpTxtVr);
        help3 = view.findViewById(R.id.helpTxtRh);
        return  builder.create();
    }
}
