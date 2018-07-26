package br.com.tercom.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;

import br.com.tercom.Annotation.BindObject;

import static br.com.tercom.Util.Util.print;

public class GenericEntity
{
    public void toObject(String json) {
        try {
            JSONObject jObj = new JSONObject(json);
        Iterator<String> keys = jObj.keys();
        Field field;
        while(keys.hasNext())
        {
            field = this.getClass().getDeclaredField(keys.next());
            if(field.isAnnotationPresent(BindObject.class))
            {
                BindObject bo = field.getAnnotation(BindObject.class);
                print("ANNOTATION BIXO!!! - " + bo.value());
            }
            field.setAccessible(true);
            field.set(this, jObj.get(field.getName()));
        }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
