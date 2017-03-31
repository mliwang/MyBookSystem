package cn.mybookmanager.model;

import java.util.ArrayList;
import java.util.List;

public class BooksupplierinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BooksupplierinfoExample() {
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

        public Criteria andSupplieridIsNull() {
            addCriterion("supplierId is null");
            return (Criteria) this;
        }

        public Criteria andSupplieridIsNotNull() {
            addCriterion("supplierId is not null");
            return (Criteria) this;
        }

        public Criteria andSupplieridEqualTo(Integer value) {
            addCriterion("supplierId =", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridNotEqualTo(Integer value) {
            addCriterion("supplierId <>", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridGreaterThan(Integer value) {
            addCriterion("supplierId >", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplierId >=", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridLessThan(Integer value) {
            addCriterion("supplierId <", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridLessThanOrEqualTo(Integer value) {
            addCriterion("supplierId <=", value, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridIn(List<Integer> values) {
            addCriterion("supplierId in", values, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridNotIn(List<Integer> values) {
            addCriterion("supplierId not in", values, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridBetween(Integer value1, Integer value2) {
            addCriterion("supplierId between", value1, value2, "supplierid");
            return (Criteria) this;
        }

        public Criteria andSupplieridNotBetween(Integer value1, Integer value2) {
            addCriterion("supplierId not between", value1, value2, "supplierid");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierIsNull() {
            addCriterion("nameOfSupplier is null");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierIsNotNull() {
            addCriterion("nameOfSupplier is not null");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierEqualTo(String value) {
            addCriterion("nameOfSupplier =", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierNotEqualTo(String value) {
            addCriterion("nameOfSupplier <>", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierGreaterThan(String value) {
            addCriterion("nameOfSupplier >", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierGreaterThanOrEqualTo(String value) {
            addCriterion("nameOfSupplier >=", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierLessThan(String value) {
            addCriterion("nameOfSupplier <", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierLessThanOrEqualTo(String value) {
            addCriterion("nameOfSupplier <=", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierLike(String value) {
            addCriterion("nameOfSupplier like", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierNotLike(String value) {
            addCriterion("nameOfSupplier not like", value, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierIn(List<String> values) {
            addCriterion("nameOfSupplier in", values, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierNotIn(List<String> values) {
            addCriterion("nameOfSupplier not in", values, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierBetween(String value1, String value2) {
            addCriterion("nameOfSupplier between", value1, value2, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andNameofsupplierNotBetween(String value1, String value2) {
            addCriterion("nameOfSupplier not between", value1, value2, "nameofsupplier");
            return (Criteria) this;
        }

        public Criteria andSupphoneIsNull() {
            addCriterion("supPhone is null");
            return (Criteria) this;
        }

        public Criteria andSupphoneIsNotNull() {
            addCriterion("supPhone is not null");
            return (Criteria) this;
        }

        public Criteria andSupphoneEqualTo(Integer value) {
            addCriterion("supPhone =", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneNotEqualTo(Integer value) {
            addCriterion("supPhone <>", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneGreaterThan(Integer value) {
            addCriterion("supPhone >", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("supPhone >=", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneLessThan(Integer value) {
            addCriterion("supPhone <", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneLessThanOrEqualTo(Integer value) {
            addCriterion("supPhone <=", value, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneIn(List<Integer> values) {
            addCriterion("supPhone in", values, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneNotIn(List<Integer> values) {
            addCriterion("supPhone not in", values, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneBetween(Integer value1, Integer value2) {
            addCriterion("supPhone between", value1, value2, "supphone");
            return (Criteria) this;
        }

        public Criteria andSupphoneNotBetween(Integer value1, Integer value2) {
            addCriterion("supPhone not between", value1, value2, "supphone");
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