package com.fuac.dragonforf.forf1.grades;

public class Student {
    private float firstTerm, secondTerm, thirdTerm, finalNote;
    private String name;

    Student(String name){
        this.name=name;
        firstTerm=0;
        secondTerm=0;
        thirdTerm=0;
        finalNote=0;
    }

    public float getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(float firstTerm) {
        this.firstTerm = firstTerm;
    }

    public float getSecondTerm() {
        return secondTerm;
    }

    public void setSecondTerm(float secondTerm) {
        this.secondTerm = secondTerm;
    }

    public float getThirdTerm() {
        return thirdTerm;
    }

    public void setThirdTerm(float thirdTerm) {
        this.thirdTerm = thirdTerm;
    }

    public float getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(float finalNote) {
        this.finalNote = finalNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
