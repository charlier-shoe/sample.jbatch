package jp.gr.java_conf.hhayakawa_jp.sample.jbatch.resource;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import jp.gr.java_conf.hhayakawa_jp.sample.jbatch.SampleUtils;

/**
 * ジョブの起動等の制御を行うための、RESTfulサービス インターフェースを提供します
 *
 * @author hhayakaw
 */
@Path("/JobController")
public class JobController {

    /**
     * デフォルト コンストラクター
     */
    public JobController() {}

    /**
     * ジョブを実行します
     *
     * @return ジョブを開始した旨のメッセージと、ジョブIDを含む文字列
     */
    @GET
    @Path("/Start")
    public String start() {
        SampleUtils.printCodeLocation();
        JobOperator operator = BatchRuntime.getJobOperator();
        long id = operator.start("samplejob", null);
        return "Started: " + id;
    }

    /**
     * 中断したジョブを再開します
     *
     * @param id
     * @return ジョブを再開した旨のメッセージと、ジョブIDを含む文字列
     */
    @GET
    @Path("/Restart")
    public String restart(@QueryParam("jobid") Long id) {
        SampleUtils.printCodeLocation();
        JobOperator operator = BatchRuntime.getJobOperator();
        // TODO: ジョブが存在しなかったり、中断状態ではない場合での例外処理
//        JobInstance job = operator.getJobInstance(id);
//        if (job == null) {
//            return "job[ " + id + "] does not exist.";
//        }
        operator.restart(id, null);
        return "Restarted: " + id;
    }

    /**
     * シンプルな文字列を返します<br>
     * アプリケーションが起動していることを確認するために利用することを想定しています。
     *
     * @return メソッドが正しく実行されたことを示す文字列
     */
    @GET
    @Path("/Ping")
    public String ping() {
        return "I'm working...";
    }

}
