package in.nexa.creditmanager.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.creditmanager.app.model.SanctionLetter;

@Repository
public interface SanctionLetterRepo extends JpaRepository<SanctionLetter, Integer>{

}
