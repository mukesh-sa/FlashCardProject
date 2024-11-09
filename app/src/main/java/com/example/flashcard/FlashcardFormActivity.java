package com.example.flashcard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class FlashcardFormActivity extends AppCompatActivity {

    private EditText questionField, answerField;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_form);

        questionField = findViewById(R.id.question_field);
        answerField = findViewById(R.id.answer_field);
        saveButton = findViewById(R.id.save_button);

        // Save flashcard
        saveButton.setOnClickListener(view -> saveFlashcard());
    }

    private void saveFlashcard() {
        String question = questionField.getText().toString();
        String answer = answerField.getText().toString();

        Flashcard flashcard = new Flashcard(question, answer);
        FlashcardDatabase.getInstance(this).flashcardDao().insert(flashcard);

        finish(); // Close form activity
    }
}
