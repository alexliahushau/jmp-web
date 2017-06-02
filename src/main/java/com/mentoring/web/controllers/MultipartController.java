package com.mentoring.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import com.mentoring.domain.Event;
import com.mentoring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mentoring.domain.User;
import com.mentoring.security.AuthoritiesConstants;
import com.mentoring.service.EventService;
import com.mentoring.web.enums.UiViews;

@Controller
@RequestMapping("/upload")
public class MultipartController {

    private static final Logger LOG = LoggerFactory.getLogger(MultipartController.class);

    @Inject
    UserService userService;

    @Inject
    EventService eventService;


    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView uploadFile(final Principal principal) {
        LOG.info("Enter myltipart controller main page");

        final ModelAndView model = new ModelAndView(UiViews.UPLOAD.name(), "principal", principal);

        return  model;
    }


    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping(value="/users", method = RequestMethod.POST)
    public String usersFileUpload(@RequestParam("file") final MultipartFile file,
                                   final RedirectAttributes redirectAttributes) throws Exception {

        final String fileName = file.getOriginalFilename();
        InputStream input = null;

        if (!file.isEmpty()) {
            try {
                input = file.getInputStream(); 
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + "!");
            } catch (IOException |RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + fileName + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + fileName + " because it was empty");
        }

        if (input != null) {
            final ObjectMapper jsonMapper = new ObjectMapper();
            List<User> users = jsonMapper.readValue(input, new TypeReference<List<User>>() {});
            
            users.stream().forEach(u -> userService.save(u));
            
        }
        
        return "redirect:/" + UiViews.UPLOAD.name().toLowerCase();
    }


    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping(value="/events", method = RequestMethod.POST)
    public String eventsFileUpload(@RequestParam("file") final MultipartFile file,
                                   final RedirectAttributes redirectAttributes) throws IOException {

        final String fileName = file.getOriginalFilename();
        InputStream input = null;

        if (!file.isEmpty()) {
            try {
                input = file.getInputStream(); 
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + "!");
            } catch (IOException |RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + fileName + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + fileName + " because it was empty");
        }

        if (input != null) {
            final ObjectMapper jsonMapper = new ObjectMapper();
            final List<Event> events = jsonMapper.readValue(input, new TypeReference<List<Event>>() {});
            
            events.stream().forEach(e -> eventService.save(e));
            
        }

        return "redirect:/" + UiViews.UPLOAD.name().toLowerCase();
    }

}
