package com.Application.Model;

import java.io.Serializable;

public class DictionaryEntity implements Serializable {
    public String Word;
    public String Description;
    public String Subject;

    public DictionaryEntity(String word, String description, String subject) {
        Word = word;
        Description = description;
        Subject = subject;
    }

    public DictionaryEntity() {
    }

    public String getWord() {
        return Word;
    }

    public String getDescription() {
        return Description;
    }

    public String getSubject() {
        return Subject;
    }


}
