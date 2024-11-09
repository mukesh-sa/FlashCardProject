package com.example.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlashcardViewActivity extends AppCompatActivity {

    private TextView cardFront;
    private TextView cardBack;
    private boolean isFrontVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        cardFront = findViewById(R.id.card_front);
        cardBack = findViewById(R.id.card_back);

        cardBack.setVisibility(View.GONE); // Initially hide back

        // Flip card on click
        cardFront.setOnClickListener(view -> flipCard());
        cardBack.setOnClickListener(view -> flipCard());
    }

    private void flipCard() {
        if (isFrontVisible) {
            cardFront.setVisibility(View.GONE);
            cardBack.setVisibility(View.VISIBLE);
        } else {
            cardFront.setVisibility(View.VISIBLE);
            cardBack.setVisibility(View.GONE);
        }
        isFrontVisible = !isFrontVisible;
    }
}
