package com.blog.blog;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BlogController {

    private  final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }
    @GetMapping("/blogs")
    public  String getBlogs(Model model){
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs",blogs);
        return "blogs";
    }

    @GetMapping("/addblog")
    public String getBlogForm(Model model){
        model.addAttribute("newBlog",new Blog());
        return "addblog";
    }

    @PostMapping("/searchblog")
    public String findblogbytitle(@ModelAttribute Blog searchedblog,Model model){
        String title = searchedblog.getTitle();
       List<Blog> blogs =  blogRepository.findby(title).orElse(null);
    }
    @PostMapping("/addblog")
    public String saveblog(@ModelAttribute("newBlog") Blog blog){
        blogRepository.save(blog);
        return "redirect:/blogs";

    }



    @GetMapping("/deletepost/{id}")
    public  String deleteBlog(@PathVariable Integer id){
        blogRepository.deleteById(id);
        return "redirect:/blogs";
    }

    @GetMapping("/editpost/{id}")
        public String getEditForm(@PathVariable Integer id,Model model){
        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null){
            return "error";
        }
        model.addAttribute("blog",blog);
        return "editblog";
        }

        @PostMapping("/editpost/{id}")
    public  String editBlog(@PathVariable Integer id,@ModelAttribute Blog updatedblog){
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null){
            return "error";
        }
        blog.setTitle(updatedblog.getTitle());
        blog.setAuthor(updatedblog.getAuthor());
        blog.setContent(updatedblog.getContent());
        blogRepository.save(blog);
        return "redirect:/blogs";
        }
}
