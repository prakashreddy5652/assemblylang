package assemblylang.example.assemblylang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assemblylang.example.assemblylang.entity.AssemblyPrograms;


@Repository
public interface AssemblyRepository extends JpaRepository<AssemblyPrograms,Long>{
	
};