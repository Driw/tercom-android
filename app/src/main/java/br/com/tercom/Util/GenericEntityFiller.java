package br.com.tercom.Util;

import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import br.com.tercom.Util.Component.CustomDataEditText;

/**
 * Class for filling entities properties in given ViewGroup, based on ViewGroup's CustomDataEditText.
 */
public abstract class GenericEntityFiller {
    /**
     * Default Entities path in this project
     */
    private static String entitiesPath = "br.com.tercom.Entity.";

    /**
     * Method created to loop the received ViewGroup, getting each CustomDataEditText and their attributes to fill the designed Entity.
     * @param vg ViewGroup of the calling Activity where there's one or more CustomDataEditText.
     * @return HashMap containing the Entities, with properties values that user set. In case there is more than one Entity defined in the CustomDataEditText, the method will fill the HashMap with all the entities involved.
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public static HashMap<String, Object> fillEntity(ViewGroup vg) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        HashMap<String, Object> entities = new HashMap<>();
        for(int i = 0; i < vg.getChildCount(); i++)
        {
            if(vg.getChildAt(i) instanceof CustomDataEditText)
            {
                CustomDataEditText txt = (CustomDataEditText) vg.getChildAt(i);
                if(!entities.containsKey(txt.getEntity()))
                    entities.put(txt.getEntity(), Class.forName(entitiesPath + txt.getEntity()).newInstance());
                //TODO(Problema: O parâmetro do método está chumbado em String.class. Como fazer para pegar dinamicamente?)
                Method m = entities.get(txt.getEntity()).getClass().getDeclaredMethod("set" + txt.getAttribute(), String.class);
                m.invoke(entities.get(txt.getEntity()), txt.getText().toString());
            }
        }
        return entities;
    }
}
