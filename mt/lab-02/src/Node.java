import java.util.Arrays;
import java.util.List;

class Node {
    private final String nodeName;
    private List<Node> children;

    Node(String nodeName, Node... children) {
        this.nodeName = nodeName;
        this.children = Arrays.asList(children);
    }

    Node(String nodeName) {
        this.nodeName = nodeName;
    }

    List<Node> getChildren() {
        return children;
    }

    String getNodeName() {
        return nodeName;
    }
}