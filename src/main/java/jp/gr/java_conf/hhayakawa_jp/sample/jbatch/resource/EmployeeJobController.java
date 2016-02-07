/**
 *
 */
package jp.gr.java_conf.hhayakawa_jp.sample.jbatch.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import jp.gr.java_conf.hhayakawa_jp.sample.jbatch.SampleUtils;
import jp.gr.java_conf.hhayakawa_jp.sample.jbatch.model.Employee;

/**
 * @author hhayakaw
 *
 */
@Dependent
@Path("/EmployeeJobController")
public class EmployeeJobController {

    @Resource
    private UserTransaction utx;

    @PersistenceContext(unitName = "jbatch-sample")
    private EntityManager em;

    /**
     * デフォルト コンストラクター
     */
    public EmployeeJobController() {}

    /**
     * ジョブを実行します。
     *
     */
    @GET
    @Path("/Start")
    public String start() {
        SampleUtils.printCodeLocation();
        JobOperator operator = BatchRuntime.getJobOperator();
        long id = operator.start("employeejob", null);
        return "Started: " + id;
    }

    @GET
    @Path("/Restart")
    public String restart(@QueryParam("id") String id) {
        SampleUtils.printCodeLocation();
        JobOperator operator = BatchRuntime.getJobOperator();
        operator.restart(Long.parseLong(id), null);
        return "Restarted: " + id;
    }

    /**
     * EMPLOYEEテーブルの全てのエントリーのデータを取得します。
     *
     * @return EMPLOYEEテーブルの全てのエントリー
     */
    @GET
    @Path("/GetResult")
    @Produces("application/json")
    public List<Employee> getResult() {
        @SuppressWarnings("unchecked")
        List<Employee> employees =
                em.createNamedQuery("Employee.findAll").getResultList();
        return employees;
    }

    /**
     * 永続化されたジョブの実行結果を初期化します。
     *
     * @param label
     */
    @DELETE
    @Path("/Clear")
    public String clear() {
        try {
            utx.begin();
            em.createNamedQuery("Employee.deleteAll").executeUpdate();
            utx.commit();
        } catch (NotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Cleared";
    }

    /**
     * シンプルな文字列を返します。
     *
     * 開発中に、サーバーが動作していることを確認するために利用することを想定しています。
     *
     * @param label
     */
    @GET
    @Path("/Ping")
    public String ping() {
        return "I'm working...";
    }

}
