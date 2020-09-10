package com.dwiromadon.myapplication.server;

public class BaseURL {

//    public static String baseUrl = "http://192.168.18.9:5050/";
//    public static String baseUrl = "http://172.31.0.120:5050/";
//    public static String baseUrl = "http://10.11.7.89:5050/";
    public static String baseUrl = "http://192.168.43.245:5050/";

    public static String inputPetShhop          = baseUrl + "petshop/input";
    public static String updatePetShhop         = baseUrl + "petshop/ubah/";
    public static String getDataPetShop         = baseUrl + "petshop/getdata/";
    public static String tambahGambarPetShop    = baseUrl + "petshop/ubahgambar/";
    public static String getJarak               = baseUrl + "petshop/getjarak/";

    //input history
    public static String inputHistory           = baseUrl + "history/input";
    public static String getHistory             = baseUrl + "history/getjarak";
}