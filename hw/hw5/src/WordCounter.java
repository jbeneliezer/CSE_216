import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ThreadHandler implements Runnable {
    public static File in;
    public ThreadHandler(File i) {
        in = i;
        System.out.println(in.toString());
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + " started.");
            List<String> words = WordCounter.readWords(in);
            WordCounter.fileElements.add(WordCounter.getMap(words));
            WordCounter.total = WordCounter.getTotal(words, WordCounter.total);
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class WordCounter {

    public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("files"); // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("output.txt"); // path to the output plain-text (.txt) file
    public static final int  NUMBER_OF_THREADS     = 10;                // max. number of threads to spawn
    public static List<File> files = new ArrayList<>();
    public static Map<String, Integer> total = new TreeMap<>();
    public static List<Map<String, Integer>> fileElements = new ArrayList<>();

    public static void getInput() throws NullPointerException {
        File directory = new File(FOLDER_OF_TEXT_FILES.toString());
        files.addAll(Arrays.asList(directory.listFiles()));
        Collections.sort(files);
    }

    public static synchronized List<String> readWords(File in) throws FileNotFoundException {
        Scanner sc = new Scanner(in).useDelimiter("\\W");
        List<String> words = new ArrayList<>();
        while (sc.hasNext()) {
            words.add(sc.next());
        }

        return words.stream().filter(s -> s.length() > 0).map(String::toLowerCase).sorted().collect(Collectors.toList());
    }
    public static synchronized Map<String, Integer> getMap(List<String> filtered) {
        Map<String, Integer> map = new TreeMap<>();
        for (String i : filtered) {
            if (map.containsKey(i)) {
                map.replace(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    public static synchronized Map<String, Integer> getTotal(List<String> filtered, Map<String, Integer> t) {
        Map<String, Integer> newT = t;
        for (String i: filtered) {
            if (newT.containsKey(i)) {
                newT.replace(i, WordCounter.total.get(i) + 1);
            } else {
                newT.put(i, 1);
            }
        }
        return newT;
    }

    public static void main(String... args) {
        try {
            File output = new File(WORD_COUNT_TABLE_FILE.toString());
            FileWriter fw = new FileWriter(output);
            BufferedWriter bw = new BufferedWriter(fw);

            getInput();
            bw.write(String.format("%20s", ""));
            BufferedWriter finalBw = bw;
            files.forEach(s -> {
                try {
                    finalBw.write(String.format("%15s", s.getName()));
                    finalBw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.write("\n");
            bw.close();
            fw.close();

            for (int i = 0; i < files.size(); ++i) {
                if (i < NUMBER_OF_THREADS) {
                    ThreadHandler th = new ThreadHandler(files.get(i));
                    Thread thread = new Thread(th);
                    thread.start();
                } else {
                    ThreadHandler fh = new ThreadHandler(files.get(i));
                    fh.run();
                }
            }
            fw = new FileWriter(output, true);
            bw = new BufferedWriter(fw);

            for (String i: total.keySet()) {
                bw.write(String.format("%20s", i));
                for (int j = 0; j < files.size(); ++j) {
                    System.out.println(fileElements.get(j));
                    bw.write(String.format("%15s", (fileElements.get(j).containsKey(i) ? fileElements.get(j).get(i): "")));
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
            fw.close();

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
}
