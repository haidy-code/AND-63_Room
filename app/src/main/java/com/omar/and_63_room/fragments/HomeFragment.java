package com.omar.and_63_room.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.omar.and_63_room.RoomFactory;
import com.omar.and_63_room.asyncTasks.DeleteAsyncTask;
import com.omar.and_63_room.asyncTasks.GetNotesAsyncTask;
import com.omar.and_63_room.room.Note;
import com.omar.and_63_room.NotesRvAdapter;
import com.omar.and_63_room.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class HomeFragment extends Fragment {


    private RecyclerView notesRv;
    private NotesRvAdapter notesRvAdapter;
    private List<Note> noteList = new ArrayList<>();

    FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        notesRv = view.findViewById(R.id.notes_rv);
        fab = view.findViewById(R.id.add_note_fab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getAllNotesFromDB();
        setUpRecyclerView();
        setUpClickListeners();
    }

    private void getAllNotesFromDB() {

        noteList.clear();
        try {
            noteList.addAll(new GetNotesAsyncTask(RoomFactory.createRoomObject(requireContext()).getNoteDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setUpClickListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_addFragment);
            }
        });
    }

    private void setUpRecyclerView() {


        notesRvAdapter = new NotesRvAdapter(noteList, new NotesRvAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {

                setUpEditOrDeleteDialog(view , position);

            }
        });


        notesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        notesRv.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));

        notesRv.setAdapter(notesRvAdapter);

    }

    private void setUpEditOrDeleteDialog(final View view, final int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Do you want to edit or delete this note?");

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteNote(position);
                getAllNotesFromDB();
            }
        });

        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Note note = noteList.get(position);
                // to send data to EditFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("note_object", note);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment, bundle);
            }
        });

        dialog.show();

    }

    private void deleteNote(int position) {

        Note note = noteList.get(position);
        new DeleteAsyncTask(RoomFactory.createRoomObject(requireContext()).getNoteDao()).execute(note);
        notesRvAdapter.notifyDataSetChanged();

    }
}