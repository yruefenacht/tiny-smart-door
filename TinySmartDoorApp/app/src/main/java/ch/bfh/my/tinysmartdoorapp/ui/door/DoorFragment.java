package ch.bfh.my.tinysmartdoorapp.ui.door;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ch.bfh.my.tinysmartdoorapp.MainActivity;
import ch.bfh.my.tinysmartdoorapp.R;
import ch.bfh.my.tinysmartdoorapp.service.DoorService;
import ch.bfh.my.tinysmartdoorapp.model.Door;
import ch.bfh.my.tinysmartdoorapp.model.DoorStatusUpdateCallBack;

public class DoorFragment extends Fragment {

    private DoorViewModel doorViewModel;
    private DoorService doorService;
    private Switch doorSwitch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        doorViewModel = ViewModelProviders.of(this).get(DoorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_door, container, false);
        final TextView textView = root.findViewById(R.id.text_door);
        doorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        this.doorService = ((MainActivity)getActivity()).getDoorService();

        doorSwitch = root.findViewById(R.id.door_switch);
        doorSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorService.toggleDoor();
            }
        });

        updateDoorSwitch(doorService.getDoor().getStatus());
        doorService.getDoor().setStatusUpdatedCallback(new DoorStatusUpdateCallBack() {
            @Override
            public void updateDoorStatus(boolean status) {
                updateDoorSwitch(status);
            }
        });

        return root;
    }

    private void updateDoorSwitch(boolean checked) {
        doorSwitch.setChecked(checked);
        doorSwitch.setText(Door.getStatusText(checked));
    }
}
