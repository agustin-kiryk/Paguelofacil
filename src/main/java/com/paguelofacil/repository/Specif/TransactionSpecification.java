package com.paguelofacil.repository.Specif;

import com.paguelofacil.entity.TransactionEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component

public class TransactionSpecification implements Specification<TransactionEntity> {

  private final String filter;
  private final String conditional;

  public TransactionSpecification(@Value("${transaction.filter}") String filter,
      @Value("${transaction.conditional}") String conditional) {
    this.filter = filter;
    this.conditional = conditional;
  }

  @Override
  public Predicate toPredicate(Root<TransactionEntity> root, CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    if (filter != null && !filter.isEmpty()) {
      String[] filters = filter.split("\\|");
      for (String f : filters) {
        String[] filterParts = f.split("::");
        if (filterParts.length == 2) {
          String fieldName = filterParts[0];
          String value = filterParts[1];
          predicates.add(criteriaBuilder.equal(root.get(fieldName), value));
        }
      }
    }
    if (conditional != null && !conditional.isEmpty()) {
      String[] conditionals = conditional.split("\\|");
      for (String c : conditionals) {
        String[] conditionalParts = c.split("::");
        if (conditionalParts.length == 2) {
          String fieldName = conditionalParts[0];
          String value = conditionalParts[1];
          if (fieldName.endsWith("$bt")) {
            String[] rangeValues = value.split(",");
            if (rangeValues.length == 2) {
              predicates.add(
                  criteriaBuilder.between(root.get(fieldName.substring(0, fieldName.length() - 3)),
                      rangeValues[0], rangeValues[1]));
            }
          } else {
            String operator = value.substring(0, 2);
            String val = value.substring(2);
            switch (operator) {
              case "=":
                predicates.add(criteriaBuilder.equal(root.get(fieldName), val));
                break;
              case ">=":
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), val));
                break;
              case "<=":
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), val));
                break;
              case "<>":
                predicates.add(criteriaBuilder.notEqual(root.get(fieldName), val));
                break;
              case ">":
                predicates.add(criteriaBuilder.greaterThan(root.get(fieldName), val));
                break;
              case "<":
                predicates.add(criteriaBuilder.lessThan(root.get(fieldName), val));
                break;
              case "~":
                predicates.add(criteriaBuilder.like(root.get(fieldName), "%" + val + "%"));
                break;
              default:
                break;
            }
          }
        }
      }
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}




