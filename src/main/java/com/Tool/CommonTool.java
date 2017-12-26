package com.Tool;

import com.entity.DataField;
import com.entity.Object;
import com.repository.DataFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonTool {
    public static List<String> dbNumberTypes = new ArrayList(Arrays.asList("TINYINT",  "SMALLINT", "MEDIUMINT", "INT", "INTEGER",
            "BIGINT", "FLOAT", "BIGINT", "FLOAT", "DOUBLE", "DECIMAL"));

    public static List<String> dbTextTypes = new ArrayList(Arrays.asList("CHAR", "VARCHAR", "TINYTEXT", "TEXT", "MEDIUMTEXT",
            "LONGTEXT"));

    public static boolean isTypeMatch(String type1, String type2){
        if (dbTextTypes.contains(type1.toUpperCase()) && !(dbTextTypes.contains(type2.toUpperCase()))){
            return false;
        } else if (dbNumberTypes.contains(type1.toUpperCase()) && !(dbNumberTypes.contains(type2.toUpperCase()))){
            return false;
        }
        return true;
    }
}
