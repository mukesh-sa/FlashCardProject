package com.example.flashcard;

public class Flashcard {

    // Fields to store the question and answer
    private String question;
    private String answer;

    // No-argument constructor (required for Firestore deserialization)
    public Flashcard() {
        // Firestore needs this constructor to create an instance of the object
    }

    // Constructor with parameters to initialize the flashcard
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getter and setter for question
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    // Getter and setter for answer
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Override toString method for easy display of flashcard information
    @Override
    public String toString() {
        return "Flashcard{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
