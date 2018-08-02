package br.com.tercom.Entity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;

import br.com.tercom.Annotation.BindObject;

import static br.com.tercom.Util.Util.print;


public class GenericEntity
{



    public <T extends GenericEntity> T toObject(String json, Class<T> selectedClass) {
        try {
            JSONObject jObj = new JSONObject(json);

            if(jObj.has("result"))
                jObj = jObj.getJSONObject("result");

            jObj = jObj.getJSONObject("attributes");
            Iterator<String> keys = jObj.keys();
            Field field;
            while(keys.hasNext())
            {
                field = selectedClass.getDeclaredField(keys.next());
                field.setAccessible(true);
                if(field.isAnnotationPresent(BindObject.class))
                {
                    BindObject bo = field.getAnnotation(BindObject.class);

                    if(bo.type() == BindObject.TYPE.OBJECT)
                    {
                        Class<? extends GenericEntity> classe = (Class<? extends GenericEntity>) field.getType();
                        field.set(this,classe.newInstance().toObject(jObj.getJSONObject(field.getName()).toString(),classe));
                    }
                    else
                    {
                        ParameterizedType listType = (ParameterizedType) field.getGenericType();
                        Class<? extends GenericEntity> classe = (Class<? extends GenericEntity>) listType.getActualTypeArguments()[0];
                        field.set(this, toList(jObj.get(field.getName()).toString(),classe));
                    }
                }
                else
                {
                    if(!jObj.isNull(field.getName()))
                    field.set(this,jObj.get(field.getName()));
                }
            }

            return selectedClass.cast(this);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends GenericEntity> ArrayList<?> toList(String json,Class<T> selectedClass) {
        try {

            JSONObject jObj = new JSONObject(json);
            jObj = jObj.getJSONObject("attributes");
            JSONArray jsonArray = jObj.getJSONArray("elements");
            ArrayList<T> values = new ArrayList<>();

            for(int i = 0; i < jsonArray.length();i++)
            {
                values.add(selectedClass.newInstance().toObject(jsonArray.getJSONObject(i).toString(),selectedClass));
            }

            return values;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
