package com.example.flashcard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private final Context context;
    private final List<Flashcard> flashcardList;

    public FlashcardAdapter(Context context, List<Flashcard> flashcardList) {
        this.context = context;
        this.flashcardList = flashcardList;
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flashcard_item, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        Flashcard flashcard = flashcardList.get(position);
        holder.questionTextView.setText(flashcard.getQuestion());

        // Set a click listener on the item
        holder.itemView.setOnClickListener(v -> {
            // Create an intent to navigate to the FlashcardViewActivity
            Intent intent = new Intent(context, FlashcardViewActivity.class);
            // Pass the flashcard question and answer as extras
            intent.putExtra("QUESTION", flashcard.getQuestion());
            intent.putExtra("ANSWER", flashcard.getAnswer());
            // Start the activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flashcardList.size();
    }

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;

        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.flashcard_question);
        }
    }
}
