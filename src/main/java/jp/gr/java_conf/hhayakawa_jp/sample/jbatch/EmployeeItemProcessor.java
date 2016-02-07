/**
 *
 */
package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import jp.gr.java_conf.hhayakawa_jp.sample.jbatch.model.Employee;

/**
 *
 *
 * @author hhayakaw
 *
 */
@Dependent
@Named("EmployeeItemProcessor")
public class EmployeeItemProcessor implements ItemProcessor {

    public EmployeeItemProcessor() {
    }

    /**
     *
     *
     * @see javax.batch.api.chunk.ItemProcessor#processItem(java.lang.Object)
     */
    @Override
    public Object processItem(Object line) throws Exception {
        SampleUtils.printCodeLocation();
        if (!(line instanceof String)) {
            // TODO: Exceptionを投げる。この場合は即ジョブを終了する
        }
        String[] params = ((String)line).split(",");
        if (params.length != 4) {
            // TODO: 不正データがあったらログに書いてスキップするよう実装する
        }
        Long employee_id = 0L;
        try {
            employee_id = Long.valueOf(params[0]);
        } catch (NumberFormatException e) {
            // TODO: 不正データがあったらログに書いてスキップするよう実装する
        }
        // TODO:入力csvの終端に余計な改行があるとエラーになるのを修正する
        return new Employee(employee_id, params[1], params[2], params[3]);
    }

}
