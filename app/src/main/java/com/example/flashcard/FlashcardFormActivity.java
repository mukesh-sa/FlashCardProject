package com.example.flashcard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
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

        // Initialize UI elements
        questionEditText = findViewById(R.id.edit_text_question);
        answerEditText = findViewById(R.id.edit_text_answer);
        Button saveButton = findViewById(R.id.button_save);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Set listener for save button to save flashcard
        saveButton.setOnClickListener(v -> saveFlashcardToFirestore());
    }

    // Method to save flashcard to Firestore
    private void saveFlashcardToFirestore() {
        String question = questionEditText.getText().toString().trim();
        String answer = answerEditText.getText().toString().trim();

        // Check if question and answer are not empty
        if (question.isEmpty() || answer.isEmpty()) {
            Toast.makeText(this, "Please enter both question and answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map to store flashcard data (question and answer)
        Map<String, String> flashcard = new HashMap<>();
        flashcard.put("question", question);
        flashcard.put("answer", answer);

        // Add flashcard to Firestore in the "flashcards" collection
        db.collection("flashcards")
                .add(flashcard)
                .addOnSuccessListener(documentReference -> {
                    // Show success message and finish activity
                    Toast.makeText(this, "Flashcard saved!", Toast.LENGTH_SHORT).show();
                    finish();  // Close the activity and return to the previous one
                })
                .addOnFailureListener(e -> {
                    // Show error message if saving fails
                    Toast.makeText(this, "Error saving flashcard", Toast.LENGTH_SHORT).show();
                });
    }
}
