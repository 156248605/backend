package com.elex.oa.query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
/**
 *@author hugo.zhao
 *@since 2018/4/20 16:57
*/
public class FieldLogic implements IWhereClause {
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String NOT = "NOT";
    private String logicOp = "AND";
    private List<IWhereClause> commands = new ArrayList();

    public FieldLogic() {
    }

    public FieldLogic(String logicOp) {
        this.logicOp = logicOp;
    }

    public Predicate execute(CriteriaBuilder builder, Root root, Class entityClass) throws Exception {
        Predicate condition = null;
        Iterator var5 = this.commands.iterator();

        while(var5.hasNext()) {
            IWhereClause cause = (IWhereClause)var5.next();
            if(condition == null) {
                condition = cause.execute(builder, root, entityClass);
                if("NOT".equals(this.logicOp)) {
                    condition = builder.not(condition);
                }
            } else if("AND".equals(this.logicOp)) {
                condition = builder.and(condition, cause.execute(builder, root, entityClass));
            } else if("OR".equals(this.logicOp)) {
                condition = builder.or(condition, cause.execute(builder, root, entityClass));
            } else {
                condition = builder.not(condition);
            }
        }

        return condition;
    }

    public String getLogicOp() {
        return this.logicOp;
    }

    public void setLogicOp(String logicOp) {
        this.logicOp = logicOp;
    }

    public List<IWhereClause> getCommands() {
        return this.commands;
    }

    public void setCommands(List<IWhereClause> commands) {
        this.commands = commands;
    }

    public String getSql() {
        if(this.commands.size() == 0) {
            return "";
        } else if(this.commands.size() == 1 && !"NOT".equals(this.logicOp)) {
            return ((IWhereClause)this.commands.get(0)).getSql();
        } else {
            StringBuffer sqlBuf = new StringBuffer("(");
            int i = 0;
            Iterator var3;
            IWhereClause clause;
            if(this.commands.size() > 0 && "NOT".equals(this.logicOp)) {
                sqlBuf.append(" NOT (");

                for(var3 = this.commands.iterator(); var3.hasNext(); sqlBuf.append(clause.getSql())) {
                    clause = (IWhereClause)var3.next();
                    if(i++ > 0) {
                        sqlBuf.append(" ").append("AND").append(" ");
                    }
                }

                sqlBuf.append(")");
                return sqlBuf.toString();
            } else {
                for(var3 = this.commands.iterator(); var3.hasNext(); sqlBuf.append(clause.getSql())) {
                    clause = (IWhereClause)var3.next();
                    if(i++ > 0) {
                        sqlBuf.append(" ").append("AND").append(" ");
                    }
                }

                sqlBuf.append(")");
                return sqlBuf.toString();
            }
        }
    }
}
