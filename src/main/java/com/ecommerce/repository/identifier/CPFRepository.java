package com.ecommerce.repository.identifier;

import com.ecommerce.entity.identifier.CPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CPFRepository extends JpaRepository<CPF, Long> {

    Optional<CPF> findByIdentifier(String identifier);

}
