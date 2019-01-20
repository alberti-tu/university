package dsa.upc.edu.minim2.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyUser
{
    @Expose
    @SerializedName("key")
    private int key;

    public KeyUser() {  }

    public int getKey() {   return key;   }

    public void setKey(int key) {   this.key = key;   }
}
