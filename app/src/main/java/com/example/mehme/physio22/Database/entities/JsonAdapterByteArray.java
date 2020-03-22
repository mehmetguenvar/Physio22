package com.example.mehme.physio22.Database.entities;

import android.util.Base64;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class JsonAdapterByteArray extends TypeAdapter<byte[]> {
    @Override
    public void write(JsonWriter out, byte[] value) throws IOException {
        //out.name("bild");
        String bv = android.util.Base64.encodeToString(value, Base64.DEFAULT);
        out.value(bv);
        //out.endObject();
    }

    @Override
    public byte[] read(JsonReader in) throws IOException {
        String n = in.nextString();
        return android.util.Base64.decode(n,Base64.DEFAULT);
    }
}
