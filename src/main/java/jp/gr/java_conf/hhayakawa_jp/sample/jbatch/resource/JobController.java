package jp.gr.java_conf.hhayakawa_jp.sample.jbatch.resource;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
