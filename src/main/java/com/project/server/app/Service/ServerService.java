package com.project.server.app.Service;

import com.project.server.app.Entity.ContextEntity;
import com.project.server.app.ServerRepo.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ServerService {


    private final ServerRepository serverRepository;

    @Autowired
    public ServerService( ServerRepository serverRepository)
    {
        this.serverRepository=serverRepository;
    }



    public ContextEntity getContextEntityById(String id)
    {

        return serverRepository.getReferenceById(id);
    }
    public String addContextEntity(ContextEntity contextEntity)
    {

        return serverRepository.saveAndFlush(contextEntity).getUuid();
    }
    public void deleteContextEntityById(String id)
    {
        serverRepository.deleteAllById(Collections.singleton(id));
    }
    public boolean checkExists(String id)
    {
        return serverRepository.existsById(id);
    }
}
