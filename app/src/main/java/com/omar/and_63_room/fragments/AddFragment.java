package com.omar.and_63_room.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omar.and_63_room.R;
import com.omar.and_63_room.RoomFactory;
import com.omar.and_63_room.asyncTasks.InsertAsyncTask;
import com.omar.and_63_room.room.Note;

public class AddFragment extends Fragment {

    EditText titleEt;
    EditText descriptionEt;
    Button saveBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        titleEt = view.findViewById(R.id.title_et);
        descriptionEt = view.findViewById(R.id.description_et);
        saveBtn = view.findViewById(R.id.save_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpClickListeners();

    }

    private void setUpClickListeners() {

         saveBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String title = titleEt.getText().toString();
                 String description = descriptionEt.getText().toString();

                 if(title.isEmpty() || description.isEmpty() ){
                     Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();
                 } else {

                     insertToRoom(title , description);

                     Navigation.findNavController(v).navigate(R.id.action_addFragment_to_homeFragment);
                 }

             }
         });


    }

    private void insertToRoom(String title , String description) {

        new InsertAsyncTask(RoomFactory.createRoomObject(requireContext()).getNoteDao()).execute(new Note(title,description));



    }
}