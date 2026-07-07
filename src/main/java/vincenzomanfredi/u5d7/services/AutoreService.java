package vincenzomanfredi.u5d7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vincenzomanfredi.u5d7.entities.Autore;
import vincenzomanfredi.u5d7.exceptions.NotFoundException;
import vincenzomanfredi.u5d7.payloads.AutorePayload;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AutoreService {

    private final List<Autore> autoriDB = new ArrayList<>();

    public List<Autore> findAll() {
        return this.autoriDB;
    }

    public Autore saveAutore(AutorePayload body) {
        Autore newAutore = new Autore(body.getName(), body.getSurname(), body.getEmail(), body.getData());
        this.autoriDB.add(newAutore);
        log.info("L'utente " + newAutore.getId() + " è stato creato");
        return newAutore;
    }

    public Autore findById(long autoreId) {
        Autore found = null;

        for (Autore autore : this.autoriDB) {
            if (autore.getId() == autoreId) found = autore;
        }
        if (found == null) throw new NotFoundException(autoreId);
        return found;
    }

    public Autore findByIdAndUpdate(long autoreId, AutorePayload body) {
        Autore found = null;

        for (Autore autore : this.autoriDB) {
            if (autore.getId() == autoreId) {
                found = autore;
                found.setName(body.getName());
                found.setSurname(body.getSurname());
                found.setEmail(body.getEmail());
                found.setData(body.getData());

                found.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
            }
        }

        if (found == null) throw new NotFoundException(autoreId);
        return found;
    }

    public void findByIdAndDelete(long autoreId) {
        Autore found = null;

        for (Autore autore : this.autoriDB) {
            if (autore.getId() == autoreId) found = autore;
        }
        if (found == null) throw new NotFoundException(autoreId);
        this.autoriDB.remove(found);
    }
}
