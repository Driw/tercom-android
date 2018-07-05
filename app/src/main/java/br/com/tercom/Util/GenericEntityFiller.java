package br.com.tercom.Util;

import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.tercom.Util.Component.CustomDataEditText;


public class GenericEntityFiller {
    private static String entitiesPath = "br.com.tercom.Entity.";
    public static <T> void fillEntity(Class<T> t, ViewGroup vg) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        T entity = t.newInstance();

        Class<?> test = Class.forName(entitiesPath + "TercomFuncionario");

        for(int i = 0; i < vg.getChildCount(); i++)
        {
            if(vg.getChildAt(i) instanceof CustomDataEditText)
            {
                CustomDataEditText txt = (CustomDataEditText) vg.getChildAt(i);


                Method m = entity.getClass().getDeclaredMethod(txt.getTag().toString(), String.class);
                m.invoke(entity, txt.getText()).toString();
                Method mTeste = entity.getClass().getDeclaredMethod("getSenha");
                String teste = mTeste.invoke(entity).toString();
            }
        }
        Method m = entity.getClass().getDeclaredMethod("getCpf");
        String cpf = m.invoke(entity).toString();
        //TODO(chamar métodos dinamicamente)
    }
}
