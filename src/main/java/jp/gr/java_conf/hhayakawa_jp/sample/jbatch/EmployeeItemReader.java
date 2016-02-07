/**
 *
 */
package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * @author hiroshi.hayakawa@oracle.com
 *
 */
@Dependent
@Named("EmployeeItemReader")
public class EmployeeItemReader implements ItemReader {

    public EmployeeItemReader() {
    }

    /**
     * 現在の行番号を記録するカウンター。入力ファイルを一行読み込む度にインクリメントする
     */
    private int lineNumber = 0;
    /**
     * 入力ファイルのReader
     */
    private LineNumberReader reader = null;

    private BufferedReader br = null;
    /**
     * 入力ファイルのパス
     */
    private static final String INPUT_FILE_PATH = "data/employees.csv";

    /**
     *
     *
     * @see javax.batch.api.chunk.ItemReader#checkpointInfo()
     */
    @Override
    public Serializable checkpointInfo() throws Exception {
        SampleUtils.printCodeLocation();
        return lineNumber;
    }

    /**
     *
     *
     * @see javax.batch.api.chunk.ItemReader#close()
     */
    @Override
    public void close() throws Exception {
        SampleUtils.printCodeLocation();
        if (reader != null) {
            reader.close();
        }
    }

    /**
     *
     *
     * @see javax.batch.api.chunk.ItemReader#open(java.io.Serializable)
     */
    @Override
    public void open(Serializable arg0) throws Exception {
        SampleUtils.printCodeLocation();
        try {
            this.br = Files.newBufferedReader(
                Paths.get(INPUT_FILE_PATH), StandardCharsets.UTF_8);
            this.reader = new LineNumberReader(br);
        } catch (IOException e) {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e2) {
            }
            throw e;
        }
    }

    /**
     *
     *
     * @see javax.batch.api.chunk.ItemReader#readItem()
     */
    @Override
    public Object readItem() throws Exception {
        SampleUtils.printCodeLocation();
        String line = reader.readLine();
        lineNumber = reader.getLineNumber();
        return line;
    }

}
