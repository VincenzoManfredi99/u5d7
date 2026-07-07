package vincenzomanfredi.u5d7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vincenzomanfredi.u5d7.entities.BlogPost;
import vincenzomanfredi.u5d7.exceptions.NotFoundException;
import vincenzomanfredi.u5d7.payloads.BlogPostPayload;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogPostService {
    private final List<BlogPost> blogPostDB = new ArrayList<>();

    public List<BlogPost> findAll() {
        return this.blogPostDB;
    }

    public BlogPost saveBlogPost(BlogPostPayload body) {
        BlogPost newBlogPost = new BlogPost(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura());
        this.blogPostDB.add(newBlogPost);
        log.info("L'utente " + newBlogPost.getId() + " è stato creato");
        return newBlogPost;
    }

    public BlogPost findById(long blogPostId) {
        BlogPost found = null;

        for (BlogPost blogPost : this.blogPostDB) {
            if (blogPost.getId() == blogPostId) found = blogPost;
        }
        if (found == null) throw new NotFoundException(blogPostId);
        return found;
    }

    public BlogPost findByIdAndUpdate(long blogPostId, BlogPostPayload body) {
        BlogPost found = null;

        for (BlogPost blogPost : this.blogPostDB) {
            if (blogPost.getId() == blogPostId) {
                found = blogPost;
                found.setCategoria(body.getCategoria());
                found.setTitolo(body.getTitolo());
                found.setContenuto(body.getContenuto());
                found.setTempoDiLettura(body.getTempoDiLettura());

                found.setCover("https://ui-avatars.com/api/?name=" + body.getTitolo() + "+" + body.getContenuto());
            }
        }

        if (found == null) throw new NotFoundException(blogPostId);
        return found;
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = null;

        for (BlogPost blogPost : this.blogPostDB) {
            if (blogPost.getId() == blogPostId) found = blogPost;
        }
        if (found == null) throw new NotFoundException(blogPostId);
        this.blogPostDB.remove(found);
    }

}
