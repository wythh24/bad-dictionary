package com.Application.Features;

import com.Application.Model.DictionaryEntity;

import java.io.Serializable;
import java.util.Vector;

public interface FileFeature {
    default Vector<DictionaryEntity> loadDictionary(String filePath) {
        return new Vector<>();
    }

    void saveDictionary(Vector<DictionaryEntity> dictionary, String filePath);

    default Serializable search(Vector<DictionaryEntity> dictionary, String word) {
        return new DictionaryEntity();
    }


    void addToDictionary(Vector<DictionaryEntity> dictionary, DictionaryEntity value);
}
