package br.com.uaijug.appex.appex.model.repository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.uaijug.appex.appex.model.domain.UserRole;

@Repository
@Qualifier(value = "userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	@Query("SELECT ur FROM br.com.uaijug.appex.appex.model.domain.UserRole ur WHERE ur.user.id = :userId")
	Set<UserRole> findRoleNameByUserId(@Param("userId") Long userId);
	
	UserRole findByRoleName(@Param("roleName") String roleName);
}