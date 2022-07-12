package ch.oliumbi.sqllib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private int id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "TestDTO [" + id + " | "  + name + " | " + password + "]";
    }
}
