package by.bsu.group1.panda.serialization;

import by.bsu.group1.panda.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@Deprecated
public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeNumberField("id", user.getId());
//        jsonGenerator.writeStringField("username", user.getUsername());
//        jsonGenerator.writeStringField("role", user.getRole().getName());
//        if (user.getProject() != null) {
//            jsonGenerator.writeFieldName("project");
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", user.getProject().getId());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndObject();
    }
}
