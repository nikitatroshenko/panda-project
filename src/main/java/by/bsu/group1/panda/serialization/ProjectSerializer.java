package by.bsu.group1.panda.serialization;

import by.bsu.group1.panda.model.Project;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@Deprecated
public class ProjectSerializer extends StdSerializer<Project> {

    public ProjectSerializer() {
        this(null);
    }

    public ProjectSerializer(Class<Project> t) {
        super(t);
    }

    @Override
    public void serialize(Project project, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", project.getId());
        jsonGenerator.writeStringField("projectKey", project.getProjectKey());
        jsonGenerator.writeFieldName("manager");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", project.getManager().getId());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField("description", project.getDescription());
        jsonGenerator.writeEndObject();
    }
}
