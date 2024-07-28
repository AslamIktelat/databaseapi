package com.project.server.app.ServerRepo;

import com.project.server.app.Entity.ContextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<ContextEntity,String> {


}
