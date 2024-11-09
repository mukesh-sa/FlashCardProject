package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private FlashcardAdapter adapter;
    private List<Flashcard> flashcardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list
        flashcardList = new ArrayList<>();
        adapter = new FlashcardAdapter(this, flashcardList);
        recyclerView.setAdapter(adapter);

        // Fetch flashcards from Firestore
        fetchFlashcardsFromFirestore();

        // Floating action button to add a new flashcard
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, FlashcardFormActivity.class);
            startActivity(intent);
        });
    }

    // Method to fetch flashcards from Firestore
    private void fetchFlashcardsFromFirestore() {
        db.collection("flashcards")
                .get()  // Get all flashcards
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Get all documents
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot document : documents) {
                            // Create Flashcard object from Firestore document data
                            Flashcard flashcard = document.toObject(Flashcard.class);
                            flashcardList.add(flashcard);
                        }
                        // Notify adapter to update the RecyclerView
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle errors if fetching data from Firestore fails
                    e.printStackTrace();
                });
    }
}
