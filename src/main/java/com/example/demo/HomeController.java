package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "listAll";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String processForm(@Valid Job job,
                              BindingResult result){
        if(result.hasErrors()){
            return "jobform";
        }
        Date date = new Date();
        job.setPostedDate(date);
        jobRepository.save(job);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String processSearch(Model model,
                                @RequestParam(name="SearchSelector") String option,
                                @RequestParam(name="search") String search){
        if(option.equals("Title")){
            model.addAttribute("jobs", jobRepository.findByTitleContainingIgnoreCase(search));
        }else if(option.equals("Description")){
            model.addAttribute("jobs", jobRepository.findByDescriptionContainingIgnoreCase(search));
        }else if(option.equals("Author")){
            model.addAttribute("jobs", jobRepository.findByAuthorContainingIgnoreCase(search));
        }
        return "listSearchResults";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "showdetail";
    }

    @RequestMapping("/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String delJob(@PathVariable("id") long id, Model model){
        jobRepository.deleteById(id);
        return "redirect:/";
    }
}
