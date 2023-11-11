package Algonquin.cst2355.lee;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Algonquin.cst2355.lee.databinding.DetailsLayoutBinding;




public class MessageDetailsFragment extends Fragment {
    ChatMessage selected;
    public MessageDetailsFragment(ChatMessage m) {
        selected = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Fragment layout:
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(getLayoutInflater());
        binding.messageView.setText(selected.message);
        binding.timeView.setText(selected.timeSent);
        binding.sendReceiveView.setText(selected.isSentButton?"True":"False");
        binding.idView.setText("ID = " + selected.id);
        return binding.getRoot();
    }



}













