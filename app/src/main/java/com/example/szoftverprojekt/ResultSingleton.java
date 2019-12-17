package com.example.szoftverprojekt;

import java.util.ArrayList;

public class ResultSingleton {
    private static  ResultSingleton res;
    public ArrayList<Product> result=new ArrayList<>();

    private ResultSingleton (){
    }

    public static ResultSingleton getResult()
    {
        if (res==null)
            res=new ResultSingleton();
        return res;
    }

    public ArrayList<Product> getresult()
    {
        return result;
    }

    public void insertresult(Product product)
    {
        result.add(product);
    }
}
