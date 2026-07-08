package vincenzomanfredi.u5d7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vincenzomanfredi.u5d7.entities.Autore;
import vincenzomanfredi.u5d7.exceptions.BadRequestException;
import vincenzomanfredi.u5d7.exceptions.NotFoundException;
import vincenzomanfredi.u5d7.payloads.AutorePayload;
import vincenzomanfredi.u5d7.repositories.AutoreRepository;

@Service
@Slf4j
public class AutoreService {

    private final AutoreRepository autoreRepository;


    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }


    public Page<Autore> findAll(int page, int size, String orderBy) {
        if (size > 50) size = 50;
        if (size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.autoreRepository.findAll(pageable);
    }

    public Autore saveAutore(AutorePayload body) {
        if (this.autoreRepository.existsByEmail(body.getEmail())) {
            throw new BadRequestException("L'email " + body.getEmail() + " è già in uso!");
        }
        Autore newAutore = new Autore(body.getName(), body.getSurname(), body.getEmail(), body.getData());
        return this.autoreRepository.save(newAutore);
    }

    public Autore findById(long autoreId) {
        return this.autoreRepository.findById(autoreId)
                .orElseThrow(() -> new NotFoundException(autoreId));
    }

    public Autore findByIdAndUpdate(long autoreId, AutorePayload body) {
        Autore found = this.findById(autoreId);

        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setData(body.getData());
        found.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());

        return this.autoreRepository.save(found);
    }

    public void findByIdAndDelete(long autoreId) {
        Autore found = this.findById(autoreId);
        this.autoreRepository.delete(found);
    }

}
