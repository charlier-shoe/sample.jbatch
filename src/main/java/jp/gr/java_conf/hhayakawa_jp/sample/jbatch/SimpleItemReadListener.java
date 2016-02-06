package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("SimpleItemReadListener")
public class SimpleItemReadListener implements ItemReadListener {

    @Inject
    JobContext jobCtx;

    @Override
    public void afterRead(Object arg0) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeRead() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void onReadError(Exception arg0) throws Exception {
        SampleUtils.printCodeLocation();
        // TODO: job.xmlで停止状態になるように設定すべきのような気がする
        JobOperator operator = BatchRuntime.getJobOperator();
        operator.stop(jobCtx.getExecutionId());
    }

}
