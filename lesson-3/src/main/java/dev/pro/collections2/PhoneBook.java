package dev.pro.collections2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {
    private final HashMap<String, HashSet<Long>> entries = new HashMap<>();

    public void add(String name, long number){
        //final long maxNumber = 9999 9999 999
        if (!entries.containsKey(name)){
            entries.put(name, new HashSet<>());
        }
        var phoneList = entries.get(name);
        phoneList.add(number);
    }

    public Long[] get(String name){
        if (!entries.containsKey(name)){
            return new Long[0];
        }
        var phones = entries.get(name);
        var result = new Long[phones.size()];
        phones.toArray(result);


        return result;
    }
}
