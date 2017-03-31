package cn.mybookmanager.model;

import java.util.ArrayList;
import java.util.List;

public class BookrecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookrecordsExample() {
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

        public Criteria andBookmemuidIsNull() {
            addCriterion("bookMemuId is null");
            return (Criteria) this;
        }

        public Criteria andBookmemuidIsNotNull() {
            addCriterion("bookMemuId is not null");
            return (Criteria) this;
        }

        public Criteria andBookmemuidEqualTo(Integer value) {
            addCriterion("bookMemuId =", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidNotEqualTo(Integer value) {
            addCriterion("bookMemuId <>", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidGreaterThan(Integer value) {
            addCriterion("bookMemuId >", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookMemuId >=", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidLessThan(Integer value) {
            addCriterion("bookMemuId <", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidLessThanOrEqualTo(Integer value) {
            addCriterion("bookMemuId <=", value, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidIn(List<Integer> values) {
            addCriterion("bookMemuId in", values, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidNotIn(List<Integer> values) {
            addCriterion("bookMemuId not in", values, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidBetween(Integer value1, Integer value2) {
            addCriterion("bookMemuId between", value1, value2, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookmemuidNotBetween(Integer value1, Integer value2) {
            addCriterion("bookMemuId not between", value1, value2, "bookmemuid");
            return (Criteria) this;
        }

        public Criteria andBookidIsNull() {
            addCriterion("bookid is null");
            return (Criteria) this;
        }

        public Criteria andBookidIsNotNull() {
            addCriterion("bookid is not null");
            return (Criteria) this;
        }

        public Criteria andBookidEqualTo(Integer value) {
            addCriterion("bookid =", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotEqualTo(Integer value) {
            addCriterion("bookid <>", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThan(Integer value) {
            addCriterion("bookid >", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookid >=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThan(Integer value) {
            addCriterion("bookid <", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThanOrEqualTo(Integer value) {
            addCriterion("bookid <=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidIn(List<Integer> values) {
            addCriterion("bookid in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotIn(List<Integer> values) {
            addCriterion("bookid not in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidBetween(Integer value1, Integer value2) {
            addCriterion("bookid between", value1, value2, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotBetween(Integer value1, Integer value2) {
            addCriterion("bookid not between", value1, value2, "bookid");
            return (Criteria) this;
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

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIsNull() {
            addCriterion("purchaseTime is null");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIsNotNull() {
            addCriterion("purchaseTime is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeEqualTo(String value) {
            addCriterion("purchaseTime =", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotEqualTo(String value) {
            addCriterion("purchaseTime <>", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeGreaterThan(String value) {
            addCriterion("purchaseTime >", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeGreaterThanOrEqualTo(String value) {
            addCriterion("purchaseTime >=", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeLessThan(String value) {
            addCriterion("purchaseTime <", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeLessThanOrEqualTo(String value) {
            addCriterion("purchaseTime <=", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeLike(String value) {
            addCriterion("purchaseTime like", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotLike(String value) {
            addCriterion("purchaseTime not like", value, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeIn(List<String> values) {
            addCriterion("purchaseTime in", values, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotIn(List<String> values) {
            addCriterion("purchaseTime not in", values, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeBetween(String value1, String value2) {
            addCriterion("purchaseTime between", value1, value2, "purchasetime");
            return (Criteria) this;
        }

        public Criteria andPurchasetimeNotBetween(String value1, String value2) {
            addCriterion("purchaseTime not between", value1, value2, "purchasetime");
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