package com.elex.oa.query;
import com.elex.oa.util.StringUtil;

import java.lang.reflect.Field;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
/**
 *@author hugo.zhao
 *@since 2018/4/20 16:59
*/
public class QueryParam implements IWhereClause {
    public static final String OP_EQUAL = "EQ";
    public static final String OP_NOT_EQUAL = "NEQ";
    public static final String OP_GREAT = "GT";
    public static final String OP_GREAT_EQUAL = "GE";
    public static final String OP_LESS = "LT";
    public static final String OP_LESS_EQUAL = "LE";
    public static final String OP_LIKE = "LK";
    public static final String OP_LEFT_LIKE = "LEK";
    public static final String OP_RIGHT_LIKE = "RIK";
    public static final String OP_IS_NULL = "ISNULL";
    public static final String OP_NOTNULL = "NOTNULL";
    public static final String OP_IN = "IN";
    public static final String FIELD_TYPE_STRING = "S";
    public static final String FIELD_TYPE_LONG = "L";
    public static final String FIELD_TYPE_INTEGER = "I";
    public static final String FIELD_TYPE_SHORT = "SN";
    public static final String FIELD_TYPE_FLOAT = "F";
    public static final String FIELD_TYPE_DOUBLE = "DB";
    public static final String FIELD_TYPE_DATE = "D";
    public static final String FIELD_TYPE_BIGDECIMAL = "BD";
    public static final String JOIN_TYPE_INNER = "I";
    public static final String JOIN_TYPE_LEFT = "L";
    public static final String JOIN_TYPE_RIGHT = "R";
    public JoinType joinType;
    private String fieldName;
    private String opType;
    private Object value;
    private String fieldType;

    public QueryParam() {
        this.joinType = JoinType.INNER;
    }

    public QueryParam(String fieldName, String opType, Object value) {
        this.joinType = JoinType.INNER;
        this.fieldName = fieldName;
        this.opType = opType;
        this.value = value;
    }

    public QueryParam(String fieldName, JoinType joinType, String opType, Object value) {
        this.joinType = JoinType.INNER;
        this.fieldName = fieldName;
        this.joinType = joinType;
        this.opType = opType;
        this.value = value;
    }

    public QueryParam(String fieldName, String fieldType, String opType, Object value) {
        this.joinType = JoinType.INNER;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.opType = opType;
        this.value = value;
    }

    public Predicate execute(CriteriaBuilder builder, Root root, Class entityClass) throws Exception {
        boolean isNeedJoin = false;
        int joinLabelIndex = this.fieldName.indexOf("#");
        if(joinLabelIndex != -1) {
            String fields = this.fieldName.substring(joinLabelIndex + 1);
            this.fieldName = this.fieldName.substring(0, joinLabelIndex);
            this.joinType = this.getJoinType(fields);
        }

        String[] fields1 = this.fieldName.split("[.]");
        if(fields1 != null && fields1.length > 1) {
            this.fieldName = fields1[0];
            isNeedJoin = true;
        }

        Field field = null;

        for(Class cls = entityClass; cls != Object.class; cls = cls.getSuperclass()) {
            try {
                field = cls.getDeclaredField(this.fieldName);
            } catch (Exception var13) {
                ;
            }

            if(field != null) {
                break;
            }
        }

        Expression valueExpression = builder.literal(this.value);
        EntityType entityType = root.getModel();
        Path expression = null;
        if(isNeedJoin) {
            Path attribute = root.join(this.fieldName, this.joinType).get(fields1[1]);
            expression = attribute;
            attribute.alias(StringUtil.makeFirstLetterLowerCase(this.fieldName));
        } else {
            SingularAttribute attribute1 = entityType.getSingularAttribute(this.fieldName, field.getType());
            expression = root.get(attribute1);
        }

        return "EQ".equals(this.opType)?builder.equal(expression, valueExpression):("NEQ".equals(this.opType)?builder.notEqual(expression, valueExpression):("GT".equals(this.opType)?builder.greaterThan(expression, valueExpression):("GE".equals(this.opType)?builder.greaterThanOrEqualTo(expression, valueExpression):("LT".equals(this.opType)?builder.lessThan(expression, valueExpression):("LE".equals(this.opType)?builder.lessThanOrEqualTo(expression, valueExpression):("LK".equals(this.opType)?builder.like(expression, builder.literal(this.value + "")):("LEK".equals(this.opType)?builder.like(expression, builder.literal(this.value + "")):("RIK".equals(this.opType)?builder.like(expression, builder.literal(this.value + "")):("IN".equals(this.opType)?expression.in((Object[])((Object[])this.value)):builder.equal(expression, valueExpression))))))))));
    }

    public SingularAttribute getFieldAttribute(EntityType entityType, String fileName, Class entityClass) throws NoSuchFieldException {
        Field field = entityClass.getDeclaredField(fileName);
        SingularAttribute attribute = entityType.getSingularAttribute(this.fieldName, field.getType());
        return attribute;
    }

    public Class getFieldTypeClass() {
        return "S".equals(this.fieldType)?String.class:("L".equals(this.fieldType)?Long.class:("SN".equals(this.fieldType)?Short.class:("F".equals(this.fieldType)?Float.class:("I".equals(this.fieldType)?Integer.class:("DB".equals(this.fieldType)?Double.class:("D".equals(this.fieldType)?Date.class:String.class))))));
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOpType() {
        return this.opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public JoinType getJoinType(String shortJoinName) {
        return "L".equals(shortJoinName)?JoinType.LEFT:("R".equals(shortJoinName)?JoinType.RIGHT:JoinType.INNER);
    }

    public String getFieldParam() {
        String name = this.fieldName.replace(".", "");
        if(this.opType == null) {
            this.opType = "EQ";
        } else {
            this.opType = this.opType.toUpperCase();
        }

        if("D".equals(this.fieldType)) {
            if("LE".equals(this.opType) || "LT".equals(this.opType)) {
                return name + "_END";
            }

            if("GT".equals(this.opType) || "GE".equals(this.opType)) {
                return name + "_START";
            }
        }

        return name;
    }

    public String getSql() {
        if(this.opType == null) {
            this.opType = "EQ";
        } else {
            this.opType = this.opType.toUpperCase();
        }

        String fieldParam = "#{" + this.getFieldParam() + "}";
        String sql = this.fieldName;
        if("EQ".equals(this.opType)) {
            sql = sql + " = " + fieldParam;
        } else if("NEQ".equals(this.opType)) {
            sql = sql + " != " + fieldParam;
        } else if("LT".equals(this.opType)) {
            sql = sql + " < " + fieldParam;
        } else if("LE".equals(this.opType)) {
            sql = sql + " <= " + fieldParam;
        } else if("GT".equals(this.opType)) {
            sql = sql + " > " + fieldParam;
        } else if("GE".equals(this.opType)) {
            sql = sql + " >= " + fieldParam;
        } else if("LEK".equals(this.opType)) {
            sql = sql + " like " + fieldParam;
        } else if("RIK".equals(this.opType)) {
            sql = sql + " like  " + fieldParam;
        } else if("LK".equals(this.opType)) {
            sql = sql + " like  " + fieldParam;
        } else if("ISNULL".equals(this.opType)) {
            sql = sql + " is null " + fieldParam;
        } else if("NOTNULL".equals(this.opType)) {
            sql = sql + " is not null";
        } else {
            sql = sql + " =  " + fieldParam;
        }

        return sql;
    }

    public static void main(String[] args) {
        Class clz = "aaa".getClass();
        System.out.println(clz == String.class);
    }
}
