import java.io.*;
import java.util.ArrayList;
import java.util.StringJoiner;


abstract class MessData {
    private String dataType;
    private final String line = "------------------------------------------------------------";
    String hash = null;

    MessData(String dataType) {
        this.dataType = dataType;
    }

    private void writeToDisk(String s) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataType + ".json"));
            bufferedWriter.write(s);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract String deal(ArrayList<ArrayList<String>> blocks);

    private String deal(File file) {
        hash = file.getName().split("_")[0];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> allLine = new ArrayList<>();
            String tmp;
            // 读入全部
            while ((tmp = bufferedReader.readLine()) != null) {
                allLine.add(tmp);
            }
            ArrayList<ArrayList<String>> blocks = new ArrayList<>();
            for (int i = 0; i < allLine.size(); i++) {
                ArrayList<String> muLine = new ArrayList<>();
                while (!allLine.get(i).equals(line)) {
                    muLine.add(allLine.get(i));
                    i++;
                }
                blocks.add(muLine);
            }
            return deal(blocks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deal() {
        File folder = new File(dataType);
        File[] files = folder.listFiles();
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        if (files != null) {
            for (File file : files) {
                joiner.add(deal(file));
            }
        }
        writeToDisk(joiner.toString());
        System.out.println(dataType + ":done");
    }

    final void dealDebug() {
        File folder = new File(dataType);
        File[] files = folder.listFiles();
        if (files != null) {
            System.out.println(deal(files[0]));
        }
    }

    // 参数为字符串的对象或者数组
    final String createJsonObject(String key, String value) {
        StringJoiner joiner = new StringJoiner(":", "{", "}");
        joiner.add(
                createJsonString(key)
        ).add(
                value
        );
        return joiner.toString();
    }

    /**
     * 对于原来一行被逗号分割的多个数据转化为json对象
     *
     * @param arr  split后的数组
     * @param name 对象中各个属性的名字
     * @return “{}”的字符串json对象
     */
    final String createJsonObject(String[] arr, String[] name) {
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        for (int i = 0; i < arr.length; i++) {
            joiner.add(createJsonKeyValue(name[i], arr[i]));
        }
        return joiner.toString();
    }

    /**
     * 将数组里面存放的json化的字符串组合为json数组
     *
     * @param arr json化的字符串
     * @return “[{},{}]”的json数组
     */
    String createJsonArray(ArrayList<String> arr) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (String s : arr) {
            joiner.add(s);
        }
        return joiner.toString();

    }


    /**
     * 将名称和值json化为一个json键值对，但不加大括号，作为json对象的一个键值对
     *
     * @param key   键名
     * @param value 键值
     * @return “"key":"value"”
     */
    private String createJsonKeyValue(String key, String value) {
        return String.join(":",
                createJsonString(key),
                createJsonString(value)
        );
    }

    /**
     * 给名称或值加引号
     *
     * @param s 需要加引号的字符串
     * @return 加了引号后的字符串
     */
    private String createJsonString(String s) {
        return String.join(s, "\"", "\"");
    }
}
