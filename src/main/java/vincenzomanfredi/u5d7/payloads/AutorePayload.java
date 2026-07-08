package vincenzomanfredi.u5d7.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import vincenzomanfredi.u5d7.entities.Autore;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class AutorePayload {
    private String name;
    private String surname;
    private String email;
    private LocalDate data;
    private Autore autoreId;
}
