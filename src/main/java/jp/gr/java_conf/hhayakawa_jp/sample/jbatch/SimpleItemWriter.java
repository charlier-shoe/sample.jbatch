package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * ItemWriterの実装のサンプル
 *
 * @author hhayakaw
 *
 */
@Dependent
@Named("SimpleItemWriter")
public class SimpleItemWriter implements ItemWriter {

    /**
     * 現在の、ItemWriterの実行回数を表すカウンター
     */
    private int count = 0;

    /**
     * checkpointInfo()の実装。<br>
     * writeItemsの実行回数のカウンター値を返却する。
     *
     * @see javax.batch.api.chunk.ItemWriter#checkpointInfo()
     */
    @Override
    public Serializable checkpointInfo() throws Exception {
        SampleUtils.printCodeLocation();
        return count;
    }

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemWriter#close()
     */
    @Override
    public void close() throws Exception {
        SampleUtils.printCodeLocation();
    }

    /**
     * open()の実装。<br>
     * checkpointInfoが存在するときはジョブが再開されたとき。この場合、checkpointInfoを
     * カウンター値に設定する。
     *
     * @see javax.batch.api.chunk.ItemWriter#open(java.io.Serializable)
     */
    @Override
    public void open(Serializable checkpointInfo) throws Exception {
        SampleUtils.printCodeLocation();
        if (checkpointInfo != null && checkpointInfo instanceof Integer) {
            this.count = (Integer)checkpointInfo;
        } else {
            this.count = 0;
        }
    }

    /**
     * writeItems()の実装。<br>
     * このメソッドが呼ばれたら、カウンターの値をインクリメントする。また、これまでのItemReader#readItem()
     * と、ItemWriter#writeItems()の実行回数を標準出力に出力する。
     *
     * @see javax.batch.api.chunk.ItemWriter#writeItems(java.util.List)
     */
    @Override
    public void writeItems(List<Object> readCount) throws Exception {
        SampleUtils.printCodeLocation();
        count++;
        System.out.println("ItemReader#readItem() has been executed ["
                + readCount.get(readCount.size() - 1) + "] times.");
        System.out.println("ItemWriter#writeItem() has benn executed ["
                + count + "] times(including this execution).");
    }

}
