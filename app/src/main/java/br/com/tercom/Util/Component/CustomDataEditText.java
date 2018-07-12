package br.com.tercom.Util.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.R;

public class CustomDataEditText extends CustomEditText
{
    private String Entity;
    private String Attribute;

    public CustomDataEditText(Context context) {
        super(context);
        verifyAttributes(null);
    }

    public CustomDataEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        verifyAttributes(attrs);
    }

    public CustomDataEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        verifyAttributes(attrs);
    }

    private void verifyAttributes(AttributeSet attrs)
    {
        TypedArray attributes = AppTercom.getContext().obtainStyledAttributes(attrs, R.styleable.CustomDataEditText);
        String dbField = attributes.getString(R.styleable.CustomDataEditText_Attribute);
        String dbTable = attributes.getString(R.styleable.CustomDataEditText_Entity);
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
