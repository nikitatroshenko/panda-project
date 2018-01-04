package by.bsu.group1.panda.serialization;

import by.bsu.group1.panda.model.Project;
import by.bsu.group1.panda.model.Role;
import by.bsu.group1.panda.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

@Deprecated
public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    protected UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        User user = new User();
//        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
//
//        if (jsonNode.get("id") != null) {
//            user.setId(jsonNode.get("id").asLong());
//        }
//        if (jsonNode.get("role") != null) {
//            user.setRole(Role.forName(jsonNode.get("role").asText()));
//        }
//        if (jsonNode.get("username") != null) {
//            user.setUsername(jsonNode.get("username").asText());
//        }
//
//        if (jsonNode.get("project") != null && !jsonNode.get("project").isNull()) {
//            Project project = new Project();
//
//            project.setId(jsonNode.get("project").get("id").asLong());
//            user.setProject(project);
//        }
        return user;
    }
}
