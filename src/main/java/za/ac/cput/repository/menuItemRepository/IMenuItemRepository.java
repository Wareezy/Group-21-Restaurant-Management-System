package za.ac.cput.repository.menuItemRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.MenuItem;

@Repository
public interface IMenuItemRepository extends JpaRepository<MenuItem,String> {
}

