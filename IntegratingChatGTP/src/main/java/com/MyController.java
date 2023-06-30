package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@CrossOrigin(origins = "*")
@RestController
public class MyController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestBody Prompt prompt){
    	System.out.println(prompt);
    	
        Request request=new Request(model, prompt.getMessage());
        Response response = template.postForObject(apiURL, request, Response.class);
        
        return response.getChoices().get(0).getMessage().getContent();
    }
}
