package vincenzomanfredi.u5d7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "autori")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    }
}
