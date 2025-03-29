package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Url {
    private int id;
    private String name;
    private Timestamp createdAt;

    public Url(String name) {
        this.name = name;
    }
}
