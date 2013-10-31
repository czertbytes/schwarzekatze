package de.czertbytes.schwarzekatze.api.converter;

import com.google.gson.*;
import de.czertbytes.schwarzekatze.api.converter.gson.DateTimeTypeConverter;
import de.czertbytes.schwarzekatze.api.converter.gson.EnumTypeConverter;
import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

@Component
public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Type type = null;
    private boolean prefixJson = false;

    private static final ThreadLocal<Gson> threadSafeGsonBuilder = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            final GsonBuilder gsonBuilder = new GsonBuilder();

            gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT);
            gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new EnumTypeConverter());
            gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());

            gsonBuilder.setPrettyPrinting();

            return gsonBuilder.create();
        }
    };

    static Gson gson() {
        return threadSafeGsonBuilder.get();
    }

    public GsonHttpMessageConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return canWrite(mediaType);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // should not be called, since we override canRead/Write instead
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        final Reader json = new InputStreamReader(inputMessage.getBody(), getCharset(inputMessage.getHeaders()));

        try {
            Type typeOfT = getType();
            if (typeOfT != null) {
                return gson().fromJson(json, typeOfT);
            } else {
                return gson().fromJson(json, clazz);
            }
        } catch (JsonSyntaxException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        } catch (JsonIOException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        } catch (JsonParseException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        }

    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        final OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), getCharset(outputMessage.getHeaders()));

        try {
            if (this.prefixJson) {
                writer.append("{} && ");
            }
            Type typeOfSrc = getType();
            if (typeOfSrc != null) {
                gson().toJson(o, typeOfSrc, writer);
            } else {
                gson().toJson(o, writer);
            }
            writer.close();
        } catch(JsonIOException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    private Charset getCharset(HttpHeaders headers) {
        if (headers != null && headers.getContentType() != null && headers.getContentType().getCharSet() != null) {
            return headers.getContentType().getCharSet();
        }

        return DEFAULT_CHARSET;
    }
}
