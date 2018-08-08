package br.com.tercom.Entity;


import com.google.gson.annotations.Expose;

import java.lang.reflect.ParameterizedType;

public class ApiResponse<T extends GenericEntity> {

    private Class<T> clazzOfT;
    private int status;
    private String message;
    private String time;
    private T result;

    public T getInstance() throws Exception {
        return clazzOfT.newInstance();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getResult() {
        return result;
    }

    public void setResult(String resultString) {
        try {

            result = getInstance().toObject(resultString,clazzOfT);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
