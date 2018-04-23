package com.elex.oa.query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface IWhereClause {
    Predicate execute(CriteriaBuilder var1, Root var2, Class var3) throws Exception;

    String getSql();
}