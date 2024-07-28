package com.project.server.app.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.server.app.Entity.ContextEntity;
import com.project.server.app.Entity.LogEntity;
import com.project.server.app.Service.ServerService;
import com.project.server.app.kafka.KafkaProducer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/server/v1")
public class ServerController {

    private final ServerService serverService;
   // private final KafkaProducer kafkaProducer;

    @Autowired
    public ServerController(ServerService serverService,KafkaProducer kafkaProducer)
    {
        this.serverService = serverService;
     //   this.kafkaProducer=kafkaProducer;
    }

    @GetMapping(path = "/getContextEntityById/{ContextId}")
    @ResponseBody
    public ResponseEntity getContextEntityById(@PathVariable("ContextId") String ContextId) throws JsonProcessingException {

        try{
            String context = serverService.getContextEntityById(ContextId).toString();
            LogEntity logEntity=new LogEntity(ContextId,"GETBYID",HttpStatus.FOUND.toString());
            //kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.FOUND).body(context);
        }
        catch (EntityNotFoundException err)
        {
            LogEntity logEntity=new LogEntity(ContextId,"GETBYID",HttpStatus.NOT_FOUND.toString());
            //kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }


    }
    @PostMapping(path = "/addContextEntity/", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addContextEntity(@RequestBody ContextEntity contextEntity)
    {
        String id=contextEntity.getUuid();
        if(!serverService.checkExists(id))
        {
            serverService.addContextEntity(contextEntity);
            LogEntity logEntity=new LogEntity(id,"ADD",HttpStatus.CREATED.toString());
          //  kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.CREATED).body("Context Entity with id:"+id+" created");
        }
        else
        {
            LogEntity logEntity=new LogEntity(id,"ADD",HttpStatus.BAD_REQUEST.toString());
           // kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Context Entity with id:"+id+" Exists!");
        }


    }

    @DeleteMapping(path = "/deleteContextEntityById/{ContextId}")
    @ResponseBody
    public ResponseEntity deleteContextEntityById(@PathVariable("ContextId") String ContextId)
    {
        try
        {
            serverService.deleteContextEntityById(ContextId);
            LogEntity logEntity=new LogEntity(ContextId,"DELETE",HttpStatus.OK.toString());
          //  kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.OK).body("DELETE");
        }
        catch (EmptyResultDataAccessException err)
        {
            LogEntity logEntity=new LogEntity(ContextId,"DELETE",HttpStatus.BAD_REQUEST.toString());
         //   kafkaProducer.send(logEAsString(logEntity));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Context Entity with id:"+ContextId+" does not Exists!");
        }

    }

    private String logEAsString(LogEntity logEntity)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(logEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
