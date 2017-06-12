package com.tistory.algorithm;

/**
 * <pre>
 * <b>이진 탐색 트리</b>
 *  - 이진 트리를 이용하여 탐색하는 알고리즘.
 * </pre>
 */
public class BinarySearchTree {

    private static Node root = null;

    private static boolean find(int data) {
        Node current = root;

        while(current != null) {
            if(current.getData() == data) {
                return true;
            } else if(current.getData() > data) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return false;
    }

    private static boolean delete(int data) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        while(current.getData() != data) {
            parent = current;

            if(current.getData() > data) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }

            if(current == null) {
                return false;
            }
        }
        //Case 1: 자식노드가 없는 경우
        if(current.getLeft() == null &&
            current.getRight() == null) {

            if(current == root) {
                root = null;
            }

            if(isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
        //Case 2 : 하나의 자식을 갖는 경우
        else if(current.getRight() == null) {

            if(current == root) {
                root = current.getLeft();
            } else if(isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }

        } else if(current.getLeft() == null) {

            if(current == root){
                root = current.getRight();
            } else if(isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }

        }
        //Case 3 : 두개의 자식을 갖는 경우
        else {
            Node minNode = getMinNode(current);
            if(current == root) {
                root = minNode;
            } else if( isLeftChild ) {
                parent.setLeft(minNode);
            } else {
                parent.setRight(minNode);
            }
            minNode.setLeft(current.getLeft());
        }
        return true;
    }

    private static Node getMinNode(Node node){
        Node _node = null;
        Node _nodeParent = null;
        Node current = node.getRight();

        while(current != null) {
            _nodeParent = _node;
            _node = current;
            current = current.getLeft();
        }

        if(_node != node.getRight()) {
            _nodeParent.setLeft(_node.getRight());
            _node.setRight(node.getRight());
        }
        return _node;
    }

    private static void insert(int id){
        Node newNode = new Node(id);

        if(root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while(true) {
            Node parent = current;

            if(newNode.getData() == current.getData()) {
                return;
            }

            if(newNode.getData() < current.getData()) {
                current = current.getLeft();
                if(current == null) {
                    parent.setLeft(newNode);
                    return;
                }
            } else {
                current = current.getRight();
                if(current == null) {
                    parent.setRight(newNode);
                    return;
                }
            }
        }
    }

    private static void display(Node root) {
        if(root != null) {
            display(root.getLeft());
            System.out.print(" " + root.getData());
            display(root.getRight());
        }
    }

    static class Node {
        private int data;
        private Node left;
        private Node right;

        Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        insert(8);
        insert(1);
        insert(4);
        insert(6);
        insert(2);
        insert(10);
        insert(9);
        insert(20);
        insert(25);
        insert(15);
        insert(16);

        display(root);
        System.out.println();

        System.out.println("이진트리에서 4를 탐색 : " + find(4));

        System.out.println("이진트리에서 2를 삭제 : " + delete(2));

        display(root);
        System.out.println();

        System.out.println("이진트리에서 4를 삭제 : " + delete(4));

        display(root);
        System.out.println();

        System.out.println("이진트리에서 10을 삭제 : " + delete(10));

        display(root);
    }
}