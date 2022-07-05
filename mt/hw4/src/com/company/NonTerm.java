package com.company;

import java.util.List;

public class NonTerm extends ProdElem {
    public String name;
    public List<String> callArgs;

    public NonTerm(String name, List<String> args) {
        this.name = name;
        callArgs = args;
    }

    @Override
    public String getName() {
        return name;
    }
}
