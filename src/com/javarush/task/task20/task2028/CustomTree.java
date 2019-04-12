package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
//    Entry<String> root = new Entry<>("0");

    public CustomTree() {
        this.root = new Entry<>("0");
    }

    @Override
    public int size() {
        int size = -1;
        Queue<Entry<String>> queueE = new LinkedList<>();
        queueE.add(root);
        while (!queueE.isEmpty()) {
            Entry<String> entry = queueE.poll();
            size++;
            if (entry.leftChild != null) {
                queueE.offer(entry.leftChild);
            }
            if (entry.rightChild != null) {
                queueE.offer(entry.rightChild);
            }
        }
        return size;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String elementName) {
        Queue<Entry<String>> entryQueue = new LinkedList<>();
        entryQueue.add(root);
        while (!entryQueue.isEmpty()) {
            Entry<String> entry = entryQueue.poll();
            entry.checkChildren();
            if (entry.isAvailableToAddChildren()) {
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = new Entry<>(elementName);
                    entry.leftChild.parent = entry;
                    return true;
                } else if (entry.availableToAddRightChildren) {
                    entry.rightChild = new Entry<>(elementName);
                    entry.rightChild.leftChild = entry;
                    return true;
                }
            } else {
                if (entry.leftChild != null) {
                    entryQueue.offer(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    entryQueue.offer(entry.rightChild);
                }
            }
        }
        return false;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
    String getParent(String parentName) {
        Queue<Entry<String>> queueEntry = new LinkedList<>();
        queueEntry.add(root);
        while (!queueEntry.isEmpty()) {
            Entry<String> entry = queueEntry.poll();
            if (entry.elementName.equals(parentName)) {
                return entry.parent.elementName;
            } else {
                if (entry.leftChild != null) {
                    queueEntry.offer(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    queueEntry.offer(entry.rightChild);
                }
            }
        }
        return null;
    }
}
//решение которое мне не понять, иду дальше так как слишком долго тут завис
/*
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    //Entry<String> root;

    Entry<String> root = new Entry<>("0");

    static class Entry<T> implements Serializable {

        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }
        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }

    } // End of Entry class


    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            currentNode.checkChildren();
            if (currentNode.isAvailableToAddChildren()) {
                if (currentNode.availableToAddLeftChildren){
                    currentNode.leftChild = new Entry<>(s);
                    currentNode.leftChild.parent = currentNode;
                    return true;
                } else if (currentNode.availableToAddRightChildren) {
                    currentNode.rightChild = new Entry<>(s);
                    currentNode.rightChild.parent = currentNode;
                    return true;
                }
            } else {
                if (currentNode.leftChild != null){
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null){
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return false;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size = -1;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            size++;
            if (currentNode.leftChild != null) {
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                queue.offer(currentNode.rightChild);
            }
        }
        return size;
    }

    @Override
    public boolean remove(Object o) {
        String s = (String) o;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.leftChild != null) {
                if (currentNode.leftChild.elementName.equals(s)){
                    currentNode.leftChild = null;
                    return true;
                }
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                if (currentNode.rightChild.elementName.equals(s)){
                    currentNode.rightChild = null;
                    return true;
                }
                queue.offer(currentNode.rightChild);
            }
        }
        return false;
    }

    String getParent(String entryName){
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.elementName.equals(entryName)){
                return currentNode.parent.elementName;
            } else {
                if (currentNode.leftChild != null) {
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null) {
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return null;
    }
}
 */
