package se.kth.id1201;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.LinkedList;

public class ZipBuckets{
    public class Node{
        String code;
        String name;
        int pop;
        Node next;

        public Node(String code, String name, int pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
            this.next = null;
        }
    }

    Node[] data;
    LinkedList<Integer> keys;
    private final int SIZE = 10000;

    public ZipBuckets(String file){
        data = new Node[SIZE];
        keys = new LinkedList<Integer>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                int index = code % SIZE;
                if(data[index] == null){
                    data[index] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                }else{
                    Node node = data[index];
                    while(node.next != null){
                        node = node.next;
                    }
                    node.next = new Node(row[0], row[1], Integer.valueOf(row[2]));
                } 
                keys.add(code);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void collisions(int mod){ 
        int[] data = new int[mod]; 
        int[] cols = new int[10];
        for (Integer key : keys){
            Integer index = key % mod; 
            cols[data[index]]++; 
            data[index]++; 
        } 
        System.out.print(mod); 
        for(int i = 0; i < 10;i++){ 
            System.out.print("\t" + cols[i]); 
        } 
        System.out.println();
    }

     public Node findCollisions(){
        int[] data_length = new int[10000];
        for(int i = 0; i < data.length; i++){
            int length = 0;
            Node node = data[i];
            while(node != null){
                length++;
                node = node.next;
            }
            data_length[i] = length;
        }
        int max = data_length[0];
        int maxCodeIndex = 0;
        for(int i = 0; i < data_length.length; i++){
            if (data_length[i] > max){
                max = data_length[i];
                maxCodeIndex = i;
            }
        }
        return data[maxCodeIndex];
     }

    public Node lookupBucket(String zip){
        int index = Integer.valueOf(zip.replace(" ", "")) % SIZE;
        Node node = data[index];
        if(node.next == null){
            return node;
        }else{
            while(node != null){
                node = node.next;
                if(node.code.equals(zip)){
                    return node;
                }
            }
        }
        return null; 
    }
    
}
