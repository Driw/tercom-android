package br.com.tercom.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum created to keep all possible variable types that will come from CustomEditText's attributes from xml.
 */
public enum EnumTypes
{
    STRING(0), INTEGER(1), FLOAT(2);

    /**
     * Using Map to keep EnumTypes int and string values, to be able to get EnumType by int.
     */
    private static Map<Integer, EnumTypes> values = new HashMap<>();
    private final int typeIndex;
    private final Class classType;
    private final Class primitive;

    EnumTypes(int index)
    {
       typeIndex = index;

        switch(typeIndex)
        {
            default:
                classType = String.class;
                primitive = String.class;
                break;
            case 1:
                classType = Integer.class;
                primitive = int.class;
                break;
            case 2:
                classType = Float.class;
                primitive = float.class;
                break;
        }
    }

    /**
     * Fill Map with EnumType's int and String. Run only once.
     */
    static
    {
        for(EnumTypes value : EnumTypes.values())
            values.put(value.getValue(), value);
    }

    public int getValue()
    {
        return typeIndex;
    }

    /**
     *
     * @param intType Enum's int value
     * @return EnumTypes of the given value
     */
    public static EnumTypes getEnum(int intType)
    {
        return values.get(intType);
    }

    /**
     *
     * @return Class object according to the EnumType's set value.
     */
    public Class getClassType()
    {
        return classType;
    }

    public Class getPrimitive()
    {
        return primitive;
    }
}
