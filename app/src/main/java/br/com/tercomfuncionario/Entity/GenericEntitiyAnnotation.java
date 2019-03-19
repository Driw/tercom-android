package br.com.tercomfuncionario.Entity;



import org.json.JSONException;
import org.json.JSONObject;


public class GenericEntitiyAnnotation  {

    protected <T> T toObject(String json) throws JSONException {
        JSONObject jO  = new JSONObject(json);
//        Collection<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(BindObject.class);

        return (T) new Object();

    }

}
