package com.project.server.app.Entity;

import java.io.Serializable;
public class LogEntity implements Serializable {

    public LogEntity(){}
    private String uuid;
    private String operation;
    private String status;

    public LogEntity(String uuid, String operation, String status) {
        this.uuid = uuid;
        this.operation = operation;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "uuid='" + uuid + '\'' +
                ", operation='" + operation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
