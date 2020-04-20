package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception{
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUoM() throws Exception{
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        Long count = unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1), count);
    }

    @Test
    public void testFindByDescription() throws Exception{
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("test UoM");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        UnitOfMeasure foundUoM = unitOfMeasureReactiveRepository.findByDescription("test UoM").block();

        assertNotNull(foundUoM.getId());
    }
}
