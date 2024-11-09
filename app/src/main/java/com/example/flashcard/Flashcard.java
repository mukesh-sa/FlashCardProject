package com.example.flashcard;

import com.google.firebase.FirebaseApp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flashcards")
public class Flashcard {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question;
    private String answer;

    // Constructor
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
