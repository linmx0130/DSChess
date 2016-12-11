package com.sweetdum.dschess.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mengxiao Lin on 2016/12/7.
 */
public class ListTupleTool {
    public static List<Tuple<Integer>> getListTupleFromString(String str){
        Scanner sin = new Scanner(str);
        ArrayList<Tuple<Integer>> ret=new ArrayList<>();
        do{
            int x, y;
            if (!sin.next().equals("(")) return null;
            x = sin.nextInt();
            if (!sin.next().equals(",")) return null;
            y = sin.nextInt();
            if (!sin.next().equals(")")) return null;
            ret.add(new Tuple<>(x,y));
        }while(sin.hasNext());
        return ret;
    }
    public static String getStringFromListTuple(List<Tuple<Integer>> l ){
        StringBuilder buf = new StringBuilder();
        if (l==null){
            return "";
        }
        for (Tuple<Integer> t :l){
            buf.append(t.toString());
        }
        return buf.toString();
    }
}
