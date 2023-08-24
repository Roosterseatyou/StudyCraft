package xyz.roosterseatyou.studycraft.topic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class Topic {
    private String name;
    private String description;

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Topic getTopicFromJSON(String json) {
        Gson gson = new Gson();
        StringReader reader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(reader);
        return gson.fromJson(jsonReader, Topic.class);
    }

    public String getJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
