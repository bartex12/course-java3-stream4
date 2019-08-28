package com.batiaev.java3.lesson3;


import java.io.*;
import java.util.*;

public class Lesson3_Artem {
    public static void main(String[] args) throws IOException {

        //fileManipulations();
        //fileinputStreamWork();
        //bufReaderWork();
        //pipeWork(); //ошибки!
        //pipedStream(); //без ошибок
        //seqInputStreamWork();
        //randomAccessWork();
        consoleBook();
       // bufferedWriterAndReaderArrayList();
    }

    private static void fileManipulations() throws IOException {
        System.out.println("**************** выводим список");
        //file.mkdirs(); //создаёт весь путь
        File file1 = new File("123");
        String[] str = file1.list(); //выводит и файлы и папки
        for (String s : str) {
            System.out.println(s);
        }
        System.out.println("**************** выводим список по фильтру");

        File file2 = new File("123");
        String[] str2 = file1.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //return name.startsWith("t"); // только файлы, начинающиеся с
                return name.endsWith(".txt"); //только файлы заканчивающиеся на
            }
        }); //выводит и файлы и папки
        for (String s2 : str2) {
            System.out.println(s2);
        }
        System.out.println("**************** создаём не через mkdirs");

        File file3 = new File("123/test2.txt");
        file3.createNewFile();
        System.out.println(file3.exists());
        //file3.renameTo()
        System.out.println(file3.getPath());
        System.out.println(file3.getAbsolutePath());
    }

    private static void fileinputStreamWork() throws IOException {
        System.out.println("******************* Ввод по 1 байту");
        FileInputStream in = new FileInputStream("123/test2.txt");
        int n;
        while ((n = in.read()) != -1) {
            System.out.print((char) n);
        }
        System.out.println();

        System.out.println("******************* ввод через массив байт");
        byte[] arr = new byte[1024];
        FileInputStream in2 = new FileInputStream("123/test.txt");
        int x;
        while ((x = in2.read(arr)) > 0) {
            System.out.print(new String(arr, 0, x));
        }
        System.out.println();
        System.out.println("*******************");
    }

    private static void bufReaderWork() throws IOException {

        System.out.println("******** ввод через буферизированный символьный поток *********** ");
        System.out.println();

        BufferedReader br = null;
        FileReader fr = null;

        fr = new FileReader("123/test.txt");
        br = new BufferedReader(fr);
        String s;
        while ((s = br.readLine())!=null){
            System.out.println(s);
        }
    }

    private static void pipeWork() throws IOException {

        //ОШИбки валятся в каждом цикле !!!

        PipedInputStream in = null;
        PipedOutputStream out = null;
        in = new PipedInputStream();
        out = new PipedOutputStream();
        out.connect(in);
        out.flush();
        PipedOutputStream finalOut = out;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<100; i++){
                    try {
                        finalOut.write(i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        PipedInputStream finalIn = in;
        new Thread(new Runnable() {
            @Override
            public void run() {

                int x;
                while (true){
                    try {
                        if (!((x = finalIn.read())!=-1)){
                            System.out.println(x);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    //из батяева
    private static void pipedStream() {
        PipedInputStream in = null;
        PipedOutputStream out = null;
        try {
            in = new PipedInputStream();
            out = new PipedOutputStream();
            out.connect(in);
            out.flush();
            for (int i = 0; i < 100; i++) {
                out.write(i);
            }
            int x;
            while ((x = in.read()) != -1) {
                System.out.print(x + " ");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void seqInputStreamWork() throws IOException {
        ArrayList<InputStream> arr = new ArrayList<>();
        arr.add(new FileInputStream("123/test.txt"));
        arr.add(new FileInputStream("123/test2.txt"));
        arr.add(new FileInputStream("123/test3.txt"));
        arr.add(new FileInputStream("123/test4.txt"));
        arr.add(new FileInputStream("123/test5.txt"));
        SequenceInputStream seq = new SequenceInputStream(Collections.enumeration(arr));
        int x;
        while ((x=seq.read())!=-1){
            System.out.print((char)x);
        }
    }

    private static void randomAccessWork() throws IOException {

        byte[] bt = new byte[1800];
        RandomAccessFile raf= new RandomAccessFile("123/test.txt","r");
        raf.seek(5);
        int x = 0;
        x = raf.read(bt,0,11);
        System.out.print(new String(bt, 0,x));
        }

    private static void   bufferedWriterAndReaderArrayList(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Один");
        arr.add("Два");
        arr.add("Три");
        arr.add("Четыре");
        arr.add("Пять");
        System.out.println("arr.size() = " + arr.size());

        //блок записи  в файл
        File buf = new File("bufWriter.txt");
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(buf))){
            for (String s: arr){
                bw.write(s);
                bw.write(System.getProperty("line.separator"));
            }
            bw.flush();
            System.out.println(" buf.length()" + buf.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //блок чтения из файла
        try( BufferedReader br = new BufferedReader(new FileReader(buf))) {
            ArrayList<String> input = new ArrayList<>();
            String str;
            while ((str = br.readLine())!=null){
                input.add(str);
            }
            for (String s: input){
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        //консольное приложение Книга
    private static void consoleBook() throws IOException {

        System.out.println("Консольное приложение \"Книга\" ");
        final int PAGE_SIZE = 2000;// размер страницы  2000 символов/100 символов в строке  = 20 строк
        final int LINES = 100000; //количество строк по 100 случайных символов
        byte[] bt = new byte[PAGE_SIZE]; //размер буфера = размеру страницы
        File file = new File("123/book.txt");
        ArrayList<String> arr = new ArrayList<>(LINES); //ArrayList подходящего размера
        Random rnd = new Random();

            for (int i = 0; i<LINES; i++ ){
                String str = "";
                for (int jj = 0; jj<100; jj++){
                    int к = 65 + rnd.nextInt(60);
                    str += (char)к; //собираем строку из 100 символов
                }
                arr.add(str); //добавляем строку в ArrayList
        }
            //заполняем файл строками из ArrayList
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (String ii: arr){
                bw.write(ii);
                bw.write(System.getProperty("line.separator"));
            }
            bw.flush();
            arr.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long size = file.length(); //узнаём размер файла
        System.out.println("Размер файла = " + size);
        long pageCountMax = size/PAGE_SIZE +1; //рассчитываем количество страниц
        System.out.println("Страниц в файле = " + pageCountMax);

        //Метод, реализующий консольное приложение
        //читаем файл постранично
        RandomAccessFile raf= new RandomAccessFile(file,"r");
            System.out.println("*** Для выхода введите /exit \n");
            System.out.println("Введите номер страницы число от 1 до " + pageCountMax);
            Scanner in = new Scanner(System.in);

            while (true){
                String s = in.nextLine();
                if (s.trim().startsWith("/exit")){
                  System.exit(0);
                }else if (s.trim().equals("")) {
                    System.out.println("Введите число");
                }else {
                    long pageNumber = Long.parseLong(s);
                    if (pageNumber<1)pageNumber = 1;
                    if (pageNumber > pageCountMax) pageNumber = pageCountMax;
                    System.out.println("Страница: " + pageNumber);

                    raf.seek((pageNumber-1)*(PAGE_SIZE));
                    int x = 0;
                    x = raf.read(bt,0,PAGE_SIZE);
                    System.out.println(new String(bt, 0,x));

                }
            }

    }

    }



