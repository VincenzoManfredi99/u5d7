package vincenzomanfredi.u5d7.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
public class Autore {

    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate data;
    private String avatar;

    public Autore(String name, String surname, String email, LocalDate data) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.data = data;
        this.avatar = "https://picsum.photos/200/300";
        Random random = new Random();
        this.id = random.nextInt(1, 10000);
    }
}
