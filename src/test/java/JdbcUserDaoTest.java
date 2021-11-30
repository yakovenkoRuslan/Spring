import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.nixsolution.config.hibernate.HibernateConfig;
import com.nixsolution.config.spring.SpringConfig;
import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;
import com.nixsolution.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, SpringConfig.class})
@DBUnit(caseInsensitiveStrategy = Orthography.UPPERCASE, url = "jdbc:h2:~/test/spring/test", driver = "org.h2.Driver", user = "root", password = "root")
@WebAppConfiguration
public class JdbcUserDaoTest {

    @Autowired
    UserService userService;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance( );

    public JdbcUserDaoTest() throws SQLException {
    }

    @Test
    @DataSet(value = "datasets/dataRole.xml", strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
            "sqlCode/dropAll.sql", "sqlCode/createATables.sql" })
    public void shouldReturnTheUsernameOfTheUserAfterAddingItToTheDatabase() {
        User user = getNewUser();
        userService.save(user);
        List<User> list = userService.findAll();
        assertEquals(1, list.size());
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldReturnAllUsersFromDatabase() throws SQLException {
        List<User> list = userService.findAll();
        assertEquals(1, list.size());
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldBecomeZeroTheSizeOfTheTableAfterDeletingOneRecord() {
        User user = getNewUser();
        userService.delete(user.getId());
        List<User> list = userService.findAll();
        assertEquals(0, list.size());
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldReturnUserById() {
        User user = userService.findById(1);
        assertNotNull(user);
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldReturnUserByLogin() {
        User user = userService.findByLogin("lumiere");
        assertNotNull(user);
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldReturnUserByEmail() {
        User user = userService.findByEmail("ruslan@gmail.com");
        assertNotNull(user);
    }

    @Test
    @DataSet(value = { "datasets/dataRole.xml",
            "datasets/dataUser.xml" }, strategy = SeedStrategy.CLEAN_INSERT, cleanAfter = true, cleanBefore = true, tableOrdering = {
            "role", "user" }, executeScriptsBefore = { "sqlCode/dropAll.sql",
            "sqlCode/createATables.sql" })
    public void shouldBeUpdatedLoginAfterUpdatingTheFieldOfTableUser() {
        User user = userService.findById(1);
        assertEquals("lumiere", user.getLogin());
        userService.update(getNewUser());
        User user1 = userService.findById(1);
        assertEquals("rusik", user1.getLogin());
    }

    public User getNewUser() {
        Role role = new Role();
        role.setId(1);
        User user = new User();
        user.setId(1);
        user.setLogin("rusik");
        user.setPassword("123");
        user.setEmail("ruslan@gmail.com");
        user.setFirstName("Ruslan");
        user.setLastName("Yakovenko");
        user.setBirthday(new Date(2019, 3, 20));
        user.setRole(role);
        return user;
    }
}

