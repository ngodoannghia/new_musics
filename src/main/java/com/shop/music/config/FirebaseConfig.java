package com.shop.music.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {
    static FirebaseApp createFireBaseApp(String pathToJson,String databaseUrl) throws IOException {
        System.out.println("firebase config");
        FileInputStream serviceAccount =
                new FileInputStream(pathToJson);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();

        return  FirebaseApp.initializeApp(options);
    }
}
