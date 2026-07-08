package vincenzomanfredi.u5d7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vincenzomanfredi.u5d7.entities.Autore;
import vincenzomanfredi.u5d7.entities.BlogPost;
import vincenzomanfredi.u5d7.exceptions.NotFoundException;
import vincenzomanfredi.u5d7.payloads.BlogPostPayload;
import vincenzomanfredi.u5d7.repositories.BlogPostRepository;

@Service
@Slf4j
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final AutoreService autoreService;

    public BlogPostService(BlogPostRepository blogPostRepository, AutoreService autoreService) {
        this.blogPostRepository = blogPostRepository;
        this.autoreService = autoreService;
    }

    public Page<BlogPost> findAll(int page, int size, String orderBy) {
        if (size > 50) size = 50;
        if (size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.blogPostRepository.findAll(pageable);
    }


    public BlogPost saveBlogPost(BlogPostPayload body) {
        Autore autore = this.autoreService.findById(body.getAutoreId());
        BlogPost newBlogPost = new BlogPost(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura(), autore);
        return this.blogPostRepository.save(newBlogPost);
    }

    public BlogPost findById(long blogPostId) {
        return this.blogPostRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, BlogPostPayload body) {
        BlogPost found = this.findById(blogPostId);

        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());

        Autore autore = this.autoreService.findById(body.getAutoreId());
        found.setAutore(autore);

        return this.blogPostRepository.save(found);
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostRepository.delete(found);
    }
}
