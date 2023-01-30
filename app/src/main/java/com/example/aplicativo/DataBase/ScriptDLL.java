package com.example.aplicativo.DataBase;

public class ScriptDLL {


    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();

        sql.append("   CREATE TABLE IF NOT EXISTS CLIENTE(");
        sql.append("   CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("   NAME VARCHAR (250) NOT NULL DEFAULT(''),");
        sql.append("   ADDRESS VARCHAR (255) NOT NULL DEFAULT(''),");
        sql.append("   EMAIL VARCHAR (200) NOT NULL DEFAULT(''),");
        sql.append("   PASSWORD VARCHAR (20) NOT NULL DEFAULT('') ) ");

        return sql.toString();
    }

}
