package com.project.server.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.core.serializer.Deserializer;

import java.io.Serializable;

@Entity
@Table(name = "context", schema = "conlog")
public class ContextEntity implements Serializable
{
        public ContextEntity() {}
        public ContextEntity(String uuid,String context)
        {
                this.uuid=uuid;
                this.context=context;
        }
        @Id
        private String uuid;
        private String context;

        public String getUuid() {
                return uuid;
        }

        public void setUuid(String uuid) {
                this.uuid = uuid;
        }

        public String getContext() {
                return context;
        }

        public void setContext(String context) {
                this.context = context;
        }

        @Override
        public String toString() {
                return "ContextEntity{" +
                        "uuid='" + uuid + '\'' +
                        ", context='" + context + '\'' +
                        '}';
        }
}