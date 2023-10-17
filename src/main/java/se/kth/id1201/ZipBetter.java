package se.kth.id1201;

import java.io.BufferedReader; 
import java.io.FileReader;

public class ZipBetter{
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
    private final int SIZE = 10000;

    public ZipBetter(String file){
        data = new Node[SIZE];

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                int index = code % SIZE;
                if(data[index] == null){
                    data[index] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                }else{
                    int i = index+1;
                    while(data[i] != null){
                        i = (i + 1) % SIZE;
                        if(i == index){
                            throw new Exception("Hash table is full");
                        }
                    }
                    data[i] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                } 
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public Node lookup(String zip){
        int index = Integer.valueOf(zip.replace(" ", "")) % SIZE;
        Node node = data[index];
        if(data[index].code.equals(zip)){
            return node;
        }else{
            for(int i = index; i < data.length; i++){
                if(data[i].code.equals(zip)){
                    return data[i];
                }
            }
        }
        return null; 
    } 
}
