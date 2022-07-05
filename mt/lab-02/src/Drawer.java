import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Drawer {
    private final Node node;
    StringBuilder tree;

    Drawer(Node node) {
        this.node = node;
        tree = new StringBuilder();
    }

    void draw() {
        tree.append("digraph G{").append(System.lineSeparator());
        dfs(node);
        tree.append("}");
        System.out.println(tree);
        File file = new File("/Users/admin/Documents/University/#GitHub/mt/lab-02/src/tree.dot");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(tree.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dfs(Node node) {
        if (node.getChildren() == null) {
            return;
        }
        for (Node child : node.getChildren()) {
            tree.append("\"")
                    .append(node.getNodeName())
                    .append("\"")
                    .append("->")
                    .append("\"")
                    .append(child.getNodeName())
                    .append("\"")
                    .append(System.lineSeparator());
            dfs(child);
        }
    }
}
