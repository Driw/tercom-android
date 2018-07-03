package br.com.tercom.Util;

import android.view.ViewGroup;
import android.widget.EditText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class GenericEntityFiller {
    public static <T> void fillEntity(Class<T> t, ViewGroup vg) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T entity = t.newInstance();
        for(int i = 0; i < vg.getChildCount(); i++)
        {
            if(vg.getChildAt(i) instanceof EditText)
            {
                EditText txt = (EditText) vg.getChildAt(i);
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
