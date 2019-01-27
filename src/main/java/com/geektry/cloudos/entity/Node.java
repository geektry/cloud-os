package com.geektry.cloudos.entity;

public class Node {

    public enum Type {
        DIR, FILE
    }

    private Enum type;

    private String path;

    private String name;

    public Node(Enum type, String path, String name) {
        this.type = type;
        this.path = path;
        this.name = name;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLeaf() {
        return type.equals(Type.FILE);
    }
}
