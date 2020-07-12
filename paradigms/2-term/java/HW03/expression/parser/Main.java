package expression.parser;


import expression.generic.GenericTabulator;

public class Main {
    public static void main(String args[]) {
        String s = new String("high (1, 2)");
        GenericTabulator a=new GenericTabulator();
        try {
            Object[][][] ans=a.tabulate("d","x",-4,12,-9,12,-1,11);
            System.out.println(ans[0][0][0].toString());
           // System.out.println(parser.parse(s).evaluate(1, 1, 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
