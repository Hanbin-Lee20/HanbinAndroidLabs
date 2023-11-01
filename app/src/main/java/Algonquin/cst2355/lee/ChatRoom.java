package Algonquin.cst2355.lee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Algonquin.cst2355.lee.databinding.ActivityChatRoomBinding;
import Algonquin.cst2355.lee.databinding.ReceiveMessageBinding;
import Algonquin.cst2355.lee.databinding.SentMessageBinding;
import data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {

    //in the beginning, there's no text
    ArrayList<ChatMessage> messages = new ArrayList<>();

    ChatRoomViewModel chatModel;

    ActivityChatRoomBinding binding;

    private RecyclerView.Adapter myAdapter;
//private RecyclerView.Adapter<myRowHolder> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }

        binding.sendButton.setOnClickListener( cli -> {
            String userMessage = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(userMessage, currentDateandTime, true);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");


        });

        binding.receiveButton.setOnClickListener( cli -> {
            String userMessage = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(userMessage, currentDateandTime, false);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");

        });

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

            public int getItemViewType(int position) {
                ChatMessage chatMessage = messages.get(position);
                if(chatMessage.isSent()) {
                    return 0;
                } else {
                    return 1;
                }

            }

            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if(viewType == 0) {
                    //1. load a XML layout
                    SentMessageBinding binding =
                            SentMessageBinding.inflate(getLayoutInflater(), parent, false);

                    //2. call our constructor below
                    return new MyRowHolder(binding.getRoot());
                }
                else{
                    //1. load a XML layout
                    ReceiveMessageBinding binding =
                            ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);

                    //2. call our constructor below
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                //where you overwrite the default text
                ChatMessage chatMessage = messages.get(position);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());

                holder.messageText.setText(chatMessage.getMessage());
                holder.timeText.setText(currentDateandTime);

            }

            //returns the number of rows to draw
            @Override
            public int getItemCount() {
                return messages.size();
            }

        }

        );

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

    }

    class MyRowHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView timeText;

        public MyRowHolder(@NonNull View theRootConstraintLayout) {
            super(theRootConstraintLayout);

            messageText = theRootConstraintLayout.findViewById(R.id.message);
            timeText = theRootConstraintLayout.findViewById(R.id.time);

        }
    }


}