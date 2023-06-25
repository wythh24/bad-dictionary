package com.Application.Features;

import com.Application.Model.DictionaryEntity;

import java.util.Vector;

public interface FileFeature {
    default Vector<DictionaryEntity> loadDictionary(String filePath) {
        return new Vector<>();
    }

    void saveDictionary(Vector<DictionaryEntity> dictionary, String filePath);

    default DictionaryEntity search(Vector<DictionaryEntity> dictionary, String word) {
        return new DictionaryEntity();
    }

    void update(Vector<DictionaryEntity> dictionary, DictionaryEntity valueUpdate);

    void addToDictionary(Vector<DictionaryEntity> dictionary, DictionaryEntity value);
}
