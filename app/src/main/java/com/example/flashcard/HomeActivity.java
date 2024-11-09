package com.example.flashcard;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlashcardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter with flashcard data
        adapter = new FlashcardAdapter(this, FlashcardDatabase.getInstance(this).flashcardDao().getAllFlashcards());
        recyclerView.setAdapter(adapter);

        // Floating action button to add a new flashcard
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, FlashcardFormActivity.class);
            startActivity(intent);
        });
    }
}
