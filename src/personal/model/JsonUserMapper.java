package personal.model;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonUserMapper {

    public String map(User user) {

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(writer, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
         return writer.toString();
    }

    public User map(String line) {
        StringReader reader = new StringReader(line);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
