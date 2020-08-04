package com.wujie.learning.util;

import com.alibaba.fastjson.JSON;
import com.opencsv.CSVReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\XXX\\XXX\\XXX\\XXX.csv");
            CSVReader csvReader = new CSVReader(new FileReader(file));
            List<String[]> strArrayList = new ArrayList<String[]>();
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                strArrayList.add(nextLine);
            }
            System.out.println(JSON.toJSONString(strArrayList));

        } catch (Exception e) {

        }




    }
}
