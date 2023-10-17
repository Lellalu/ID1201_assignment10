package se.kth.id1201;

import java.io.BufferedReader; 
import java.io.FileReader;

public class Zip {
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
    int max;

    public Zip(String file){
        data = new Node[10000];

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                String[] row = line.split(","); 
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max=i-1;
        }catch(Exception e){
            System.out.println("file" + file + "not found");
        }
    }

    public Node linear(String zip){
        for(int i = 0; i <= max; i++){
            if(data[i].code.equals(zip)){
                return data[i];
            }
        }
        return null;
    }

    public Node binary(String zip){
        int start = 0;
        int end = max;
        int mid;

        int zip_int = Integer.valueOf(zip.replace(" ", ""));
        int code_int;
        while(start <= end){
            mid = start + (end-start)/2;
            code_int = Integer.valueOf(data[mid].code.replace(" ", ""));
            if(code_int == zip_int){
                return data[mid];
            }else if(code_int < zip_int){
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
            sb.append(data[i].code + " " + data[i].name + " " + Integer.valueOf(data[i].pop) + '\n');
        }
        return sb.toString();
    }
    
}
