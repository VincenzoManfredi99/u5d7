package vincenzomanfredi.u5d7.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzomanfredi.u5d7.entities.BlogPost;
import vincenzomanfredi.u5d7.payloads.BlogPostPayload;
import vincenzomanfredi.u5d7.services.BlogPostService;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public Page<BlogPost> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.blogPostService.findAll(page, size, orderBy);
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

