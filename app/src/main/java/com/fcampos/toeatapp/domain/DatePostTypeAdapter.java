package com.fcampos.toeatapp.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePostTypeAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String dateValue = in.nextString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datePost = LocalDate.parse(dateValue, formatter);
        return datePost;
    }
}
