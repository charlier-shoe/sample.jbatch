package jp.gr.java_conf.hhayakawa_jp.sample.jbatch;

/**
 * サンプルアプリケーション用のユーティリティメソッドを集めたクラス
 *
 * @author hhayakaw
 *
 */
public class SampleUtils {

    /**
     * このメソッドを実行した箇所のクラス名、メソッド名、行番号を標準出力に出力します。
     * 文字列のフォーマットは以下のとおりです。
     *
     * ${クラス名]#${メソッド名}(${行番号})
     */
    public static void printCodeLocation() {
        StackTraceElement throwableStackTraceElement =
                new Throwable().getStackTrace()[1];
        System.out.println(throwableStackTraceElement.getClassName()
                + "#" + throwableStackTraceElement.getMethodName()
                + "(" + throwableStackTraceElement.getLineNumber() + ")");
    }
}
