package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("job", jobRepository.findAll());
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
                                @RequestParam(name="search") String search,
                                @RequestParam(name="searchD") String searchD,
                                @RequestParam(name="searchPAC")int searchPAC,
                                @RequestParam(name="searchP1")int searchP1,
                                @RequestParam(name="searchP2")int searchP2) {
        if (option.equals("Date")) {
            String pattern = "yyyy-MM-dd";
            try {
                String formattedDate = searchD;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date realDate = simpleDateFormat.parse(formattedDate);
                model.addAttribute("jobs", jobRepository.findByDate(realDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(option.equalsIgnoreCase("Title")){
            model.addAttribute("jobs", jobRepository.findByTitleContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("Description")){
            model.addAttribute("jobs", jobRepository.findByDescriptionContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("Author")){
            model.addAttribute("jobs", jobRepository.findByAuthorContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("Phone")){
            String phone = searchPAC + "-" + searchP1 + "-" + searchP2;
            model.addAttribute("jobs", jobRepository.findByPhone(phone));
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
