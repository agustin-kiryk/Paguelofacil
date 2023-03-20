package com.paguelofacil.repository.Specif;

import com.paguelofacil.entity.TransactionEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TransactionSpecificationBuilder {

  private final SearchCriteria criteria;

  public TransactionSpecificationBuilder(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public Specification<TransactionEntity> buildSpecification() {
    return new Specification<TransactionEntity>() {
      @Override
      public Predicate toPredicate(Root<TransactionEntity> root, CriteriaQuery<?> criteriaQuery,
          CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
          return criteriaBuilder.greaterThan(root.<String>get(criteria.getKey()),
              criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
          return criteriaBuilder.lessThan(root.<String>get(criteria.getKey()),
              criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
          if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return criteriaBuilder.like(root.<String>get(criteria.getKey()),
                "%" + criteria.getValue() + "%");
          } else {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
          }
        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
          return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return null;
      }
    };
  }
}