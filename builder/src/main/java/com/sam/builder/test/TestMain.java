package com.sam.builder.test;

import com.sam.builder.builder.ConstructorArg;

/**
 * @author Mr.xuewenming
 * @title: TestMain
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1810:13
 */
public class TestMain {

    public static void main(String[] args) {
        ConstructorArg build1 = new ConstructorArg.Builder().setRef(Boolean.FALSE).setArg(1).setType(Integer.class).build();
        Class type = build1.getType();
        Object arg = build1.getArg();
        System.out.println("type : " + type + "arg : " + arg);


        //ConstructorArg build = new ConstructorArg.Builder().setRef(Boolean.TRUE).setArg("A").build();
        //System.out.println(build.getArg());



    }
}
