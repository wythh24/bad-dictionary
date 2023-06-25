package com.Application.Features;

import com.Application.Model.DictionaryEntity;

import java.io.*;
import java.util.Vector;

public class FileFeatureImp implements FileFeature {
    @Override
    public Vector<DictionaryEntity> loadDictionary(String filePath) {
        Vector<DictionaryEntity> sourceDictionary = new Vector<>();
        try {
            var path = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(path);
            sourceDictionary = (Vector<DictionaryEntity>) objectInputStream.readObject();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return sourceDictionary;
    }

    @Override
    public void saveDictionary(Vector<DictionaryEntity> dictionary, String filePath) {
        try {
            var Path = new FileOutputStream(filePath);
            var objectOutputStream = new ObjectOutputStream(Path);

            objectOutputStream.writeObject(dictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToDictionary(Vector<DictionaryEntity> dictionary, DictionaryEntity value) {
        if (value != null) dictionary.add(value);
    }
}
