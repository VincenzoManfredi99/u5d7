package vincenzomanfredi.u5d7.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzomanfredi.u5d7.entities.BlogPost;
import vincenzomanfredi.u5d7.payloads.BlogPostPayload;
import vincenzomanfredi.u5d7.services.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> findAll() {
        return this.blogPostService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody BlogPostPayload body) {
        return this.blogPostService.saveBlogPost(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable long blogPostId) {
        return this.blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost findByIdAndUpdate(@PathVariable long blogPostId, @RequestBody BlogPostPayload body) {
        return this.blogPostService.findByIdAndUpdate(blogPostId, body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long blogPostId) {
        this.blogPostService.findByIdAndDelete(blogPostId);
    }
}
