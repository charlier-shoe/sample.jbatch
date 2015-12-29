package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * ItemProcessorの実装のサンプル
 *
 * @author hhayakaw
 *
 */
@Dependent
@Named("SimpleItemProcessor")
public class SimpleItemProcessor implements ItemProcessor {

    /**
     * processItem()の実装。<br>
     * ItemReader#readItem()が返すオブジェクト（readItem()がジョブの中で実行された回数を表す
     * Integer）をそのまま返却する。
     *
     * @see javax.batch.api.chunk.ItemProcessor#processItem(java.lang.Object)
     */
    @Override
    public Object processItem(Object readCount) throws Exception {
        SampleUtils.printCodeLocation();
        return readCount;
    }

}
