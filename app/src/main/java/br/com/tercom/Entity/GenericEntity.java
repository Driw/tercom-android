package br.com.tercom.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;

import br.com.tercom.Annotation.BindObject;


public class GenericEntity
{
    public void toObject(String json) {
        try {
            JSONObject jObj = new JSONObject(json);
            //TODO()
//            try{
//                jObj = new JSONObject().getJSONObject("result");
//            }
//            catch(Exception e)
//            {
//
//            }
            jObj = jObj.getJSONObject("attributes");
            Iterator<String> keys = jObj.keys();
            Field field;
            while(keys.hasNext())
            {
                field = this.getClass().getDeclaredField(keys.next());
                if(field.isAnnotationPresent(BindObject.class))
                {
                    BindObject bo = field.getAnnotation(BindObject.class);
                    if(bo.type() == BindObject.TYPE.LIST)
                    {

                    }
                    else
                    {
                        //ANTES - objType era um atributo de BindObject
//                        Object obj = bo.objType().newInstance();
//                        Method m = bo.objType().getDeclaredMethod("toObject", String.class);
//                        m.invoke(obj, jObj.getJSONObject());
                    }
                }
                else
                {
                    field.setAccessible(true);
                    field.set(this, jObj.get(field.getName()));
                }
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
