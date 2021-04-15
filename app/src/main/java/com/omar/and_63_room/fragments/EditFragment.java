package com.omar.and_63_room.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omar.and_63_room.R;
import com.omar.and_63_room.RoomFactory;
import com.omar.and_63_room.asyncTasks.UpdateAsyncTask;
import com.omar.and_63_room.room.Note;

public class EditFragment extends Fragment {

    private EditText titleEt;
    private EditText descriptionEt;
    private Button updateBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        titleEt = view.findViewById(R.id.edit_title_et);
        descriptionEt = view.findViewById(R.id.edit_description_et);
        updateBtn = view.findViewById(R.id.update_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Note noteObjectBeforeUpdate = getNoteFromHomeFragment();

        setUpClickListeners(noteObjectBeforeUpdate);

    }

    private void setUpClickListeners(final Note updatedNote) {


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "";
                String description = "";

                if(!TextUtils.isEmpty(titleEt.getText()) && !TextUtils.isEmpty(descriptionEt.getText())){

                    title = titleEt.getText().toString();
                    description = descriptionEt.getText().toString();

                    updatedNote.setTitle(title);
                    updatedNote.setDescription(description);

                    new UpdateAsyncTask(RoomFactory.createRoomObject(requireContext()).getNoteDao()).execute(updatedNote);

                    Navigation.findNavController(v).navigate(R.id.action_editFragment_to_homeFragment);

                } else {
                    Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    private Note getNoteFromHomeFragment(){

        Note noteObjectBeforeUpdate = null;

        Bundle arguments = getArguments();
        if(arguments != null){
            noteObjectBeforeUpdate = (Note) arguments.getSerializable("note_object");

            titleEt.setText(noteObjectBeforeUpdate.getTitle());
            descriptionEt.setText(noteObjectBeforeUpdate.getDescription());
        }

        return noteObjectBeforeUpdate;
    }
}