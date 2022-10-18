package za.ac.cput.repository.menuRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Menu;

@Repository
public interface IMenuRepository extends JpaRepository<Menu,String> {

}

