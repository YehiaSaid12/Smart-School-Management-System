package netbeans_school;

import java.util.ArrayList;

public class SchoolHierarchy {
// class made by yehia, changes not listed
    private static class Node {
        private final String name;
        private final ArrayList<Node> children = new ArrayList<Node>();

        private Node(String name) {
            this.name = name;
        }
    }

    private final Node root;

    public SchoolHierarchy(String rootName) {
        if (rootName == null || rootName.trim().isEmpty()) {
            throw new IllegalArgumentException("Root is required.");
        }

        root = new Node(rootName.trim());
    }

    public void addBranch(String parentName, String childName) {
        if (parentName == null || childName == null
                || parentName.trim().isEmpty()
                || childName.trim().isEmpty()) {
            return;
        }

        Node parent = findNode(root, parentName.trim());

        if (parent == null) {
            return;
        }

        for (Node child : parent.children) {
            if (child.name.equalsIgnoreCase(childName.trim())) {
                return;
            }
        }

        parent.children.add(new Node(childName.trim()));
    }

    private Node findNode(Node current, String target) {
        if (current.name.equalsIgnoreCase(target)) {
            return current;
        }

        for (Node child : current.children) {
            Node found = findNode(child, target);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public String displayHierarchy() {
        StringBuilder output = new StringBuilder();
        appendRecursive(root, 0, output);
        return output.toString();
    }

    private void appendRecursive(Node node, int depth, StringBuilder output) {
        for (int i = 0; i < depth; i++) {
            output.append("  ");
        }

        if (depth > 0) {
            output.append("- ");
        }

        output.append(node.name).append('\n');

        for (Node child : node.children) {
            appendRecursive(child, depth + 1, output);
        }
    }

    public boolean contains(String target) {
        if (target == null) {
            return false;
        }
        return findNode(root, target) != null;
    }

    public int countNodes() {
        return countRecursive(root);
    }

    private int countRecursive(Node node) {
        int total = 1;

        for (Node child : node.children) {
            total += countRecursive(child);
        }

        return total;
    }
}
