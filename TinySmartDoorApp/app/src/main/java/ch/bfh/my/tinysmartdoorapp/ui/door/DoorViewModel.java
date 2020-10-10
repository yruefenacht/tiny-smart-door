package ch.bfh.my.tinysmartdoorapp.ui.door;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DoorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the remote access");
    }

    public LiveData<String> getText() {
        return mText;
    }
}