package ework.utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class DataManagementPool {
    public static <T> ArrayList<T> getGsonInstance(String dir, String array, Class<T> list) throws IOException {
        Gson gson = new Gson();

        FileReader reader = new FileReader(dir);

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        Type type = TypeToken.getParameterized(ArrayList.class, list).getType();
        ArrayList<T> data = gson.fromJson(jsonObject.get(array), type);

        return data;
    }
}
