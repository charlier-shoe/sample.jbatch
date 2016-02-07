/**
 *
 */
package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author hiroshi.hayakawa@oracle.com
 *
 */
@Dependent
@Named("EmployeeItemWriter")
public class EmployeeItemWriter implements ItemWriter {

    public EmployeeItemWriter() {
    }

    @PersistenceContext(unitName = "jbatch-sample")
    private EntityManager em;

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemWriter#checkpointInfo()
     */
    @Override
    public Serializable checkpointInfo() throws Exception {
        SampleUtils.printCodeLocation();
        return "writer checkpoint";
    }

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemWriter#close()
     */
    @Override
    public void close() throws Exception {
        SampleUtils.printCodeLocation();
        // コンテナ管理のトランザクションとなるので、ここではクローズできない
//        em.close();
    }

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemWriter#open(java.io.Serializable)
     */
    @Override
    public void open(Serializable arg0) throws Exception {
        SampleUtils.printCodeLocation();
        System.out.println(arg0);
    }

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemWriter#writeItems(java.util.List)
     */
    @Override
    public void writeItems(List<Object> employees) throws Exception {
        SampleUtils.printCodeLocation();
        for (Object employee : employees) {
            System.out.println(employee.toString());
            em.persist(employee);
        }
    }

}
