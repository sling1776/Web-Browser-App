package com.sling.webbrowser;

public class History {
    private Node root=null;
    private Node leaf=null;
    private Node cur= null;

    private static class Node{
        Node parent = null;
        Node child = null;
        String name;

        Node(String n){
            name = n;
        }
    }



    public void addHistory(String node){
        Node newNode = new Node(node);
        if(root == null){
            root = newNode;
            leaf = newNode;
            cur = newNode;
        } else{
            cur.child = newNode; //change current node to point to the new node.
            leaf = newNode; //make new node the last node in tree.
            leaf.parent = cur; //link the last node back to its parent.
            cur = newNode; //move to next node.
        }
    }

    public void traverseForward(){
        try{cur = cur.child;}
        catch(NullPointerException e){
            System.out.println("Handled Esception");
            cur = leaf;
        }
    }

    public void traverseBackward(){
        try{cur = cur.parent;}
        catch(NullPointerException e){
            System.out.println("Handled Esception");
            cur = root;
        }
    }

    public String getName(){
        if(cur == null){
            return "";
        }
        return cur.name;
    }

    }

