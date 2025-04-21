package com.openapi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsRoverCamera {
    // define fields //
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String cameraType;

    @JsonProperty("rover_id")
    private int roverId;

    @JsonProperty("full_name")
    private String cameraFullName;

    // define getters and setters //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCameraType() {
        return cameraType;
    }

    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    public int getRoverId() {
        return roverId;
    }

    public void setRoverId(int roverId) {
        this.roverId = roverId;
    }

    public String getCameraFullName() {
        return cameraFullName;
    }

    public void setCameraFullName(String cameraFullName) {
        this.cameraFullName = cameraFullName;
    }

    // define consts //

    public MarsRoverCamera(int id, String cameraType, int roverId, String cameraFullName) {
        this.id = id;
        this.cameraType = cameraType;
        this.roverId = roverId;
        this.cameraFullName = cameraFullName;
    }
}
