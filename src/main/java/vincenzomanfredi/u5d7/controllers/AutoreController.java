package vincenzomanfredi.u5d7.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzomanfredi.u5d7.entities.Autore;
import vincenzomanfredi.u5d7.payloads.AutorePayload;
import vincenzomanfredi.u5d7.services.AutoreService;

import java.util.List;

@RestController
@RequestMapping("/autore")
public class AutoreController {
    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    @GetMapping
    public List<Autore> findAll() {
        return this.autoreService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAutore(@RequestBody AutorePayload body) {
        return this.autoreService.saveAutore(body);
    }

    @GetMapping("/{autoreId}")
    public Autore findById(@PathVariable long autoreId) {
        return this.autoreService.findById(autoreId);
    }

    @PutMapping("/{autoreId}")
    public Autore findByIdAndUpdate(@PathVariable long autoreId, @RequestBody AutorePayload body) {
        return this.autoreService.findByIdAndUpdate(autoreId, body);
    }

    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long autoreId) {
        this.autoreService.findByIdAndDelete(autoreId);
    }
}
