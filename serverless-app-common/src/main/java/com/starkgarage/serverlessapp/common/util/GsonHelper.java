package com.starkgarage.serverlessapp.common.util;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Base64;

public class GsonHelper {
    private static final Gson CUSTOM_GSON = Converters.registerDateTime((new GsonBuilder()).registerTypeHierarchyAdapter(byte[].class, new GsonHelper.ByteArrayToBase64TypeAdapter())).disableHtmlEscaping().create();

    public GsonHelper() {
    }

    public static Gson gson() {
        return CUSTOM_GSON;
    }

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        private ByteArrayToBase64TypeAdapter() {
        }

        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.getDecoder().decode(json.getAsString());
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
        }
    }
}
