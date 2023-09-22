package data;

import android.widget.CompoundButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<>();

    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();

    public MutableLiveData<CompoundButton.OnCheckedChangeListener> checkedChangeListener = new MutableLiveData<>();



    }


