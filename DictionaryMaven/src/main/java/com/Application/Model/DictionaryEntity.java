package com.Application.Model;

import java.io.Serial;
import java.io.Serializable;

public class DictionaryEntity implements Serializable {
    public String Word;
    public String Definition;
    public String Subject;

    public DictionaryEntity(String word, String definition, String subject) {
        Word = word;
        Definition = definition;
        Subject = subject;
    }

    public DictionaryEntity() {
    }

    public String getWord() {
        return Word;
    }

    public String getDefinition() {
        return Definition;
    }

    public String getSubject() {
        return Subject;
    }


}
