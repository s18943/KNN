package com.company;

import java.util.ArrayList;

public class kNN {
    private ArrayList<InelVector> trainSet;
    private int k;
    private double[] arrK;
    private Integer[] arrI;
    public kNN(int k,ArrayList<InelVector> trainSet){
        this.k=k;
        this.trainSet=trainSet;
    }

    public void determ(InelVector tester){
        int innerI;
        double distance;
        arrK = new double[k];
        arrI = new Integer[k];
        arrK[0]=tester.distance(trainSet.get(0));
        for(int i = 1;i<k;i++){
            distance=tester.distance(trainSet.get(i));
            for(int j = i;j>0;j--) {
                if (arrK[j - 1] < distance) {

                    arrK[j] = arrK[j - 1];
                    arrK[j - 1] = distance;

                    arrI[j] = arrI[j - 1];
                    arrI[j - 1] = i;
                }
            }
            arrK[i]=distance;
            arrI[i]=i;
        }
        for(int i = k;i<trainSet.size();i++) {
            distance = tester.distance(trainSet.get(i));
            innerI = add(distance);
            if(innerI>-1) {
                arrK[innerI] = distance;
                arrI[innerI] = i;
            }
        }
        Integer[] counters = new Integer[k];
        for(int i = 0;i<k;i++){
            counters[i]=1;
        }
        for(int i = 0;i<k;i++){
            for(int j = i+1;j<k;j++){
                if(trainSet.get(arrI[i]).definition.equals(trainSet.get(arrI[j]).definition)){
                    counters[i]++;
                }
            }

            if(counters[i]==k){
                System.out.println(trainSet.get(arrI[i]).definition);
                System.out.println("accuracy:" + ((double)counters[i]/(double)k)*100);
                System.out.println("_____________________________________");
                return;
            }
        }
        int max = 0;
        for(int i = 1;i<k;i++){
            if(counters[i]>counters[max])
                max = i;
        }
        System.out.println(trainSet.get(arrI[max]).definition);
        System.out.println("accuracy:" + ((double)counters[max]/(double)k)*100);
        System.out.println("_____________________________________");
    }
    private int add(double distance){
        for(int i = k-1;i>=0;i--){
            if(arrK[i]>distance){
               return i;
            }
        }
        return -1;
    }
}
