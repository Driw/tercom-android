package br.com.tercom.Util.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.R;

public class CustomDataEditText extends CustomEditText
{
    private String DBTable;
    private String DBField;

    public CustomDataEditText(Context context) {
        super(context);
        verifyAttributes(null);;
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
        String dbField = attributes.getString(R.styleable.CustomDataEditText_DBField);
        String dbTable = attributes.getString(R.styleable.CustomDataEditText_DBTable);
        if(dbField.equals("") || dbTable.equals(""))
            throw new RuntimeException("DBColumn and DBField MUST be filled");
        else
        {
            setDBTable(dbTable);
            setDBField(dbField);
        }
    }

    public String getDBTable() {
        return DBTable == null ? "" : DBTable;
    }

    public void setDBTable(String DBTable) {
        this.DBTable = DBTable;
    }

    public String getDBField() {
        return DBField == null ? "" : DBField;
    }

    public void setDBField(String DBField) {
        this.DBField = DBField;
    }
}
