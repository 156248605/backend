package com.elex.oa.query;
import java.lang.reflect.Field;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
/**
 *@author hugo.zhao
 *@since 2018/4/20 16:58
*/
public class SortParam {
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    private String property;
    private String direction;

    public SortParam() {
    }

    public SortParam(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSql() {
        return this.property + " " + this.direction;
    }

    public String toString() {
        return this.property + " " + this.direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Order execute(CriteriaBuilder builder, Root root, Class entityClass) throws Exception {
        boolean isNeedJoin = false;
        String[] fields = this.property.split("[.]");
        if(fields != null && fields.length > 1) {
            this.property = fields[0];
            isNeedJoin = true;
        }

        Field field = null;

        for(Class cls = entityClass; cls != Object.class; cls = cls.getSuperclass()) {
            try {
                field = cls.getDeclaredField(this.property);
            } catch (Exception var13) {
                ;
            }

            if(field != null) {
                break;
            }
        }

        EntityType entityType = root.getModel();
        SingularAttribute attribute = entityType.getSingularAttribute(this.property, field.getType());
        Path expression = null;
        if(isNeedJoin) {
            Path path = null;

            for(int i = 0; i < fields.length; ++i) {
                if(path == null) {
                    path = root.get(fields[i]);
                } else {
                    path = path.get(fields[i]);
                }
            }

            expression = path;
        } else {
            expression = root.get(attribute);
        }

        return "ASC".equalsIgnoreCase(this.direction)?builder.asc(expression):builder.desc(expression);
    }
}
