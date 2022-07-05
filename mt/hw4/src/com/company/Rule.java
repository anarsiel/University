package com.company;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    public String name;
    public String returnType;

    public List<Way> ways = new ArrayList<>();
    public List<Arg> args = new ArrayList<>();

    public Rule(String name) {
        this.name = name;
    }
}
