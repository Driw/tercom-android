package br.com.tercom.Util.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.R;

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    private String Entity;
    private String Attribute;

    public CustomEditText(Context context) {
        super(context);
        verifyAttributes(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        verifyAttributes(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        verifyAttributes(attrs);
    }

    private void verifyAttributes(AttributeSet attrs)
    {
        TypedArray attributes = AppTercom.getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        String dbField = attributes.getString(R.styleable.CustomEditText_Attribute);
        String dbTable = attributes.getString(R.styleable.CustomEditText_Entity);
        if(dbField == null || dbTable == null)
            throw new RuntimeException("Entity and Attribute MUST be filled");
        else
        {
            setEntity(dbTable);
            setAttribute(dbField);
        }
    }

    public String getEntity() {
        return Entity == null ? "" : Entity;
    }

    public void setEntity(String entity) {
        this.Entity = entity;
    }

    public String getAttribute() {
        return Attribute == null ? "" : Attribute;
    }

    public void setAttribute(String attribute) {
        this.Attribute = attribute;
    }
}
