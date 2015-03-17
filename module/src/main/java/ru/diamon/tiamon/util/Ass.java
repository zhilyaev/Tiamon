package ru.diamon.tiamon.util;

public class Ass {
    private String[] key;
    private String[] val;

    private int N;

    public Ass(String[] k, String[] v){
        val = v;
        key = k;

        N = val.length;
    }

    public String val(String k) {
        int i;
        for(i=0; i<N;i++){
            if (k.equals(key[i])) break;
        }
        if (i==N){return null;}
        return val[i];
    }

    public String key(String v) {
        int i;
        for(i=0; i<N;i++){
            if (v.equals(val[i])) break;
        }
        if (i==N){return null;}
        return key[i];
    }
}
/*  private final String[] keys = {"_PET","_NAME","_AGE","_NEXTTIME","_MONEY","_TIME","_BURN"};
    private final String[] vals = {"PET", "NAME" , "AGE","NEXTTIME", "MONEY", "TIME","BURN"};
    protected Ass F = new Ass(keys,vals);
*/