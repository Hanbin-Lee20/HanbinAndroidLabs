package Algonquin.cst2355.lee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import Algonquin.cst2355.lee.databinding.ActivityChatRoomBinding;
import Algonquin.cst2355.lee.databinding.ReceiveMessageBinding;
import Algonquin.cst2355.lee.databinding.SentMessageBinding;
import data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {

    //in the beginning, there's no text
    ArrayList<ChatMessage> messages = new ArrayList<>();

    ChatRoomViewModel chatModel;

    ActivityChatRoomBinding binding;

    ChatMessageDAO mDAO;

    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //open db connection
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        mDAO = db.cmDAO();

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        chatModel.selectedMessage.observe(this, (newSelected) -> {

            MessageDetailsFragment newFragment = new MessageDetailsFragment( newSelected );
            FragmentManager fMgr = getSupportFragmentManager();
            FragmentTransaction tx = fMgr.beginTransaction();
            tx.replace(R.id.fragmentLocation, newFragment);
            tx.addToBackStack("");
            tx.commit();


//            String message = newSelected.message;
//            String time = newSelected.timeSent;

        });

        if(messages == null)
        {
            chatModel.messages.setValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database

                runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
            });
        }


        binding.sendButton.setOnClickListener( cli -> {
            String userMessage = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage cm = new ChatMessage(userMessage, currentDateandTime, true);
            messages.add(cm);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(new Runnable() {
                @Override
                public void run() {
                    long id = mDAO.insertMessage(cm);
                    cm.id = id;
                }
            });


        });

        binding.receiveButton.setOnClickListener( cli -> {
            String userMessage = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage cm = new ChatMessage(userMessage, currentDateandTime, false);
            messages.add(cm);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(new Runnable() {
                @Override
                public void run() {
                    long id = mDAO.insertMessage(cm);
                    cm.id = id;
                }
            });

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

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);

            itemView.setOnClickListener( cl -> {

                int rowNum = getAbsoluteAdapterPosition();
                ChatMessage selected = messages.get(rowNum);
                chatModel.selectedMessage.postValue(selected);



//                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );
//
//                builder.setMessage("Do you want to delete the message: " + messageText.getText())
//                .setTitle("Question:")
//                .setNegativeButton("No", (a,b) -> {})
//                .setPositiveButton("Yes", (a,b) -> {
//
//                    Executors.newSingleThreadExecutor().execute(() -> {
//                        mDAO.deleteMessage(thisMessage);
//                    });
//                    messages.remove(whichRow);
//                    myAdapter.notifyItemRemoved(whichRow);
//
//                    Snackbar.make(messageText, "You deleted message #" + whichRow, Snackbar.LENGTH_LONG)
//                            .setAction("Undo", clk -> {
//
//                                Executors.newSingleThreadExecutor().execute( () -> {
//                                    mDAO.insertMessage(thisMessage);
//                                });
//
//
//                    messages.add(whichRow, thisMessage);
//                    myAdapter.notifyItemInserted(whichRow);
//
//                        })
//                        .show();
//
//                }).create().show();

            });


        }
    }


}