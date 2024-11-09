package com.example.flashcard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class FlashcardFormActivity extends AppCompatActivity {

    private EditText questionEditText;
    private EditText answerEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_form);

        questionEditText = findViewById(R.id.edit_text_question);
        answerEditText = findViewById(R.id.edit_text_answer);
        Button saveButton = findViewById(R.id.button_save);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Save flashcard to Firestore when save button is clicked
        saveButton.setOnClickListener(v -> saveFlashcardToFirestore());
    }

    private void saveFlashcardToFirestore() {
        String question = questionEditText.getText().toString().trim();
        String answer = answerEditText.getText().toString().trim();

        if (question.isEmpty() || answer.isEmpty()) {
            Toast.makeText(this, "Please enter both question and answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a flashcard map to store question and answer in Firestore
        Map<String, String> flashcard = new HashMap<>();
        flashcard.put("question", question);
        flashcard.put("answer", answer);

        // Add flashcard to the Firestore "flashcards" collection
        db.collection("flashcards")
                .add(flashcard)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Flashcard saved!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the form activity
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error saving flashcard", Toast.LENGTH_SHORT).show()
                );
    }
}
