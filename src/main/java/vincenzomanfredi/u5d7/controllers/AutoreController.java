package vincenzomanfredi.u5d7.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzomanfredi.u5d7.entities.Autore;
import vincenzomanfredi.u5d7.payloads.AutorePayload;
import vincenzomanfredi.u5d7.services.AutoreService;

@RestController
@RequestMapping("/autore")
public class AutoreController {
    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    @GetMapping
    public Page<Autore> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.autoreService.findAll(page, size, orderBy);
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
