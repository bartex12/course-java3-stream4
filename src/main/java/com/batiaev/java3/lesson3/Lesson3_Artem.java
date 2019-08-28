package com.batiaev.java3.lesson3;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Lesson3_Artem {
    public static void main(String[] args) throws IOException {

        //fileManipulations();
        //fileinputStreamWork();
        //bufReaderWork();
        //pipeWork(); //ошибки!
        //pipedStream(); //без ошибок
        seqInputStreamWork();
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
        arr.add(new FileInputStream("123/test1.txt"));
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
        RandomAccessFile randomAccessFile = new RandomAccessFile("123/test1.txt","r");
        randomAccessFile.seek(1);
        System.out.println(randomAccessFile.r);
    }

}

