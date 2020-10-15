package util;

import backend.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Trie<ItemType> {
    private static class Node<ItemType> {
        private ItemType item;
        private boolean leaf;
        private Node<ItemType> parent;
        private final HashMap<Character, Node<ItemType>> children;

        Node() {
            children = new HashMap<Character, Node<ItemType> >();
            leaf = false;
        }

        Node(ItemType item) {
            this();
            this.item = item;
        }

        Node<ItemType> getChild(Character edgeChar) {
            if (children.containsKey(edgeChar)) {
                return children.get(edgeChar);
            }

            return null;
        }

        HashMap<Character, Node<ItemType>> getAllChildren() {
            return this.children;
        }

        void addChild(Character edgeChar, Node<ItemType> child) {
            children.put(edgeChar, child);
        }

        void removeChild(Character edgeChar) {
            children.remove(edgeChar);
        }

        int getNumChildren() {
            return children.size();
        }

        void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        boolean isLeaf() {
            return leaf;
        }

        void setItem(ItemType item) {
            this.item = item;
        }

        ItemType getItem() {
            return item;
        }
    }

    private final Node<ItemType> root;
    private int elementCounter;

    public Trie() {
        root = new Node<ItemType>();
        elementCounter = 0;
    }

    private void insert(String key, ItemType item, int currentIndex, Node<ItemType> currentNode) {
        if (currentIndex == key.length()) {
            currentNode.setLeaf(true);
            currentNode.setItem(item);
            ++elementCounter;
            return;
        }

        Character c = key.charAt(currentIndex);
        Node<ItemType> nextNode = currentNode.getChild(c);

        if (nextNode == null) {
            nextNode = new Node<ItemType>(item);
            currentNode.addChild(c, nextNode);
        }

        insert(key, item, currentIndex + 1, nextNode);
    }

    public void insert(String key, ItemType item) {
        key = key + "@";
        insert(key, item, 0, root);
    }

    private boolean remove(String key, int currentIndex, Node<ItemType> currentNode) {
        if (currentIndex == key.length()) {
            return true;
        }

        Character c = key.charAt(currentIndex);
        Node<ItemType> nextNode = currentNode.getChild(c);

        if (nextNode != null && remove(key, currentIndex + 1, nextNode)) {
            if (nextNode.getNumChildren() == 0) {
                currentNode.removeChild(c);
            }
        }

        return false;
    }

    public boolean remove(String key) {
        key = key + "@";
        return remove(key, 0, root);
    }

    public int size() {
        return elementCounter;
    }

    public List<ItemType> queryPrefix(String prefix, int currentIndex, Node<ItemType> currentNode) {
        if (currentNode.isLeaf()) {
            List<ItemType> ret = new ArrayList<ItemType>();
            ret.add(currentNode.getItem());
            return ret;
        }

        if (currentIndex < prefix.length()) {
            Character c = prefix.charAt(currentIndex);
            Node<ItemType> nextNode = currentNode.getChild(c);
            if (nextNode != null) {
                return queryPrefix(prefix, currentIndex + 1, nextNode);
            }
            return new ArrayList<ItemType>();
        }

        HashMap<Character, Node<ItemType>> allChildren = currentNode.getAllChildren();
        List<ItemType> ret = new ArrayList<>();
        for (Character c : allChildren.keySet()) {
            ret.addAll(queryPrefix(prefix, currentIndex, allChildren.get(c)));
        }

        return ret;
    }
    
    public boolean contains(String word, int currentIndex, Node<ItemType> currentNode) {
        if (currentNode.isLeaf()) {
            return true;
        }

        Character c = word.charAt(currentIndex);
        Node<ItemType> nextNode = currentNode.getChild(c);
        if (nextNode == null) {
            return false;
        }
        return contains(word, currentIndex + 1, nextNode);
    }
    
    public boolean contains(String word) {
        return contains(word + "@", 0, root);
    }
        

    public List<ItemType> queryPrefix(String prefix) {
        return queryPrefix(prefix, 0, root);
    }

    public static void main(String[] args) {
        Trie<Integer> tr = new Trie<Integer>();
        tr.insert("aaaaa", 8);
        tr.insert("Abaaa", 1);
        tr.insert("acaaa", 2);
        tr.insert("accaa", 3);

        System.out.println(tr.queryPrefix("ac"));
    }
}
