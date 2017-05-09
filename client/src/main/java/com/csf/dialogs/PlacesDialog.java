package com.csf.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.csf.activities.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import dto.PlaceDto;

public class PlacesDialog extends DialogFragment {

    public static PlacesDialog newInstance(List<PlaceDto> places){
        PlacesDialog dialog = new PlacesDialog();

        Bundle args = new Bundle();
        args.putParcelable("places", Parcels.wrap(places));
        dialog.setArguments(args);

        return dialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<PlaceDto> places = Parcels.unwrap(bundle.getParcelable("places"));

        if (places != null) {
            CharSequence[] items = new CharSequence[places.size()];
            for (int i = 0; i < items.length; i++) {
                items[i] = buildField(places.get(i));
            }

            builder.setTitle(R.string.titlePlacesDialog)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }

        return builder.create();
    }

    private String buildField(PlaceDto dto){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(dto.getName() + System.getProperty("line.separator"));
        sb.append("Address: ");
        sb.append(dto.getAddress() + System.getProperty("line.separator"));
        sb.append("Type: ");
        sb.append(dto.getType() + System.getProperty("line.separator"));

        return sb.toString();
    }
}













