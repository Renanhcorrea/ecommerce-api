package com.ecommerce.repository.identifier;

import com.ecommerce.entity.identifier.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CNPJRepository extends JpaRepository<CNPJ, Long> {

    Optional<CNPJ> findByIdentifier(String identifier);

}
