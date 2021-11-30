import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.nixsolution.config.hibernate.HibernateConfig;
import com.nixsolution.config.spring.SpringConfig;
import com.nixsolution.entity.Role;
import com.nixsolution.service.RoleService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, SpringConfig.class })
@DBUnit(caseInsensitiveStrategy = Orthography.UPPERCASE, url = "jdbc:h2:~/test/spring/test", driver = "org.h2.Driver", user = "root", password = "root")
@WebAppConfiguration
public class JdbcRoleDaoTest {

    @Autowired
    RoleService roleService;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();

    public JdbcRoleDaoTest() throws SQLException {
    }

    @Test
    @DataSet(strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldReturnTheNameOfTheRoleAfterAddingItToTheDatabase() {
        roleService.save(getNewRole());
        List<Role> list = roleService.findAll();
        assertEquals(1, list.size());
    }

    @Test
    @DataSet(value = "dataRole.xml", strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldReturnAllRolesFromDatabase() throws SQLException {
        List<Role> list = roleService.findAll();
        assertEquals(2, list.size());
    }

    @Test
    @DataSet(value = "dataRole.xml", strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldBecomeOneTheSizeOfTheTableAfterDeletingOneRecord() {
        Role role = getNewRole();
        roleService.delete(role.getId());
        List<Role> list = roleService.findAll();
        assertEquals(1, list.size());
    }

    @Test
    @DataSet(value = "dataRole.xml", strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldReturnRoleById() {
        Role role = roleService.findById(1);
        assertNotNull(role);
    }

    @Test
    @DataSet(value = "dataRole.xml", strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldReturnRoleByName() {
        Role role = roleService.findByName("bodyPositive");
        assertNotNull(role);
    }

    @Test
    @DataSet(value = "dataRole.xml", strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldBeUpdatedRoleAfterUpdatingTheFieldOfTableRole() {
        Role role = roleService.findById(1);
        assertEquals("king", role.getName());
        role.setName("not king");
        roleService.update(role);
        role = roleService.findByName("not king");
        assertEquals("not king", role.getName());
    }

    private Role getNewRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("king");
        return role;
    }
}

