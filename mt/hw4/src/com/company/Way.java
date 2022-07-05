package com.company;

import java.util.List;

public class Way {
    public List<ProdElem> prods;
    public List<CodeOrProd> prodsOrCode;

    public Way(List<ProdElem> p, List<CodeOrProd> np) {
        prods = p;
        prodsOrCode = np;
    }

}
