package br.com.tercom.Util;

import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class GenericEntityFiller {
    public static <T> void fillEntity(Class<T> t, ViewGroup vg) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T entity = t.newInstance();
        Method m = entity.getClass().getDeclaredMethod("getCpf");
        String cpf = m.invoke(entity).toString();
        //TODO(chamar métodos dinamicamente)
    }
}
