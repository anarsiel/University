package com.company;

public class Term extends ProdElem {
    public String name;

    public Term(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
