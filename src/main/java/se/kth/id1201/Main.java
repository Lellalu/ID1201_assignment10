package se.kth.id1201;

import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    { 
        //ZipBuckets.Node node = z.findCollisions();
        benchmarkBinaryLinear1(args[0]);
        benchmarkBinaryLinear2(args[0]);
        benchmarkCollisions(args[0]);
        benchmarkLookupVersusBinary(args[0]);
        benchmarkBucketsVersusBetter(args[0]);
    }

    public static void benchmarkBucketsVersusBetter(String file){
        int repitition = 100;
        long zipBucketsSumTime = 0;
        long zipBetterSumTime = 0;
        long stratTime;
        long endTime;

        ZipBuckets zBuckets = new ZipBuckets(file);
        ZipBetter zBetter = new ZipBetter(file);

        System.out.printf("#look up in hash table, time in ns\n");
        System.out.printf("#%12s%12s\n", "buckets", "better");
        for(int i = 0; i < repitition; i++){
            stratTime = System.nanoTime();
            zBuckets.lookupBucket("611 31");
            endTime = System.nanoTime();
            zipBucketsSumTime += endTime - stratTime;

            stratTime = System.nanoTime();
            zBetter.lookup("611 31");
            endTime = System.nanoTime();
            zipBetterSumTime += endTime - stratTime;
        }
        System.out.printf("%12.2f%12.2f\n", (double)zipBucketsSumTime/repitition, (double)zipBetterSumTime/repitition);
            
    }

    public static void benchmarkLookupVersusBinary(String file){
        int repitition = 100;
        long zipBucketsSumTime = 0;
        long zipBinarySumTime = 0;
        long stratTime;
        long endTime;

        ZipBuckets zBuckets = new ZipBuckets(file);
        Zip zip = new Zip(file);

        System.out.printf("#search in hash table with Lookup() and Binary(), time in ns\n");
        System.out.printf("#%12s%12s\n", "lookup", "binary");
        for(int i = 0; i < repitition; i++){
            stratTime = System.nanoTime();
            zBuckets.lookupBucket("611 31");
            endTime = System.nanoTime();
            zipBucketsSumTime += endTime - stratTime;

            stratTime = System.nanoTime();
            zip.binary("611 31");
            endTime = System.nanoTime();
            zipBinarySumTime += endTime - stratTime;
        }
        System.out.printf("%12.2f%12.2f\n", (double)zipBucketsSumTime/repitition, (double)zipBinarySumTime/repitition);
            
    }

    public static void benchmarkBinaryLinear1(String file){
        int repitition = 100;
        long binarySumTime = 0;
        long linearSumTime = 0;
        long stratTime;
        long endTime;

        Zip zip = new Zip(file);
        String[] keys = {"111 15", "984 99"};

        System.out.printf("#Search the key code: \"111 15\", \"984 99\" within entries, time in ns\n");
        System.out.printf("#%5s%12s%12s\n", "code String","linear", "binary");
        for(String key : keys){
            for(int i = 0; i < repitition; i++){
            stratTime = System.nanoTime();
            zip.linear(key);
            endTime = System.nanoTime();
            linearSumTime += endTime - stratTime;

            stratTime = System.nanoTime();
            zip.binary(key);
            endTime = System.nanoTime();
            binarySumTime += endTime - stratTime;
            }
            System.out.printf("%s%18.2f%12.2f\n", key, (double)linearSumTime/repitition, (double)binarySumTime/repitition);
        }  
    }

    public static void benchmarkBinaryLinear2(String file){
        int repitition = 100;
        long binarySumTime = 0;
        long linearSumTime = 0;
        long stratTime;
        long endTime;

        ZipInt zip = new ZipInt(file);
        int[] keys = {11115, 98499};

        System.out.printf("#Search the key Integer: 111 15 and 984 99 within entries, time in ns\n");
        System.out.printf("#%5s%12s%12s\n", "code Integer","linear", "binary");
        for(int key : keys){
            for(int i = 0; i < repitition; i++){
            stratTime = System.nanoTime();
            zip.linear(key);
            endTime = System.nanoTime();
            linearSumTime += endTime - stratTime;

            stratTime = System.nanoTime();
            zip.binary(key);
            endTime = System.nanoTime();
            binarySumTime += endTime - stratTime;
            }
            System.out.printf("%s%20.2f%12.2f\n", key, (double)linearSumTime/repitition, (double)binarySumTime/repitition);
        }  
    }

    public static void benchmarkCollisions(String file){
        int[] length = {12345,13513,13600,14000};

        ZipHash zipHash = new ZipHash(file);
        for(int i :length){
            zipHash.collisions(i);
        }
        
    }
}

