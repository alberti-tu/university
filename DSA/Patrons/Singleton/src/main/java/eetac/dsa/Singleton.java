package eetac.dsa;

import java.util.HashMap;
import java.util.ResourceBundle;

public class Singleton
{
    private static Singleton instance = null;
    private ResourceBundle es;
    private ResourceBundle en;
    private HashMap<String,ResourceBundle> listaresources;


    protected Singleton()
    {
        listaresources = new HashMap<String, ResourceBundle>();
        /*this.es = ResourceBundle.getBundle("ca");
        this.en = ResourceBundle.getBundle("en");*/
    }

    private ResourceBundle resources(String lengua)
    {
        ResourceBundle tmp = listaresources.get(lengua);
        if(tmp==null)
        {
            tmp = ResourceBundle.getBundle(lengua);
            listaresources.put(lengua,tmp);
        }
        return tmp;
    }

    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    public String getText(String lengua, String key)
    {
        String ret=null;

        /*if (lengua.equals("es")) ret = es.getString(key);
        if (lengua.equals("en")) ret = en.getString(key);*/
        ret = resources(lengua).getString(key);

        return ret;
    }
}