package com.elex.oa.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
public class SerializeUtil {
    public SerializeUtil() {
    }

    public static String object2String(Object obj) {
        String objBody = null;
        ByteArrayOutputStream baops = null;
        ObjectOutputStream oos = null;

        try {
            baops = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baops);
            oos.writeObject(obj);
            byte[] bytes = baops.toByteArray();
            byte[] b64 = Base64Util.encode(bytes);
            objBody = new String(b64, "iso-8859-1");
        } catch (IOException var14) {
            ;
        } finally {
            try {
                if(oos != null) {
                    oos.close();
                }

                if(baops != null) {
                    baops.close();
                }
            } catch (IOException var13) {
                ;
            }

        }

        return objBody;
    }

    public static <T extends Serializable> T getObjectFromString(String objBody, Class<T> clazz) throws UnsupportedEncodingException {
        byte[] b64 = objBody.getBytes("iso-8859-1");
        byte[] bytes = Base64Util.decode(b64);
        ObjectInputStream ois = null;
        Serializable obj = null;

        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = (Serializable)ois.readObject();
        } catch (IOException var17) {
            ;
        } catch (ClassNotFoundException var18) {
            ;
        } finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            } catch (IOException var16) {
                ;
            }

        }

        return (T) obj;
    }
}