package se.kth.id1201;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.LinkedList;

public class ZipHash {
    public class Node{
        String code;
        String name;
        int pop;

        public Node(String code, String name, int pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    Node[] data;
    LinkedList<Integer> keys;

    public ZipHash(String file){
        data = new Node[100000];
        keys = new LinkedList<Integer>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[code] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                keys.add(code);
            }
        }catch(Exception e){
            System.out.println("file" + file + "not found");
        }
    }

    public Node lookup(String zip){
        return data[Integer.valueOf(zip.replace(" ", ""))];
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

    public Node lookupBucket(String zip){
        return data[Integer.valueOf(zip.replace(" ", ""))];
    }
    
}
