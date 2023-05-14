package com.fcampos.toeatapp.domain;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class RoleTypeAdapter extends TypeAdapter<Role> {
    @Override
    public void write(JsonWriter out, Role value) throws IOException {

    }

    @Override
    public Role read(JsonReader in) throws IOException {
        String roleValue = in.nextString();

        // Mapea el valor del servidor a los valores de la clase Role en la aplicación cliente
        if (roleValue.equals("USER")) {
            return Role.USER;
        } else if (roleValue.equals("ADMIN")) {
            return Role.ADMIN;
        } else if (roleValue.equals("ESTABLISHMENT")) {
            return Role.ESTABLISHMENT;
        } else {
            // Maneja cualquier otro valor inesperado si es necesario
            throw new IOException("Valor de Role desconocido: " + roleValue);
        }
    }
}
