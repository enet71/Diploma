import com.diploma.dataBase.Command;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;

public class AnalysisTest extends Assert{
    @Test
    public void testQueryAnalysis() throws Exception{

        Query query = Command.getEm().createQuery("select a.id FROM Analysis as a WHERE a.name = 'qwe'");
        int a = ((Integer)query.getResultList().get(0));
        assertEquals(a,21);
    }
}
