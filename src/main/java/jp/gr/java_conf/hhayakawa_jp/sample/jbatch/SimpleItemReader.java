package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import java.io.Serializable;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ItemReaderの実装のサンプル
 *
 * @author hhayakaw
 *
 */
@Dependent
@Named("SimpleItemReader")
public class SimpleItemReader implements ItemReader {

    @Inject
    @BatchProperty(name="itemNumber")
    private String itemNumberString;
    /**
     *
     */
    private int itemNumber = 0;
    /**
     * 現在の、ItemReaderの実行回数を表すカウンター
     */
    private int count = 0;

    /**
     * checkpointInfo()の実装。<br>
     * readItemの実行回数のカウンター値を返却する。
     *
     * @see javax.batch.api.chunk.ItemReader#checkpointInfo()
     */
    @Override
    public Serializable checkpointInfo() throws Exception {
        SampleUtils.printCodeLocation();
        return count;
    }

    /* (non-Javadoc)
     * @see javax.batch.api.chunk.ItemReader#close()
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
     * @see javax.batch.api.chunk.ItemReader#open(java.io.Serializable)
     */
    @Override
    public void open(Serializable checkpointInfo) throws Exception {
        SampleUtils.printCodeLocation();
        itemNumber = Integer.valueOf(itemNumberString);
        if (checkpointInfo != null && checkpointInfo instanceof Integer) {
            this.count = (Integer)checkpointInfo;
        } else {
            this.count = 0;
        }
    }

    /**
     * readItem()の実装。<br>
     * このメソッドが呼ばれたら、カウンターの値をインクリメントする。
     *
     * @see javax.batch.api.chunk.ItemReader#readItem()
     */
    @Override
    public Object readItem() throws Exception {
        SampleUtils.printCodeLocation();
        if (count >= itemNumber) {
            return null;
        }
        return ++count;
    }

}