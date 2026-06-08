package com.dsa.iterator.problems;


import java.util.*;

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Leetcode 588. Design In-Memory File System

// ls(path)
// mkdir(path)
// addContentToFile(filePath, content)
// readContentFromFile(filePath)

public class FileSystem{

    class Node{
        String name;
        boolean isFile;
        StringBuilder content;
        Map<String,Node> children;

        Node(String name){
            this.name=name;
            this.isFile=false;
            this.content=new StringBuilder();
            this.children=new HashMap<>();
        }
    }

    Node root;
    public FileSystem(){
        root=new Node("/");
    }

    private Node traverse(String path, boolean create){
        String[] parts = path.split("/");
        Node curr = root;
        for(int i=1;i<parts.length;i++){
            String part = parts[i];
            if(!curr.children.containsKey(part)){
                if(create){
                    curr.children.put(part,new Node(part));
                }else{
                    return null;
                }
            }
            curr=curr.children.get(part);
        }
        return curr;
    }

    public List<String> ls(String path){
        Node node = traverse(path,false);
        List<String> result = new ArrayList<>();
        if(node.isFile){
            result.add(node.name);
            return result;
        }
        result.addAll(node.children.keySet());
        Collections.sort(result);
        return result;
    }
    public void mkdir(String path){
        traverse(path,true);
    }
    public void addContentToFile(String path, String content){
        Node node = traverse(path,true);
        node.isFile=true;
        node.content.append(content);
    }
    public String readContentFromFile(String path){
        Node node = traverse(path,false);
        return node.content.toString();
    }

}
