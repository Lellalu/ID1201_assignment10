package se.kth.id1201;

import java.io.BufferedReader; 
import java.io.FileReader;

public class ZipInt {
    public class Node{
        Integer code;
        String name;
        int pop;

        public Node(Integer code, String name, int pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    Node[] data;
    int max;

    public ZipInt(String file){
        data = new Node[10000];

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            max=i-1;
        }catch(Exception e){
            System.out.println("file" + file + "not found");
        }
    }

    public Node linear(Integer zip){
        for(int i = 0; i <= max; i++){
            if(data[i].code.intValue() == zip.intValue()){
                return data[i];
            }
        }
        return null;
    }

    public Node binary(Integer zip){
        int start = 0;
        int end = max;
        int mid;
        while(start <= end){
            mid = start + (end-start)/2;
            if(data[mid].code.intValue() == zip.intValue()){
                return data[mid];
            }else if(data[mid].code.intValue() < zip.intValue()){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return null;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i <= max; i++){
            sb.append(Integer.valueOf(data[i].code) + " " + data[i].name + " " + Integer.valueOf(data[i].pop) + '\n');
        }
        return sb.toString();
    }
    
}
