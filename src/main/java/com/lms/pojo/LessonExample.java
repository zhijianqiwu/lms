package com.lms.pojo;

import java.util.ArrayList;
import java.util.List;

public class LessonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LessonExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andLnoIsNull() {
            addCriterion("lno is null");
            return (Criteria) this;
        }

        public Criteria andLnoIsNotNull() {
            addCriterion("lno is not null");
            return (Criteria) this;
        }

        public Criteria andLnoEqualTo(String value) {
            addCriterion("lno =", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoNotEqualTo(String value) {
            addCriterion("lno <>", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoGreaterThan(String value) {
            addCriterion("lno >", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoGreaterThanOrEqualTo(String value) {
            addCriterion("lno >=", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoLessThan(String value) {
            addCriterion("lno <", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoLessThanOrEqualTo(String value) {
            addCriterion("lno <=", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoLike(String value) {
            addCriterion("lno like", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoNotLike(String value) {
            addCriterion("lno not like", value, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoIn(List<String> values) {
            addCriterion("lno in", values, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoNotIn(List<String> values) {
            addCriterion("lno not in", values, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoBetween(String value1, String value2) {
            addCriterion("lno between", value1, value2, "lno");
            return (Criteria) this;
        }

        public Criteria andLnoNotBetween(String value1, String value2) {
            addCriterion("lno not between", value1, value2, "lno");
            return (Criteria) this;
        }

        public Criteria andCnoIsNull() {
            addCriterion("cno is null");
            return (Criteria) this;
        }

        public Criteria andCnoIsNotNull() {
            addCriterion("cno is not null");
            return (Criteria) this;
        }

        public Criteria andCnoEqualTo(String value) {
            addCriterion("cno =", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotEqualTo(String value) {
            addCriterion("cno <>", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoGreaterThan(String value) {
            addCriterion("cno >", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoGreaterThanOrEqualTo(String value) {
            addCriterion("cno >=", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLessThan(String value) {
            addCriterion("cno <", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLessThanOrEqualTo(String value) {
            addCriterion("cno <=", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLike(String value) {
            addCriterion("cno like", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotLike(String value) {
            addCriterion("cno not like", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoIn(List<String> values) {
            addCriterion("cno in", values, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotIn(List<String> values) {
            addCriterion("cno not in", values, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoBetween(String value1, String value2) {
            addCriterion("cno between", value1, value2, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotBetween(String value1, String value2) {
            addCriterion("cno not between", value1, value2, "cno");
            return (Criteria) this;
        }

        public Criteria andRnoIsNull() {
            addCriterion("rno is null");
            return (Criteria) this;
        }

        public Criteria andRnoIsNotNull() {
            addCriterion("rno is not null");
            return (Criteria) this;
        }

        public Criteria andRnoEqualTo(String value) {
            addCriterion("rno =", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoNotEqualTo(String value) {
            addCriterion("rno <>", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoGreaterThan(String value) {
            addCriterion("rno >", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoGreaterThanOrEqualTo(String value) {
            addCriterion("rno >=", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoLessThan(String value) {
            addCriterion("rno <", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoLessThanOrEqualTo(String value) {
            addCriterion("rno <=", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoLike(String value) {
            addCriterion("rno like", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoNotLike(String value) {
            addCriterion("rno not like", value, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoIn(List<String> values) {
            addCriterion("rno in", values, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoNotIn(List<String> values) {
            addCriterion("rno not in", values, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoBetween(String value1, String value2) {
            addCriterion("rno between", value1, value2, "rno");
            return (Criteria) this;
        }

        public Criteria andRnoNotBetween(String value1, String value2) {
            addCriterion("rno not between", value1, value2, "rno");
            return (Criteria) this;
        }

        public Criteria andTnoIsNull() {
            addCriterion("tno is null");
            return (Criteria) this;
        }

        public Criteria andTnoIsNotNull() {
            addCriterion("tno is not null");
            return (Criteria) this;
        }

        public Criteria andTnoEqualTo(String value) {
            addCriterion("tno =", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotEqualTo(String value) {
            addCriterion("tno <>", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThan(String value) {
            addCriterion("tno >", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThanOrEqualTo(String value) {
            addCriterion("tno >=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThan(String value) {
            addCriterion("tno <", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThanOrEqualTo(String value) {
            addCriterion("tno <=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLike(String value) {
            addCriterion("tno like", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotLike(String value) {
            addCriterion("tno not like", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoIn(List<String> values) {
            addCriterion("tno in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotIn(List<String> values) {
            addCriterion("tno not in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoBetween(String value1, String value2) {
            addCriterion("tno between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotBetween(String value1, String value2) {
            addCriterion("tno not between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andCsweekIsNull() {
            addCriterion("csweek is null");
            return (Criteria) this;
        }

        public Criteria andCsweekIsNotNull() {
            addCriterion("csweek is not null");
            return (Criteria) this;
        }

        public Criteria andCsweekEqualTo(Integer value) {
            addCriterion("csweek =", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekNotEqualTo(Integer value) {
            addCriterion("csweek <>", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekGreaterThan(Integer value) {
            addCriterion("csweek >", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekGreaterThanOrEqualTo(Integer value) {
            addCriterion("csweek >=", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekLessThan(Integer value) {
            addCriterion("csweek <", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekLessThanOrEqualTo(Integer value) {
            addCriterion("csweek <=", value, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekIn(List<Integer> values) {
            addCriterion("csweek in", values, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekNotIn(List<Integer> values) {
            addCriterion("csweek not in", values, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekBetween(Integer value1, Integer value2) {
            addCriterion("csweek between", value1, value2, "csweek");
            return (Criteria) this;
        }

        public Criteria andCsweekNotBetween(Integer value1, Integer value2) {
            addCriterion("csweek not between", value1, value2, "csweek");
            return (Criteria) this;
        }

        public Criteria andCeweekIsNull() {
            addCriterion("ceweek is null");
            return (Criteria) this;
        }

        public Criteria andCeweekIsNotNull() {
            addCriterion("ceweek is not null");
            return (Criteria) this;
        }

        public Criteria andCeweekEqualTo(Integer value) {
            addCriterion("ceweek =", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekNotEqualTo(Integer value) {
            addCriterion("ceweek <>", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekGreaterThan(Integer value) {
            addCriterion("ceweek >", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekGreaterThanOrEqualTo(Integer value) {
            addCriterion("ceweek >=", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekLessThan(Integer value) {
            addCriterion("ceweek <", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekLessThanOrEqualTo(Integer value) {
            addCriterion("ceweek <=", value, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekIn(List<Integer> values) {
            addCriterion("ceweek in", values, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekNotIn(List<Integer> values) {
            addCriterion("ceweek not in", values, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekBetween(Integer value1, Integer value2) {
            addCriterion("ceweek between", value1, value2, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCeweekNotBetween(Integer value1, Integer value2) {
            addCriterion("ceweek not between", value1, value2, "ceweek");
            return (Criteria) this;
        }

        public Criteria andCdayIsNull() {
            addCriterion("cday is null");
            return (Criteria) this;
        }

        public Criteria andCdayIsNotNull() {
            addCriterion("cday is not null");
            return (Criteria) this;
        }

        public Criteria andCdayEqualTo(Integer value) {
            addCriterion("cday =", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayNotEqualTo(Integer value) {
            addCriterion("cday <>", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayGreaterThan(Integer value) {
            addCriterion("cday >", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("cday >=", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayLessThan(Integer value) {
            addCriterion("cday <", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayLessThanOrEqualTo(Integer value) {
            addCriterion("cday <=", value, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayIn(List<Integer> values) {
            addCriterion("cday in", values, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayNotIn(List<Integer> values) {
            addCriterion("cday not in", values, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayBetween(Integer value1, Integer value2) {
            addCriterion("cday between", value1, value2, "cday");
            return (Criteria) this;
        }

        public Criteria andCdayNotBetween(Integer value1, Integer value2) {
            addCriterion("cday not between", value1, value2, "cday");
            return (Criteria) this;
        }

        public Criteria andCsnumberIsNull() {
            addCriterion("csnumber is null");
            return (Criteria) this;
        }

        public Criteria andCsnumberIsNotNull() {
            addCriterion("csnumber is not null");
            return (Criteria) this;
        }

        public Criteria andCsnumberEqualTo(Integer value) {
            addCriterion("csnumber =", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberNotEqualTo(Integer value) {
            addCriterion("csnumber <>", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberGreaterThan(Integer value) {
            addCriterion("csnumber >", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("csnumber >=", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberLessThan(Integer value) {
            addCriterion("csnumber <", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberLessThanOrEqualTo(Integer value) {
            addCriterion("csnumber <=", value, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberIn(List<Integer> values) {
            addCriterion("csnumber in", values, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberNotIn(List<Integer> values) {
            addCriterion("csnumber not in", values, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberBetween(Integer value1, Integer value2) {
            addCriterion("csnumber between", value1, value2, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCsnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("csnumber not between", value1, value2, "csnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberIsNull() {
            addCriterion("cwnumber is null");
            return (Criteria) this;
        }

        public Criteria andCwnumberIsNotNull() {
            addCriterion("cwnumber is not null");
            return (Criteria) this;
        }

        public Criteria andCwnumberEqualTo(Integer value) {
            addCriterion("cwnumber =", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberNotEqualTo(Integer value) {
            addCriterion("cwnumber <>", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberGreaterThan(Integer value) {
            addCriterion("cwnumber >", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("cwnumber >=", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberLessThan(Integer value) {
            addCriterion("cwnumber <", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberLessThanOrEqualTo(Integer value) {
            addCriterion("cwnumber <=", value, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberIn(List<Integer> values) {
            addCriterion("cwnumber in", values, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberNotIn(List<Integer> values) {
            addCriterion("cwnumber not in", values, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberBetween(Integer value1, Integer value2) {
            addCriterion("cwnumber between", value1, value2, "cwnumber");
            return (Criteria) this;
        }

        public Criteria andCwnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("cwnumber not between", value1, value2, "cwnumber");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}